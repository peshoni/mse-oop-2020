package com.mse.oop.crawler.utils;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.mse.oop.crawler.core.Timeouts;
import com.mse.oop.crawler.models.JobSite;

import javafx.scene.control.Alert;

/**
 * Crawler utility class
 * 
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public class CUtil {
	private CUtil() {
	}

	public static ArrayList<JobSite> getJobSitesCollection() {
		ArrayList<JobSite> jobSites = new ArrayList<JobSite>();
		AtomicInteger atomInt = new AtomicInteger(1);

		JobSite site = new JobSite();
		site.setId(atomInt.getAndIncrement());
		site.setName("Jobs.bg");
		site.setSiteType("multi-page");
		site.setTimeoutType(Timeouts.SMALL.getTypeId());
		site.setMaxItemsPerDownload(10);

		site.setSelectorJobDescription(
				"body > table:nth-child(4) > tbody > tr > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(3) > td");
		site.setSelectorLocaion(
				"body > table:nth-child(4) > tbody > tr > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(3) > td");
		site.setSelectorPossition(
				"body > table:nth-child(4) > tbody > tr > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(2) > td > b");
		site.setSelectorRefNumber(
				"body > table:nth-child(4) > tbody > tr > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(1) > td");
		site.setSelectorSalary(
				"body > table:nth-child(4) > tbody > tr > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(3) > td > span");
		try {

			site.setUrl("https://www.jobs.bg/front_job_search.php?frompage=0");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		// site.setUrl(new
		// Hyperlink("https://www.jobs.bg/front_job_search.php?frompage=0"));
		jobSites.add(site);

		site = new JobSite();
		site.setId(atomInt.getAndIncrement());
		site.setName("MegaJobs.bg");
		site.setSiteType("multi-page");
		site.setTimeoutType(Timeouts.SMALL.getTypeId());
		site.setMaxItemsPerDownload(10);
		site.setSelectorJobDescription("description selector");
		site.setSelectorLocaion("loaction selector");
		site.setSelectorPossition("possition selector");
		site.setSelectorRefNumber("ref selector");
		site.setSelectorSalary("salary selector");
		site.setUrl("www.google.com");
		jobSites.add(site);

		return jobSites;
//		Timeouts to = Timeouts.valueOf(1);
//		System.out.println(to.toString());
	}

	public static void showError(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
