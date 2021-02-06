/**
 * 
 */
package com.mse.oop.crawler.core;

import org.openqa.selenium.WebDriver;

import com.mse.oop.crawler.gui.MainController;
import com.mse.oop.crawler.models.JobSite;

/**
 * Used Factory design pattern
 * 
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public class DownloaderFactory {
	private DownloaderFactory() {
	}

	public static Downloader getDownloader(MainController parent, JobSite site, WebDriver driver) {
		if (site.getSiteType().toLowerCase().contains("single")) {
			return new SinglePageWorker(driver, parent, site);
		} else if (site.getSiteType().toLowerCase().contains("multi")) {
			return new MultiPageWorker(parent, site);
		} else {
			return null;
		}
	}
}
