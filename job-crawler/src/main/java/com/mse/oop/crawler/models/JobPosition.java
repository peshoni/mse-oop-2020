/**
 * 
 */
package com.mse.oop.crawler.models;

import java.util.Date;

import javafx.scene.control.Hyperlink;

/**
 * 
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public class JobPosition implements TableElement {
	private int id;
	private Date downloadedAt;
	private String siteName;
	private String link;
	private Hyperlink hyperLink;
	private String position;
	private String description;
	private String refNumber;
	private String location;
	private String salary;

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

	public Date getDownloadedAt() {
		return downloadedAt;
	}

	public void setDownloadedAt(Date downloadedAt) {
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

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	@Override
	public String toString() {
		return "JobPosition [id=" + id + ", downloadedAt=" + downloadedAt + ", siteName=" + siteName + ", link=" + link
				+ ", hyperLink=" + hyperLink + ", position=" + position + ", description=" + description
				+ ", refNumber=" + refNumber + ", location=" + location + ", salary=" + salary + "]";
	}

}
