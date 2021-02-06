package com.mse.oop.crawler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mse.oop.crawler.core.DownloaderFactory;
import com.mse.oop.crawler.core.MultiPageWorker;
import com.mse.oop.crawler.core.TimeoutTypes;
import com.mse.oop.crawler.models.JobSite;
import com.mse.oop.crawler.utils.CrawlerUtil;

public class TestCrawler {

	@Test
	public void testMultiPageThread() {
		JobSite js = CrawlerUtil.getJobSitesCollection().get(0);
		js.setTimeoutType(TimeoutTypes.TEST);
		MultiPageWorker multiPage = (MultiPageWorker) DownloaderFactory.getDownloader(null, js, null);
		multiPage.downloadJobsPosistions();
		while (multiPage.isAlive()) {
		}
		assertEquals(multiPage.getSite().getDownloadLimit(), multiPage.getFetchedItemsCounter());
	}

	@Test
	public void testTwoMultiPageThreads() {
		JobSite js = CrawlerUtil.getJobSitesCollection().get(0);
		js.setTimeoutType(TimeoutTypes.TEST);
		MultiPageWorker multiPage = (MultiPageWorker) DownloaderFactory.getDownloader(null, js, null);
		multiPage.downloadJobsPosistions();

		JobSite js2 = CrawlerUtil.getJobSitesCollection().get(1);
		js2.setTimeoutType(TimeoutTypes.TEST);
		MultiPageWorker multiPageSecond = (MultiPageWorker) DownloaderFactory.getDownloader(null, js2, null);
		multiPageSecond.downloadJobsPosistions();

		while (multiPage.isAlive()) {
		}
		while (multiPageSecond.isAlive()) {

		}
		assertEquals((multiPage.getSite().getDownloadLimit() + multiPageSecond.getSite().getDownloadLimit()),
				(multiPage.getFetchedItemsCounter() + multiPageSecond.getFetchedItemsCounter()));
	}

	@Test
	public void testEnumeratorGetRandomRange() {
		int smallRange = TimeoutTypes.valueOf(TimeoutTypes.FAST.getTypeId()).getRandomNumberInInterval();
		assertTrue(smallRange >= 500 && smallRange <= 3000);
	}
}
