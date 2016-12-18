package com.arshan.it.ltd.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arshan.it.ltd.domain.JournalJpaEntry;

public interface JournalJpaRepresentInt extends JpaRepository<JournalJpaEntry, Long> {

	List<JournalJpaEntry> findByCreatedAfter(Date date);

	@Query("select j from JournalJpaEntry j where j.title like %?1%")
	List<JournalJpaEntry> findByCustomQuery(String word);
}
