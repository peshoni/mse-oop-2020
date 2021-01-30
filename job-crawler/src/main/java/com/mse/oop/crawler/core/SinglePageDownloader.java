/**
 * 
 */
package com.mse.oop.crawler.core;

/**
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class SinglePageDownloader implements Downloader {
	private RandomTimeWorker worker;

	public SinglePageDownloader(int maxCalls, Timeouts timeoutRange, int limitJobPerRequest) {
		worker = new RandomTimeWorker(maxCalls, timeoutRange, limitJobPerRequest);
	}

	@Override
	public void downloadJobsPosistions() {
		System.out.println("Single page downloader");
		this.worker.run();
	}

}
//System.setProperty("webdriver.chrome.driver", "C:\\tmp\\selenium\\chromedriver.exe");
//ChromeOptions options = new ChromeOptions();
//options.addArguments("--headless");
//options.addArguments("--disable-gpu");
//options.addArguments("--window-size=1400,800");
//WebDriver driver = new ChromeDriver(options);
//driver.get("https://www.monster.com/jobs/q-administrative-jobs?jobid=f67acfbc-bfff-46bf-9c8f-f8e70b3b3721");
//WebElement findElement = driver.findElement(
//		By.cssSelector("#JobDescription > table:nth-child(1) > tbody > tr > td > div > b:nth-child(1)"));
//System.out.println(findElement.getText());