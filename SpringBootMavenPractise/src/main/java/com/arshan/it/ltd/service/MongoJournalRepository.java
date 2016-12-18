package com.arshan.it.ltd.service;

import com.arshan.it.ltd.domain.MongoJournalEntryData;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoJournalRepository extends MongoRepository<MongoJournalEntryData, String> {
	public List<MongoJournalEntryData> findByTitleLike(String word);
}
