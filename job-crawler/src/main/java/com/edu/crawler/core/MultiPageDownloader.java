/**
 * 
 */
package com.edu.crawler.core;

/**
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class MultiPageDownloader implements Downloader {
	private RandomTimeWorker worker;

	// https://www.theladders.com/jobs/accounting-finance-jobs?sort=ByRelevance&page=400

	public MultiPageDownloader(int maxCalls, Timeouts timeoutRange, int limitJobPerRequest) {
		worker = new RandomTimeWorker(maxCalls, timeoutRange, limitJobPerRequest);
	}

	@Override
	public void downloadJobsPosistions() {
		System.out.println("Multi page downloader");
		this.worker.run();
	}

}
//Document document = Jsoup.connect(jobUrl).get();
//Elements select = document.select("body > table:nth-child(4) > tbody > tr > td > "
//		+ "table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(2) > td > b");
//System.out.println(select.text());
//
//select = document.select("body > table:nth-child(4) > tbody > tr > td > table > tbody > "
//		+ "tr:nth-child(2) > td > table > tbody > tr:nth-child(2) > td > a");
//System.out.println(select.text());
//
//select = document.select("body > table:nth-child(4) > tbody > tr > td > table > tbody > "
//		+ "tr:nth-child(2) > td > table > tbody > tr:nth-child(3) > td");
//System.out.println(select.text());