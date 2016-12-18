package com.arshan.it.ltd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arshan.it.ltd.service.JournalService;

/*@SpringBootApplication
public class SimpleJdbcApplication implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(SimpleJdbcApplication.class);
	
	@Autowired
	private JournalService journalService;

	public static void main(String[] args) {
		SpringApplication.run(SimpleJdbcApplication.class, args);

	}

	@Override
	public void run(String... arg0) throws Exception {
		
		log.info("@@ Inserting Data....");
		journalService.insertData();
		log.info("@@ findAll() call...");
		journalService.findAll().forEach(entry -> log.info(entry.toString()));
		
	}

}*/
