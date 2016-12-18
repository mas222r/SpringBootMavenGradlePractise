package com.arshan.it.ltd.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JournalEntry {

	private String title;
	private String summary;
	private Date created;

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	public JournalEntry(String title, String summary, String date) throws ParseException {

		this.title = title;
		this.summary = summary;
		this.created = dateFormat.parse(date);
	}

	public JournalEntry() {
		// TODO Auto-generated constructor stub
	}
	
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(String date) throws ParseException {
		Long date1 = null;
		try
		{
			date1 = Long.parseLong(date);
		    this.created = new Date(date1);
		    return;
		}
		catch(Exception ex){}
		this.created = dateFormat.parse(date);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("JournalEntry [");
		builder.append("Title :");
		builder.append(title);
		builder.append(",Summary :");
		builder.append(summary);
		builder.append(",created Date :");
		builder.append(dateFormat.format(created));
		builder.append("]");
		return builder.toString();
	}

}
