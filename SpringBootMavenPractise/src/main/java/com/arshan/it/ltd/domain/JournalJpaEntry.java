package com.arshan.it.ltd.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="JOURNALJPAENTRY")
public class JournalJpaEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private Date created;
	private String summary;
	
	@Transient
	private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

	public JournalJpaEntry(String title, String summary, String date) throws ParseException {
		this.title = title;
		this.summary = summary;
		this.created = format.parse(date);
	}

	public JournalJpaEntry() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("JournalJpaEntry [");
		builder.append("Title :");
		builder.append(title);
		builder.append(",Summary :");
		builder.append(summary);
		builder.append(",created Date :");
		builder.append(format.format(created));
		builder.append("]");
		return builder.toString();
	}
}
