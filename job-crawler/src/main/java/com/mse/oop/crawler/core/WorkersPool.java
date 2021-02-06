package com.mse.oop.crawler.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Used Observer design pattern
 * 
 * 
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public class WorkersPool {
	private List<Downloader> workers;

	public WorkersPool() {
		this.workers = new ArrayList<Downloader>();
	}

	/**
	 * Appends the {@link Downloader} to the end of this list.
	 * 
	 * @param worker
	 * @return
	 */
	public boolean addWorker(Downloader worker) {
		return this.workers.add(worker);
	}

	public void runAll() {
		workers.forEach(e -> e.downloadJobsPosistions());
	}
}
