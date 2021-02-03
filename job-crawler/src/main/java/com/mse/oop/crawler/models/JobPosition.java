/**
 * 
 */
package com.mse.oop.crawler.models;

import java.sql.Timestamp;

import javafx.scene.control.Hyperlink;

/**
 * 
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public class JobPosition implements TableElement {
	private int id;
	private Timestamp downloadedAt;
	private String label;

	@Override
	public String toString() {
		return "JobPosition [id=" + id + ", downloadedAt=" + downloadedAt + ", label=" + label + ", link=" + link
				+ ", position=" + position + ", description=" + description + ", refNumber=" + refNumber + ", location="
				+ location + ", salary=" + salary + "]";
	}

	private String link;
	private Hyperlink hyperLink;

	private String position;
	private String description;
	private String refNumber;
	private String location;
	private String salary;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public Timestamp getDownloadedAt() {
		return downloadedAt;
	}

	public void setDownloadedAt(Timestamp downloadedAt) {
		this.downloadedAt = downloadedAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Hyperlink getHyperLink() {
		return hyperLink;
	}

	public void setHyperLink(Hyperlink hyperLink) {
		this.hyperLink = hyperLink;
	}

}
