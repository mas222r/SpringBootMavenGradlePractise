package com.arshan.it.ltd;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arshan.it.ltd.domain.MongoJournalEntryData;
import com.arshan.it.ltd.messageing.Producer;
import com.arshan.it.ltd.messageing.rabbitmq.RabbitMqProducer;
import com.arshan.it.ltd.service.JournalJpaService;
import com.arshan.it.ltd.service.JournalService;
import com.arshan.it.ltd.service.MongoJournalRepository;

@EnableScheduling
@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class SpringBootMavenPractiseApplication extends SpringBootServletInitializer implements ApplicationRunner, CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(SpringBootMavenPractiseApplication.class);

	@RequestMapping("/")
	String home() {
		return "Hello Arshan World";
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootMavenPractiseApplication.class);
	}

	public static void main(String[] args) {

		// 1.
		SpringApplication.run(SpringBootMavenPractiseApplication.class, args);
		// 2.
		/*
		 * SpringApplication app = new
		 * SpringApplication(SpringBootMavenPractiseApplication.class);
		 * app.setBanner(new Banner() {
		 * 
		 * @Override public void printBanner(Environment environment, Class<?>
		 * sourceClass, PrintStream out) { // TODO Auto-generated method stub
		 * out.print("\n\n\tArshan It pvt ltd!\n\n".toUpperCase()); } });
		 * app.setBannerMode(Mode.OFF); app.run(args);
		 */
		// 3.
		// new
		// SpringApplicationBuilder().bannerMode(Mode.OFF).sources(SpringBootMavenPractiseApplication.class).run(args);

		// You can log the info at start up or not
		// new
		// SpringApplicationBuilder(SpringBootMavenPractiseApplication.class).logStartupInfo(false).run(args);

		// You can add profiles
		// new
		// SpringApplicationBuilder(SpringBootMavenPractiseApplication.class).profiles("prod","cloud").run(args);

		/*
		 * Logger log =
		 * LoggerFactory.getLogger(SpringBootMavenPractiseApplication.class);
		 * new
		 * SpringApplicationBuilder(SpringBootMavenPractiseApplication.class)
		 * .listeners(new ApplicationListener<ApplicationEvent>() {
		 * 
		 * @Override public void onApplicationEvent(ApplicationEvent event) { //
		 * TODO Auto-generated method stub log.info("#### > " +
		 * event.getClass().getCanonicalName()); }
		 * 
		 * }) .run(args);
		 */

	}

	@Bean
	String info() {
		return "Just a simple String bean";
	}

	@Autowired
	String info;

	@Autowired
	private JournalService journalService;

	@Override
	public void run(String... args) throws Exception {
		log.info("## > CommandLineRunner Implementation...");
		log.info("Accessing the Info bean: " + info);
		for (String arg : args)
			log.info(arg);

		// Data access object implementation
		log.info("@@ Inserting Data....");
		journalService.insertData();
		log.info("@@ findAll() call...");
		journalService.findAll().forEach(entry -> log.info(entry.toString()));
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		log.info("## > ApplicationRunner Implementation...");
		log.info("Accessing the Info bean: " + info);
		args.getNonOptionArgs().forEach(file -> log.info(file));
	}

	@Bean
	CommandLineRunner myMethod() {
		return args -> {
			log.info("## > CommandLineRunner2 Implementation ...");
			log.info("Accessing the Info bean: " + info);
			for (String arg : args)
				log.info(arg);
		};
	}

	@Value("${server.ip}")
	String serverIp1;

	@Bean
	CommandLineRunner value() {
		return args -> {
			log.info("The Server ip from application properties :" + serverIp1);
		};
	}

	@Value("${arshapp.server-ip}")
	String serverIp;

	@Autowired
	ArshAppAppProperties props;

	@Bean
	CommandLineRunner values() {
		return args -> {
			log.info(" > The Server IP is: " + serverIp);
			log.info(" > App Name: " + props.getName());
			log.info(" > App Info: " + props.getDescription());
		};
	}

	@Bean
	CommandLineRunner start(JournalJpaService journalJpaService) {
		return args -> {
			log.info("@@ Inserting Data using JPA....");
			// journalJpaService.insertData();
			log.info("@@ findAll() call using JPA...");
			journalJpaService.findAll().forEach(entry -> log.info(entry.toString()));
		};
	}

	@Bean
	CommandLineRunner mongoStart(MongoJournalRepository mongoJournalRepository) {
		return args -> {
			log.info("> Deleting existing data from mongodb ...");
			mongoJournalRepository.deleteAll();
			log.info("> Inserting new data in mongodb table...");
			mongoJournalRepository.save(new MongoJournalEntryData("Get to know Spring Boot Starter Mongodb", "Today I will learn Spring Boot with mongodb",
					"10/12/2016"));
			mongoJournalRepository.save(new MongoJournalEntryData("Simple Spring Boot Starter Mongodb Project", "I will do my first Spring Boot mongodb Project",
					"11/12/2016"));
			mongoJournalRepository.save(new MongoJournalEntryData("Spring Boot other modules intergration Reading", "Read more about Spring Boot", "12/12/2016"));
			mongoJournalRepository.save(new MongoJournalEntryData("Spring Boot in the Cloud", "Spring Boot using Cloud Foundry",
					"13/12/2016"));
			log.info("> Getting all data from mongodb...");
			mongoJournalRepository.findAll().forEach(entry -> log.info(entry.toString()));
			log.info("> Getting data from mongodb using like...");
			mongoJournalRepository.findByTitleLike("Cloud").forEach(entry -> log.info(entry.toString()));
		};
	}
	
	@JmsListener(destination="${myqueue}")
	@SendTo("${myqueuetwo}")
	public String simpleConsumerImpl(String message)
	{
		log.info("Simpler Consumer defined in spring boot > " + message);
		
		return message + " and return Spring Messaging too!";
	}
	
	@JmsListener(destination = "${myqueuetwo}")
	public void anotherSimplerConsumer(String message) {
		log.info("Another Simpler Consumer> " + message);
	}
	
	@Value("${myqueue}")
	String myQueue;
	
	@Bean
	CommandLineRunner simpleConsumerStart(JmsTemplate jmsTemplate) {
		return args -> {
			
			log.info("Sending message for simple Consumer > ...");
			
			jmsTemplate.convertAndSend(myQueue, "SpringBoot A simple Consumer Rocks!");
			
		};
	}
	
	@Bean
	CommandLineRunner sendMessage(JmsTemplate jmsTemplate) {
		return args -> {
			Producer producer = new Producer(jmsTemplate);
			producer.sendTo(myQueue, "JMS with Spring Boot Rocks!");
		};
	}
	
	
	@Value("${myRabbitQueue}")
	String myrabbitQueue;
	
	@Bean
	Queue queue(){
		return new Queue(myrabbitQueue, false);
	}
	
	
	@Bean
	CommandLineRunner sender(RabbitMqProducer rabbitMqProducer) {
		return args -> {
			rabbitMqProducer.sendTo(myrabbitQueue, "JMS with Spring Boot using Rabbit MQ");
		};
	}
	
	/*@Autowired
	private RabbitMqProducer rabbitMqProducer;
	
	@Scheduled(fixedDelay = 500L)
	public void sendMessages(){
		rabbitMqProducer.sendTo(myrabbitQueue, "Rabbit MQ Hello World at " + new Date());
	}*/

}

@Component
class MyComponent {
	private static final Logger log = LoggerFactory.getLogger(MyComponent.class);

	@Autowired
	public MyComponent(ApplicationArguments args) {
		boolean enable = args.containsOption("enable");
		if (enable)
			log.info("## > You are enable!");
		List<String> _args = args.getNonOptionArgs();
		log.info("## > extra args ...");
		if (!_args.isEmpty())
			_args.forEach(file -> log.info(file));
	}
}
