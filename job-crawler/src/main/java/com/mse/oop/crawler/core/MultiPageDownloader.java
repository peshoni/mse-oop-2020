/**
 * 
 */
package com.mse.oop.crawler.core;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.mse.oop.crawler.models.JobSite;
import com.mse.oop.crawler.utils.CrawlerUtil;

/**
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class MultiPageDownloader implements Downloader {
	private JobSite site;
	private MultiPageWorker worker;
	int itemsPerPage = 0;
	int allItems = 0;

	// https://www.theladders.com/jobs/accounting-finance-jobs?sort=ByRelevance&page=400

	public MultiPageDownloader(JobSite site, Timeouts timeout, int pageLimit, int itemsLimit) {
		this.site = site;
		try {
			getSiteData();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		intiWorker();

	}

	private void getSiteData() throws IOException {
		Document document = Jsoup.connect("https://www.jobs.bg/front_job_search.php?frompage=0").get();
		// Elements select = document.select(".joblink");
//#search_results_div > table > tbody > tr > td > table > tbody > tr:nth-child(2) > td
		// try {
		// Document document = Jsoup.connect(jobUrl).get();

//		Elements select = document
//				.select("#search_results_div > table > tbody > tr > td > table > tbody > tr:nth-child(2) > td");

		Elements select = document.select(this.site.getSelectorPaginator());

		String jobParams = select.text();
		int[] params = CrawlerUtil.getSitePaginatorParams(jobParams);
		this.itemsPerPage = params[0];
		this.allItems = params[1];

	}

	private void intiWorker() {
		System.out.println("InitWorker");

		// TODO REMOVE THIS--

		System.out.println("Jobs Items: per page" + itemsPerPage + " all: " + allItems);
		worker = new MultiPageWorker(this.site, allItems, itemsPerPage, 10, 2, Timeouts.SMALL);

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