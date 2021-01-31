package com.mse.oop.crawler.utils;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mse.oop.crawler.core.Timeouts;
import com.mse.oop.crawler.models.JobSite;

import javafx.scene.control.Alert;

/**
 * Crawler utility class
 * 
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public class CrawlerUtil {
	public final static Pattern DIGIT_IN_STRING_PATTERN = Pattern.compile("\\d+");

	private CrawlerUtil() {
	}

	public static ArrayList<JobSite> getJobSitesCollection() {
		ArrayList<JobSite> jobSites = new ArrayList<JobSite>();
		AtomicInteger atomInt = new AtomicInteger(1);

		JobSite site = new JobSite();
		site.setId(atomInt.getAndIncrement());
		site.setName("Jobs.bg");
		site.setSiteType("multi-page");
		site.setTimeoutType(Timeouts.SMALL.getTypeId());
		// TODO FIX FIELDS
		// max items per transaction
		// max page limitation
		// REMOVE THIS PER DOWNLOAD- because we get 1 per load..
		site.setMaxItemsPerDownload(10);

		site.setSelectorPaginator(
				"#search_results_div > table > tbody > tr > td > table > tbody > tr:nth-child(2) > td");

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

			System.out.println(e.getMessage());
		}
		// site.setUrl(new
		// Hyperlink("https://www.jobs.bg/front_job_search.php?frompage=0"));
		jobSites.add(site);

		////////

		JobSite zaplataBg = new JobSite();
		zaplataBg.setId(atomInt.getAndIncrement());
		zaplataBg.setName("Zaplata.bg");
		zaplataBg.setSiteType("multi-page");
		zaplataBg.setTimeoutType(Timeouts.SMALL.getTypeId());

		zaplataBg.setMaxItemsPerDownload(10);

		zaplataBg.setSelectorPaginator(
				"body > div.page.lineHeightFix.pInside > ul.scheme23 > li.left > div.pageOptions > strong");

		zaplataBg.setSelectorJobDescription(
				"body > table:nth-child(4) > tbody > tr > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(3) > td");
		zaplataBg.setSelectorLocaion(
				"body > table:nth-child(4) > tbody > tr > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(3) > td");
		zaplataBg.setSelectorPossition(
				"body > table:nth-child(4) > tbody > tr > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(2) > td > b");
		zaplataBg.setSelectorRefNumber(
				"body > table:nth-child(4) > tbody > tr > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(1) > td");
		zaplataBg.setSelectorSalary(
				"body > table:nth-child(4) > tbody > tr > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(3) > td > span");
		try {

			zaplataBg.setUrl(
					"https://www.zaplata.bg/search/?q=&city=&city_distance=0&price=200%3B10000&cat%5B0%5D=1000&go=");
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		// BULJOBS

//		site = new JobSite();
//		site.setName("BULJOBS");
//		site.setUrl("http://www.buljobs.bg/работа-Производство.aspx?rabota=2Od&job=CategoryID");
//		site.setSelectorPaginator("");

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

	public static int[] getSitePaginatorParams(String text) {
		int result[] = new int[] { 0, 0 };
		try {
			Matcher m = DIGIT_IN_STRING_PATTERN.matcher(text);
			String matcherResult = "";
			while (m.find()) {
				matcherResult += m.group() + " ";
			}
			String[] splitResult = matcherResult.split(" ");
			result[0] = Integer.parseInt(splitResult[1]);
			result[1] = Integer.parseInt(splitResult[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
