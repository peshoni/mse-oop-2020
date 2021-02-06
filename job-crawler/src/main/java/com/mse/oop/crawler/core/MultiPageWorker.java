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

import javafx.scene.control.TableView;

/**
 * Used state design pattern - works according {@link TimeoutTypes} state
 * 
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public class MultiPageWorker extends Thread implements Downloader {
	private MainController parent;
	private JobSite site;
	private Queue<JobPosition> queue = new LinkedList<JobPosition>();
	private ArrayList<JobPosition> allPosition = new ArrayList<JobPosition>(200);
	private int allItems;
	private int itemsPerPage;
	private int downloadLimit;
	private int fetchedItemsCounter = 0;
	private int pageReadedCounter = 0;

	/**
	 * Class that fetch data for job positions for given `multi-page` sites. Uses
	 * limitation per download items.
	 * 
	 * @param parent
	 * @param site
	 * 
	 */
	public MultiPageWorker(MainController parent, JobSite site) {
		super();
		this.parent = parent;
		this.site = site;
		this.downloadLimit = this.site.getDownloadLimit();
		this.setDaemon(true);
		this.setName("Thread: " + this.site.getName());
	}

	@Override
	public void run() {
		int[] paginatorParameters = getPaginatorParameters();
		this.allItems = paginatorParameters[1];
		this.itemsPerPage = paginatorParameters[0];
		this.processSite();
	}

	/**
	 * {@link TableView}
	 */
	private void processSite() {
		String url = site.getUrl();

		for (int i = 0; i < allItems; i += itemsPerPage) {
			Document document = null;
			try {
				String mainUrl = url + i;
				document = Jsoup.connect(mainUrl).get();
			} catch (Exception e) {
				e.printStackTrace();
				CrawlerUtil.showError(e.getMessage());
				break;
			}

			Elements jobLink = document.select(site.getRowSelector());

			// Read items from page and put in queue - for example 15
			for (int j = 0; j < itemsPerPage; j++) {
				if (fetchedItemsCounter >= allItems) {
					this.emptyingTheQueue(true);
					break;
				}

				String attr = jobLink.get(j).attr("href");
				String link = attr.contains("http") ? attr
						: site.getAddress() + (attr.startsWith("/") ? attr : "/" + attr);

				JobPosition position;
				try {
					if (this.site.getTimeoutType().getTypeId() > 0) {
						// open position page
						Document jobDoc = Jsoup.connect(link).get();
						position = buildJobPositionFromElement(jobDoc);
						position.setLink(link);
						position.setSiteName(site.getName());
						/**
						 * Show result in {@link TableView}
						 */
						if (parent != null) {
							this.parent.showNewArrived(position);
						}
					} else {
						// for test purposes
						position = new JobPosition();
					}
					queue.add(position);

					if (++fetchedItemsCounter >= downloadLimit) {
						this.emptyingTheQueue(true);
						break;
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				try {
					int randomNumberInMilliseconds = this.site.getTimeoutType().getRandomNumberInInterval();
					System.out.println(Thread.currentThread().getName() + " will waits: " + randomNumberInMilliseconds);
					Thread.sleep(randomNumberInMilliseconds);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// remove items according transaction limit
			this.emptyingTheQueue(false);
			// if more than the transaction limit has accumulated in the queue
			pageReadedCounter++;
			if (queue.size() >= downloadLimit) {
				this.emptyingTheQueue(false);
			}

			if (fetchedItemsCounter >= downloadLimit) {
				this.emptyingTheQueue(true);
				break;
			}
		}
		System.out.println("Done. Pages: " + pageReadedCounter + ", readed items: " + allPosition.size()
				+ ", in queue: " + queue.size());
	}

	private JobPosition buildJobPositionFromElement(Document jobDoc) {
		JobPosition jobPosition = new JobPosition();
		jobPosition.setDownloadedAt(Timestamp.valueOf(LocalDateTime.now()));
		jobPosition.setPosition(CrawlerUtil.tryToGetStringFromJsoupDocument(jobDoc, site.getSelectorPosition()));
		jobPosition.setLocation(CrawlerUtil.tryToGetStringFromJsoupDocument(jobDoc, site.getSelectorLocaion()));
		jobPosition.setRefNumber(CrawlerUtil.tryToGetStringFromJsoupDocument(jobDoc, site.getSelectorRefNumber()));
		jobPosition.setSalary(CrawlerUtil.tryToGetStringFromJsoupDocument(jobDoc, site.getSelectorSalary()));
		jobPosition
				.setDescription(CrawlerUtil.tryToGetStringFromJsoupDocument(jobDoc, site.getSelectorJobDescription()));
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
			for (int j = 0; j < downloadLimit; j++) {
				JobPosition pos = queue.poll();
				if (pos != null) {
					allPosition.add(pos);
				}
			}
		}
	}

	private int[] getPaginatorParameters() {
		try {
			Document document = Jsoup.connect(site.getUrl()).get();
			Elements select = document.select(this.site.getSelectorPaginator());
			String jobParams = select.text();
			int[] params = CrawlerUtil.getSitePaginatorParams(jobParams);
			return params;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new int[] { 10, 10 };// one position on one page
	}

	public int getFetchedItemsCounter() {
		return fetchedItemsCounter;
	}

	public int getPageReadedCounter() {
		return pageReadedCounter;
	}

	public JobSite getSite() {
		return site;
	}

	@Override
	public void downloadJobsPosistions() {
		this.start();
	}
}
