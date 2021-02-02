/**
 * 
 */
package com.mse.oop.crawler.core;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.mse.oop.crawler.gui.MainController;
import com.mse.oop.crawler.models.JobPossition;
import com.mse.oop.crawler.models.JobSite;
import com.mse.oop.crawler.utils.CrawlerUtil;

/**
 * USed state design pattern - works according {@link Timeouts} state
 * 
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public class MultiPageWorker implements Runnable {
	private JobSite site;
	private Timeouts timeout;
	private boolean isAlive;
	private Queue<JobPossition> queue = new LinkedList<JobPossition>();
	private ArrayList<JobPossition> allPossition = new ArrayList<JobPossition>(1000);
	private int fetchedItemsCounter = 0;
	private int pageReadedCounter = 0;

	private int allItems;
	private int itemsPerPage;
	private int itemsPerTransaction;
	private int pageLimit;

	private MainController parent;

	public MultiPageWorker(JobSite site, int allItems, int itemsPerPage, int itemsPerTransaction, int pageLimit,
			Timeouts timeout) {
		super();
		this.site = site;
		this.timeout = timeout;
		this.allItems = allItems;
		this.itemsPerPage = itemsPerPage;
		this.itemsPerTransaction = itemsPerTransaction;
		this.pageLimit = pageLimit;

	}

	@Override
	public void run() {
		this.processSite();
	}

	private void processSite() {
		String url = site.getUrl();
		System.out.println(url);

		for (int i = 0; i < allItems; i += itemsPerPage) {
			// read page - build URL
			Document document = null;
			try {
				document = Jsoup.connect(url + i).get();
				System.out.println("Doc is here");
			} catch (Exception e) {
				e.printStackTrace();

				isAlive = false;
				break;
			}

			System.out.println("From item : " + i + " to: " + (i + itemsPerPage));
			Elements jobLink = document.select(".joblink");
			System.out.println("Size: " + jobLink.size());
			// Read items from page and put in queue - for example 15
			for (int j = 0; j < itemsPerPage; j++) {

//				if (j == 3) {
//					throw new RuntimeException("Stop here");
//				}

				if (fetchedItemsCounter >= allItems) {
					this.emptyingTheQueue(true);
					break;
				}

				String attr = jobLink.get(j).attr("href");
				String link = "https://www.jobs.bg/" + attr;

				JobPossition possition;
				try {
					// Document d = new Document("Doc");
					Document jobDoc = Jsoup.connect(link).get();
					possition = buildJobPossitionFromElement(jobDoc);
					possition.setLink(link);
					this.parent.showNewArrived(possition.toString());
					queue.add(possition);
					fetchedItemsCounter++;

				} catch (IOException e1) {

					e1.printStackTrace();
				}

				try {
					int randomNumberInMilliseconds = this.timeout.getRandomNumberInInterval();
					System.out.println(Thread.currentThread().getName() + " Interval: " + randomNumberInMilliseconds);
					Thread.sleep(randomNumberInMilliseconds);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("Sleep failed");
				}
			}

			// remove items according transaction limit
			this.emptyingTheQueue(false);
			// if more than the transaction limit has accumulated in the queue
			if (queue.size() >= itemsPerTransaction) {
				this.emptyingTheQueue(false);
			}

			if (++pageReadedCounter >= pageLimit) {
				// finish with this page
				this.emptyingTheQueue(true);
				break;
			}

		}
		isAlive = false;
		System.out.println("Done. Pages: " + pageReadedCounter + ", readed items: " + allPossition.size());
		System.out.println("In queue: " + queue.size());
	}

	private JobPossition buildJobPossitionFromElement(Document jobDoc) {
		JobPossition jobPossition = new JobPossition();
		jobPossition.setDownloadedAt(Timestamp.valueOf(LocalDateTime.now()));
		Elements select = jobDoc.select(site.getSelectorPossition());
		jobPossition.setPossition(CrawlerUtil.tryToGetString(select.text()));
		select = jobDoc.select(site.getSelectorLocaion());
		jobPossition.setLocation(CrawlerUtil.tryToGetString(select.text()));
		select = jobDoc.select(site.getSelectorRefNumber());
		jobPossition.setRefNumber(CrawlerUtil.tryToGetString(select.text()));
		select = jobDoc.select(site.getSelectorSalary());
		jobPossition.setSalary(CrawlerUtil.tryToGetString(select.text()));
		select = jobDoc.select(site.getSelectorJobDescription());
		jobPossition.setDescription(CrawlerUtil.tryToGetString(select.text()));

		return jobPossition;
	}

	/**
	 * A method for emptying the queue.
	 * 
	 * @param all
	 */
	private void emptyingTheQueue(boolean all) {
		if (all) {
			while (queue.size() > 0) {
				JobPossition pos = queue.poll();
				if (pos != null) {
					allPossition.add(pos);
				}
			}
		} else {
			for (int j = 0; j < itemsPerTransaction; j++) {
				JobPossition pos = queue.poll();
				if (pos != null) {
					allPossition.add(pos);
				}
			}
		}
	}

	public int getFetchedItemsCounter() {
		return fetchedItemsCounter;
	}

	public int getPageReadedCounter() {
		return pageReadedCounter;
	}

	public void setParent(MainController parent) {
		this.parent = parent;
	}

}
