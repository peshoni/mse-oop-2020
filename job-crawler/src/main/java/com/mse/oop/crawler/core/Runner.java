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

		// WorkersPool pool = new WorkersPool();
//		DownloaderFactory factory = new DownloaderFactory();
//		Downloader downloader = factory.getDownloader(Timeouts.SMALL, true, 10, 5);
//		downloader.downloadJobsPosistions(); 
//		Downloader downloader2 = factory.getDownloader(Timeouts.MIDDLE, false, 5);
//		downloader2.downloadJobsPosistions(); 
//		RandomTimeWorker worker = new RandomTimeWorker(10, Timeouts.SMALL);
//		Thread thr = new Thread(worker, "worker 1");
		// pool.addWorker(worker);
//		thr.start();
//		RandomTimeWorker worker2 = new RandomTimeWorker(10, Intervals.MIDDLE);
//		Thread thr2 = new Thread(worker2);
//		thr2.start();

	}
}
