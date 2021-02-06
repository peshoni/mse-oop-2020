/**
 * 
 */
package com.mse.oop.crawler.core;

import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mse.oop.crawler.gui.MainController;
import com.mse.oop.crawler.models.JobPosition;
import com.mse.oop.crawler.models.JobSite;
import com.mse.oop.crawler.utils.CrawlerUtil;

/**
 * 
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public class SinglePageWorker extends Thread implements Downloader {
	private MainController parent;
	private JobSite site;
	private ArrayList<JobPosition> allPosition = new ArrayList<JobPosition>(200);
	private int downloadLimit;
	private WebDriver driver;
	private int fetchedItemsCounter = 0;

	public SinglePageWorker(WebDriver driver, MainController parent, JobSite site) {
		super();
		this.driver = driver;
		this.parent = parent;
		this.site = site;
		this.downloadLimit = this.site.getDownloadLimit();
		this.setDaemon(true);
		this.setName("Thread: " + this.site.getName());
	}

	@Override
	public void downloadJobsPosistions() {
		System.out.println("Single page downloader");
		this.start();
	}

	@Override
	public void run() {
		try {
			this.processSite();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Process site data according {@link JobSite} state and his selectors. Builds
	 * {@link JobPosition} objects and put them in collection.
	 */
	private void processSite() {
		/// https://www.monster.com/jobs/q-administrative-jobs?stpage=1&page=2
		driver.get(site.getUrl());

		java.util.List<WebElement> jobLinks = driver.findElements(By.cssSelector(site.getRowSelector()));
		for (WebElement eachAnchorElem : jobLinks) {
			String jobLink = eachAnchorElem.getAttribute("href");

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", eachAnchorElem);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				continue;
			}

			JobPosition position = new JobPosition();
			position.setDownloadedAt(new Date());
			position.setSiteName(site.getName());

			position.setLink(jobLink);
			position.setLocation(
					CrawlerUtil.tryToGetTextFromWebElementBycssSelector(driver, site.getSelectorLocaion()));
			position.setPosition(
					CrawlerUtil.tryToGetTextFromWebElementBycssSelector(driver, site.getSelectorJobDescription()));

			parent.showNewArrived(position);
			fetchedItemsCounter++;
			allPosition.add(position);

			if (fetchedItemsCounter >= downloadLimit) {
				System.out.println("DONE");
				break;
			}
			try {
				int randomNumberInMilliseconds = this.site.getTimeoutType().getRandomNumberInInterval();
				System.out.println(Thread.currentThread().getName() + " will waits: " + randomNumberInMilliseconds);
				Thread.sleep(randomNumberInMilliseconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
