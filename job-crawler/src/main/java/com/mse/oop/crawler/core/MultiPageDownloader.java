/**
 * 
 */
package com.mse.oop.crawler.core;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.mse.oop.crawler.gui.MainController;
import com.mse.oop.crawler.models.JobSite;
import com.mse.oop.crawler.utils.CrawlerUtil;

/**
 * 
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public class MultiPageDownloader implements Downloader {
	private JobSite site;
	private MultiPageWorker worker;
	int itemsPerPage = 0;
	int allItems = 0;

	private MainController parent;

	// https://www.theladders.com/jobs/accounting-finance-jobs?sort=ByRelevance&page=400

	/**
	 * Class that fetch items for multi-page site. Uses two type of limitation - per
	 * page or per items.
	 * 
	 * @param site
	 * @param timeout    {@link Timeouts}
	 * @param pageLimit
	 * @param itemsLimit
	 */
	public MultiPageDownloader(JobSite site, Timeouts timeout, int pageLimit) {
		this.site = site;
		try {
			getPaginatorParameters();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		worker = new MultiPageWorker(this.site, allItems, itemsPerPage, 10, 2, Timeouts.SMALL);
	}

	private void getPaginatorParameters() throws IOException {
		Document document = Jsoup.connect(site.getUrl()).get();
		Elements select = document.select(this.site.getSelectorPaginator());
		String jobParams = select.text();
		int[] params = CrawlerUtil.getSitePaginatorParams(jobParams);
		this.itemsPerPage = params[0];
		this.allItems = params[1];
	}

	@Override
	public void downloadJobsPosistions() {
		this.worker.setParent(parent);
		Thread thr = new Thread(worker);
		thr.setDaemon(true);
		thr.start();
	}

	public void setParent(MainController parent) {
		this.parent = parent;
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