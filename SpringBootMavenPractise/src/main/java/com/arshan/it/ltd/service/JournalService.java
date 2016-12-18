package com.arshan.it.ltd.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.arshan.it.ltd.domain.JournalDataEntry;

@Service
public class JournalService {

	private static final Logger log = LoggerFactory.getLogger(JournalService.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertData() {

		log.info("> Table creation");
		jdbcTemplate.execute("DROP TABLE JOURNAL IF EXISTS");
		jdbcTemplate
				.execute("CREATE TABLE JOURNAL(id SERIAL, title VARCHAR(255),summary VARCHAR(255), created TIMESTAMP)");
		log.info("> Inserting data...");
		jdbcTemplate.execute(
				"INSERT INTO JOURNAL(title,summary,created) VALUES('Get to know Spring Boot','Today I will learn Spring Boot','2016-01-01 00:00:00.00')");
		jdbcTemplate.execute(
				"INSERT INTO JOURNAL(title,summary,created) VALUES('Simple Spring Boot Project','I will do my first Spring Boot project','2016-01-02 00:00:00.00')");
		jdbcTemplate.execute(
				"INSERT INTO JOURNAL(title,summary,created) VALUES('Spring Boot Reading','Read more about Spring Boot','2016-02-01 00:00:00.00')");
		jdbcTemplate.execute(
				"INSERT INTO JOURNAL(title,summary,created) VALUES('Spring Boot in the Cloud','Learn Spring Boot using Cloud Foundry','2016-01-01 00:00:00.00')");
		log.info("> Done.");
		
		// Account table insertion
		
		log.info("> Account Table creation");
		jdbcTemplate.execute("DROP TABLE ACCOUNT IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE ACCOUNT ( ACCOUNT_NAME VARCHAR(255) NOT NULL,PASSWORD VARCHAR(255 ) NOT NULL,ID SERIAL,ENABLED BOOL DEFAULT true)");
		
		log.info("> Inserting data into ACCOUNT...");

		jdbcTemplate.execute("INSERT INTO ACCOUNT(account_name , password) VALUES('springboot', 'isawesome')");
		jdbcTemplate.execute("INSERT INTO ACCOUNT(account_name , password) VALUES('springsecurity', 'isawesometoo')");
		
	}

	public List<JournalDataEntry> findAll() throws Exception {
		List<JournalDataEntry> entries = new ArrayList<>();
		jdbcTemplate.query("SELECT * FROM JOURNAL",new Object[] {}, 
						   (rs, row) -> new JournalDataEntry(rs.getLong("id"), rs.getString("title"), rs.getString("summary"), new Date(rs.getTimestamp("created").getTime()))
						   )
						.forEach(entry -> entries.add(entry));
		return entries;
	}
	
	
	
}
