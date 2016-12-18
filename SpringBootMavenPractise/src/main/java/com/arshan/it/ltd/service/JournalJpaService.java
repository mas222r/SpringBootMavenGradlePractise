package com.arshan.it.ltd.service;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arshan.it.ltd.domain.JournalJpaEntry;

@Service
public class JournalJpaService {

	private static final Logger log = LoggerFactory.getLogger(JournalJpaService.class);

	@Autowired
	private JournalJpaRepresentInt journalJpaRepresentInt;

	/*public void insertData() throws ParseException {

		log.info("> Inserting data...");
		journalJpaRepresentInt
				.save(new JournalJpaEntry("Get to know Spring Boot", "Today I will learn Spring Boot", "12/12/2016"));
		journalJpaRepresentInt.save(new JournalJpaEntry("Simple Spring Boot Project",
				"I will do my first Spring Boot Project", "13/12/2016"));
		journalJpaRepresentInt
				.save(new JournalJpaEntry("Spring Boot Reading", "Read more about Spring Boot", "14/12/2016"));
		journalJpaRepresentInt
				.save(new JournalJpaEntry("Spring Boot in the Cloud", "Spring Boot using Cloud Foundry", "15/12/2016"));
		log.info("> Done.");
	}*/

	public List<JournalJpaEntry> findAll() {
		log.info("> find All data using JPA...");
		return journalJpaRepresentInt.findAll();
	}

}
