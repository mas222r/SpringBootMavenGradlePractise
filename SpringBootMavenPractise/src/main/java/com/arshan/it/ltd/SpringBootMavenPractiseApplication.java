package com.arshan.it.ltd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class SpringBootMavenPractiseApplication {
	
	@RequestMapping("/")
	String home()
	{
		return "Hello Arshan World";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMavenPractiseApplication.class, args);
	}
}
