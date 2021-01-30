package com.mse.pesho.crawler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.edu.crawler.core.RandomTimeWorker;
import com.edu.crawler.core.Timeouts;

public class TestCrawler {

	@Test
	public void testWorkerCouter() {
		RandomTimeWorker worker = new RandomTimeWorker(10, Timeouts.SMALL,5);
		Thread thr = new Thread(worker);
		thr.start();
		while (thr.isAlive()) {

		}
		assertEquals(11, worker.getCallsCounter());
	}

	// @Test // (expected = InterruptedException.class)
	public void testWorkerCouter2() {
		RandomTimeWorker worker = new RandomTimeWorker(10, Timeouts.SMALL,5);
		Thread thr = new Thread(worker);
		thr.start();
		thr.interrupt();
	}

}
