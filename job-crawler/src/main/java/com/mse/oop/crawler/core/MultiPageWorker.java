/**
 * 
 */
package com.mse.oop.crawler.core;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.mse.oop.crawler.models.JobPossition;
import com.mse.oop.crawler.models.JobSite;

/**
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
	// DELETE THIS
	AtomicInteger idGen = new AtomicInteger(1);

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

		for (int i = 0; i < allItems; i += itemsPerPage) {

			// read page - build URL
			Document document = null;
			try {
				document = Jsoup.connect("https://www.jobs.bg/front_job_search.php?frompage=" + i).get();
			} catch (Exception e) {
				isAlive = false;
			}

			System.out.println("From item : " + i + " to: " + (i + itemsPerPage));
			// Read items from page and put in queue - for example 15
			for (int j = 0; j < itemsPerPage; j++) {
				if (fetchedItemsCounter >= allItems) {
					this.emptyingTheQueue(true);
					break;
				}

				Elements select = document.select(".joblink");
				String attr = select.get(j).attr("href");
				String jobUrl = "https://www.jobs.bg/" + attr;
				System.out.println(jobUrl);

				// READ AND BUILD HERE
				queue.add(read());

				fetchedItemsCounter++;

				int randomNumberInMilliseconds = this.timeout.getRandomNumberInInterval();
				System.out.println(Thread.currentThread().getName() + " Interval: " + randomNumberInMilliseconds);
				try {

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

	public JobPossition read() {
		JobPossition pos = new JobPossition();
		pos.setId(idGen.getAndIncrement());
		pos.setLabel(new Timestamp(new Date().getTime()).toString());
		return pos;
	}

	public void printPossitions() {
		this.allPossition.forEach(pos -> System.out.println(pos.getId()));
	}

	public int getFetchedItemsCounter() {
		return fetchedItemsCounter;
	}

	public int getPageReadedCounter() {
		return pageReadedCounter;
	}

}
