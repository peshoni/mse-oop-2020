/**
 * 
 */
package com.edu.crawler.models;

/**
 * 
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public class JobPossition {
	private String label;
	private String link;
	private String possition;
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

	public String getPossition() {
		return possition;
	}

	public void setPossition(String possition) {
		this.possition = possition;
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
}
