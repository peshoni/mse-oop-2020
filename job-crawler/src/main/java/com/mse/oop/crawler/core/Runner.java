/**
 *
 */
package com.mse.oop.crawler.core;

import java.util.List;

import com.mse.oop.crawler.models.JobSite;
import com.mse.oop.crawler.utils.CrawlerUtil;

/**
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class Runner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// CrawlerApp.main(args);

//		private int itemsPerTransaction;// = 10;
//		private int itemsPerPage;// = 15;
//		private int pageLititation;// = 10;
//		private int allItems;// = 6567;

		List<JobSite> sites = CrawlerUtil.getJobSitesCollection();
		JobSite jobsBg = sites.get(0);

		MultiPageDownloader downloader = new MultiPageDownloader(jobsBg, Timeouts.MIDDLE, 10, 20);
		downloader.downloadJobsPosistions();

//		MultiPageWorker paginator = new MultiPageWorker(6567, 15, 10, 2, Timeouts.SMALL);
//		paginator.run();
		// paginator.processSite();
		// paginator.printPossitions();
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
//for (int i = 0; i < 60; i += 15) {
//	Document document = Jsoup.connect("https://www.jobs.bg/front_job_search.php?frompage=0").get();
//	Elements select = document.select(".joblink");
//	select.forEach(element -> {
//		String attr = element.attr("href");
//		String jobUrl = "https://www.jobs.bg/" + attr;
//		JobDownloader jobDownloader = new JobDownloader(jobUrl);
//		Thread thread = new Thread(jobDownloader);
//		thread.start();
//	});
//}
//System.out.println();