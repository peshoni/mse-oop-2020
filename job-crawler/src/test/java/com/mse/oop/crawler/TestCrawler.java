package com.mse.oop.crawler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mse.oop.crawler.core.MultiPageWorker;
import com.mse.oop.crawler.core.Timeouts;
import com.mse.oop.crawler.utils.CrawlerUtil;

public class TestCrawler {

	@Test
	public void testMultiPagePaginator() {

		MultiPageWorker paginator = new MultiPageWorker(CrawlerUtil.getJobSitesCollection().get(0), 6565, 15, 10, 3,
				Timeouts.TEST);
		Thread thr = new Thread(paginator);
		thr.start();
		while (thr.isAlive()) {
		}
		assertEquals(45, paginator.getFetchedItemsCounter());
		assertEquals(3, paginator.getPageReadedCounter());
	}

	// @Test
	public void testMultiPagePaginatorWithMoreThanAvailablePage() {
		int allItems = 6565;
		MultiPageWorker paginator = new MultiPageWorker(CrawlerUtil.getJobSitesCollection().get(0), allItems, 15, 10,
				700, Timeouts.TEST);
		Thread thr = new Thread(paginator);
		thr.start();
		while (thr.isAlive()) {
		}
		assertEquals(allItems, paginator.getFetchedItemsCounter());
	}

	@Test
	public void testEnumeratorGetRandomRange() {
		int smallRange = Timeouts.valueOf(Timeouts.SMALL.getTypeId()).getRandomNumberInInterval();
		assertTrue(smallRange >= 500 && smallRange <= 3000);
	}

//	// @Test
//	public void testWorkerCouter() {
//		RandomTimeWorker worker = new RandomTimeWorker(10, Timeouts.SMALL, 5);
//		Thread thr = new Thread(worker);
//		thr.start();
//		while (thr.isAlive()) {
//
//		}
//		assertEquals(11, worker.getCallsCounter());
//	}
//
//	// @Test // (expected = InterruptedException.class)
//	public void testWorkerCouter2() {
//		RandomTimeWorker worker = new RandomTimeWorker(10, Timeouts.SMALL, 5);
//		Thread thr = new Thread(worker);
//		thr.start();
//		thr.interrupt();
//	}

}
