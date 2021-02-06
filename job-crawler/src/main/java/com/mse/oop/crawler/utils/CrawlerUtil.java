package com.mse.oop.crawler.utils;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mse.oop.crawler.core.TimeoutTypes;
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
		/**
		 * Jobs bg - multi-page site
		 */
		JobSite site = new JobSite();
		site.setId(atomInt.getAndIncrement());
		site.setName("Jobs.bg");
		site.setSiteType("multi-page");
		site.setTimeoutType(TimeoutTypes.FAST);
		site.setAddress("https://www.jobs.bg");
		site.setRowSelector(".joblink");
		site.setDownloadLimit(5);
		site.setSelectorPaginator(
				"#search_results_div > table > tbody > tr > td > table > tbody > tr:nth-child(2) > td");
		site.setSelectorJobDescription(
				"body > table:nth-child(4) > tbody > tr > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(3) > td");
		site.setSelectorLocaion(
				"body > table:nth-child(4) > tbody > tr > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(3) > td");
		site.setSelectorPosition(
				"body > table:nth-child(4) > tbody > tr > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(2) > td > b");
		site.setSelectorRefNumber(
				"body > table:nth-child(4) > tbody > tr > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(1) > td");
		site.setSelectorSalary(
				"body > table:nth-child(4) > tbody > tr > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(3) > td > span");
		site.setUrl("https://www.jobs.bg/front_job_search.php?frompage=0");
		jobSites.add(site);

		/**
		 * Zaplata bg - multi-page site
		 */
		JobSite zaplataBg = new JobSite();
		zaplataBg.setId(atomInt.getAndIncrement());
		zaplataBg.setName("Zaplata.bg");
		zaplataBg.setSiteType("multi-page");
		zaplataBg.setTimeoutType(TimeoutTypes.SLOWEST);
		zaplataBg.setAddress("https://www.zaplata.bg");
		zaplataBg.setRowSelector(".c2 > a");
		zaplataBg.setDownloadLimit(10);
		zaplataBg.setSelectorPaginator(
				"body > div.page.lineHeightFix.pInside > ul.scheme23 > li.left > div.pageOptions > strong");
		zaplataBg.setSelectorJobDescription(
				"body > div.page-job > ul.pageJobLayout > li.main > div.shit > ul > li.R > div > span.type");
		zaplataBg.setSelectorLocaion(".searchRes");
		zaplataBg.setSelectorPosition("body > div.page-job > ul.pageJobLayout > li.main > div.shit > ul > li.R > h1");// ".title");
		zaplataBg.setSelectorRefNumber("aaa");
		zaplataBg.setSelectorSalary(
				"body > div.page-job > ul.pageJobLayout > li.main > div.shit > ul > li.L > div > div.zIco > span");
		zaplataBg.setUrl(
				"https://www.zaplata.bg/search/?q=&city=&city_distance=0&price=200%3B10000&cat%5B0%5D=1000&go=");
		jobSites.add(zaplataBg);
		/**
		 * BULJOBS - multi-page site
		 */
		JobSite indeed = new JobSite();
		// https://www.indeed.com/jobs?q=Contact+tracing&l=New+York%2C+NY
		indeed.setId(atomInt.getAndIncrement());
		indeed.setName("indeed.com");
		indeed.setSiteType("multi-page");
		indeed.setTimeoutType(TimeoutTypes.SLOWER);
		indeed.setAddress("https://www.indeed.com");

		indeed.setRowSelector(".title > a");

		indeed.setDownloadLimit(5);
		indeed.setSelectorPaginator("#searchCountPages");
		indeed.setSelectorJobDescription("#jobDescriptionText");
		indeed.setSelectorLocaion(".jobsearch-CompanyInfoWithReview > div > div > div:nth-child(2)");
		indeed.setSelectorPosition(".jobsearch-JobInfoHeader-title-container > h1");
		indeed.setSelectorRefNumber("#jobDescriptionText > div > p:nth-child(15)");
		indeed.setSelectorSalary("#jobDetailsSection > div:nth-child(2) > span");
		indeed.setUrl("https://www.indeed.com/jobs?q=Immediately+hiring&start=");

		jobSites.add(indeed);

		/**
		 * Monster - single-page site
		 */
		JobSite monster = new JobSite();
		monster.setId(atomInt.getAndIncrement());
		monster.setName("monster.com");
		monster.setSiteType("single-page");
		monster.setTimeoutType(TimeoutTypes.SLOWER);
		monster.setAddress("https://www.monster.com/");
		monster.setRowSelector("div.summary > header > h2 > a");
		monster.setDownloadLimit(5);
		monster.setSelectorPaginator("next > time");
		monster.setSelectorJobDescription("#JobViewHeader > header > div.heading > h1");
		monster.setSelectorLocaion("#JobViewHeader > header > div.heading > h2");
		monster.setSelectorPosition("next > time");
		monster.setSelectorRefNumber("next > time");
		monster.setSelectorSalary("next > time");
		monster.setUrl("https://www.monster.com/jobs/q-administrative-jobs?stpage=1");
		jobSites.add(monster);

		return jobSites;
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

	/**
	 * Tries to get text from {@link Document}. If no success returns empty string
	 * 
	 * @param jobDoc
	 * @param selector
	 * @return
	 */
	public static String tryToGetStringFromJsoupDocument(Document jobDoc, String selector) {
		String result = "";
		try {
			Elements select = jobDoc.select(selector);
			result = select.text();
		} catch (Exception e) {
			// ignored
		}
		return result;
	}

	/**
	 * Tries to get text from {@link WebElement}. If no success returns empty string
	 * 
	 * @param cssSelector The selector
	 * @return text from element or empty string.
	 */
	public static String tryToGetTextFromWebElementBycssSelector(WebDriver driver, String cssSelector) {
		String result = "";
		try {
			WebElement element = driver.findElement(By.cssSelector(cssSelector));
			result = element.getText();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// ignored
		}
		return result;
	}
}
