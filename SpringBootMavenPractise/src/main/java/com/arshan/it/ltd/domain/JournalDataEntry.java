package com.arshan.it.ltd.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JournalDataEntry {

	private Long id;
	private String title;
	private String summary;
	private Date created;

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	public JournalDataEntry(Long id, String title, String summary, Date date) {
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.created = date;
	}

	public JournalDataEntry() {
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

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreatedAsShort() {
		return dateFormat.format(created);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("JournalDataEntry [");
		builder.append("Id :");
		builder.append(id);
		builder.append(",Title :");
		builder.append(title);
		builder.append(",Summary :");
		builder.append(summary);
		builder.append(",created Date :");
		builder.append(getCreatedAsShort());
		builder.append("]");
		return builder.toString();
	}

}
