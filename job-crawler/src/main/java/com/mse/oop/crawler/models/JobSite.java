/**
 * 
 */
package com.mse.oop.crawler.models;

import javafx.scene.control.Hyperlink;

/**
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public class JobSite implements TableElement {
	private int maxItemsPerDownload;
	private int timeoutType = 1;
	private long id;
	private String name;
	private String siteType;
	private String address;
	private String jobMainPageUrl;
	private String url;
	private Hyperlink hyperLink;
	private String rowSelector;
	private String selectorPaginator;
	private String selectorPosition;
	private String selectorJobDescription;
	private String selectorRefNumber;
	private String selectorLocaion;
	private String selectorSalary;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSiteType() {
		return siteType;
	}

	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJobMainPageUrl() {
		return jobMainPageUrl;
	}

	public void setJobMainPageUrl(String jobMainPageUrl) {
		this.jobMainPageUrl = jobMainPageUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Hyperlink getHyperLink() {
		return hyperLink;
	}

	public void setHyperLink(Hyperlink hyperLink) {
		this.hyperLink = hyperLink;
	}

	public String getRowSelector() {
		return rowSelector;
	}

	public void setRowSelector(String rowSelector) {
		this.rowSelector = rowSelector;
	}

	public String getSelectorPaginator() {
		return selectorPaginator;
	}

	public void setSelectorPaginator(String selectorPaginator) {
		this.selectorPaginator = selectorPaginator;
	}

	public String getSelectorPosition() {
		return selectorPosition;
	}

	public void setSelectorPosition(String selectorPossition) {
		this.selectorPosition = selectorPossition;
	}

	public String getSelectorJobDescription() {
		return selectorJobDescription;
	}

	public void setSelectorJobDescription(String selectorJobDescription) {
		this.selectorJobDescription = selectorJobDescription;
	}

	public String getSelectorRefNumber() {
		return selectorRefNumber;
	}

	public void setSelectorRefNumber(String selectorRefNumber) {
		this.selectorRefNumber = selectorRefNumber;
	}

	public String getSelectorLocaion() {
		return selectorLocaion;
	}

	public void setSelectorLocaion(String selectorLocaion) {
		this.selectorLocaion = selectorLocaion;
	}

	public String getSelectorSalary() {
		return selectorSalary;
	}

	public void setSelectorSalary(String selectorSalary) {
		this.selectorSalary = selectorSalary;
	}

	public int getMaxItemsPerDownload() {
		return maxItemsPerDownload;
	}

	public void setMaxItemsPerDownload(int maxItemsPerDownload) {
		this.maxItemsPerDownload = maxItemsPerDownload;
	}

	public int getTimeoutType() {
		return timeoutType;
	}

	public void setTimeoutType(int timeoutType) {
		this.timeoutType = timeoutType;
	}

}
