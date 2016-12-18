package com.arshan.it.ltd.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

public class MongoJournalEntryData {

	@Id
	private String id;
	private String title;
	private Date created;
	private String summary;
	@Transient
	private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

	public MongoJournalEntryData(String title, String summary, String date) throws ParseException {
		this.title = title;
		this.summary = summary;
		this.created = format.parse(date);
	}

	MongoJournalEntryData() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String toString() {
		StringBuilder value = new StringBuilder("* MongoJournalEntryData(");
		value.append("Id: ");
		value.append(id);
		value.append(",Title: ");
		value.append(title);
		value.append(",Summary: ");
		value.append(summary);
		value.append(",Created: ");
		value.append(format.format(created));
		value.append(")");
		return value.toString();
	}

}
