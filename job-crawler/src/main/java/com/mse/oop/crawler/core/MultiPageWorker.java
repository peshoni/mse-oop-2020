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
import com.mse.oop.crawler.models.JobPosition;
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
	private Queue<JobPosition> queue = new LinkedList<JobPosition>();
	private ArrayList<JobPosition> allPosition = new ArrayList<JobPosition>(1000);
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

			Document document = null;
			try {
				document = Jsoup.connect(url + i).get();
			} catch (Exception e) {
				e.printStackTrace();
				isAlive = false;
				break;
			}

			// System.out.println("From item : " + i + " to: " + (i + itemsPerPage));
			Elements jobLink = document.select(site.getRowSelector());
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
				String link = site.getAddress() + "/" + attr;

				JobPosition position;
				try {

					Document jobDoc = Jsoup.connect(link).get();
					position = buildJobPositionFromElement(jobDoc);
					position.setLink(link);

					this.parent.showNewArrived(position);// .toString());

					queue.add(position);
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
		System.out.println("Done. Pages: " + pageReadedCounter + ", readed items: " + allPosition.size());
		System.out.println("In queue: " + queue.size());
	}

	private JobPosition buildJobPositionFromElement(Document jobDoc) {
		JobPosition jobPosition = new JobPosition();
		jobPosition.setDownloadedAt(Timestamp.valueOf(LocalDateTime.now()));
		Elements select = jobDoc.select(site.getSelectorPosition());
		jobPosition.setPosition(CrawlerUtil.tryToGetString(select.text()));
		select = jobDoc.select(site.getSelectorLocaion());
		jobPosition.setLocation(CrawlerUtil.tryToGetString(select.text()));
		select = jobDoc.select(site.getSelectorRefNumber());
		jobPosition.setRefNumber(CrawlerUtil.tryToGetString(select.text()));
		select = jobDoc.select(site.getSelectorSalary());
		jobPosition.setSalary(CrawlerUtil.tryToGetString(select.text()));
		select = jobDoc.select(site.getSelectorJobDescription());
		jobPosition.setDescription(CrawlerUtil.tryToGetString(select.text()));

		return jobPosition;
	}

	/**
	 * A method for emptying the queue.
	 * 
	 * @param all
	 */
	private void emptyingTheQueue(boolean all) {
		if (all) {
			while (queue.size() > 0) {
				JobPosition pos = queue.poll();
				if (pos != null) {
					allPosition.add(pos);
				}
			}
		} else {
			for (int j = 0; j < itemsPerTransaction; j++) {
				JobPosition pos = queue.poll();
				if (pos != null) {
					allPosition.add(pos);
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
