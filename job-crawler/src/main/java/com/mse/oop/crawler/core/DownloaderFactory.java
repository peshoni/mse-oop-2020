/**
 * 
 */
package com.mse.oop.crawler.core;

/**
 * Using Factory design pattern
 * 
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class DownloaderFactory {
	public Downloader getDownloader(Timeouts timeout, boolean isForMultiPage, int maxCalls,int limitJobPerRequest) {
		return isForMultiPage ? new SinglePageDownloader(maxCalls, timeout,limitJobPerRequest)
				: new MultiPageDownloader(maxCalls, timeout,limitJobPerRequest);

	}
}
