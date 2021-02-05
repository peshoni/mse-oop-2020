/**
 *
 */
package com.mse.oop.crawler.core;

import com.mse.oop.crawler.gui.CrawlerApp;

/**
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class Runner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CrawlerApp.main(args);

//		List<JobSite> sites = CrawlerUtil.getJobSitesCollection();
//		JobSite jobsBg = sites.get(0);
//		MultiPageDownloader downloader = new MultiPageDownloader(jobsBg, Timeouts.MIDDLE, 1);		
//		downloader.downloadJobsPosistions();

//		MultiPageWorker paginator = new MultiPageWorker(6567, 15, 10, 2, Timeouts.SMALL);
//		paginator.run();

	}
}
