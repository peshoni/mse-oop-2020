/**
 * 
 */
package com.mse.oop.crawler.core;

import java.util.LinkedList;
import java.util.Queue;

import com.mse.oop.crawler.models.JobPossition;

/**
 * //state design pattern - works according Timeouts state
 * 
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class RandomTimeWorker implements Runnable {
	private Timeouts interval;
	private int callsCounter = 0;
	private int maxCalls = 0;
	private boolean isAlive;
	private int limitPerRequest = 1;
	private Queue<JobPossition> queue;

	public RandomTimeWorker(int maxCalls, Timeouts interval, int limitPerRequest) {
		this.maxCalls = maxCalls;
		this.interval = interval;
		this.limitPerRequest = limitPerRequest;
		queue = new LinkedList<JobPossition>();
	}

//TODO - find data in THREAD's folder
	public void run() {
		isAlive = true;
		while (isAlive) {
			System.out.println(Thread.currentThread().getName() + " Makes request.............");
			// request
			// process request result

			// build job/jobs from response
			JobPossition job = buildJobPostionObjetc();
			// System.out.println(job);
			queue.add(job);
			int queueSize = queue.size();
			if (queueSize == limitPerRequest) {
				for (int i = 0; i < queueSize; i++) {
					System.out.println(queue.poll());
				}
			}

			// gogo
			// if success
			callsCounter++;
			if (callsCounter > maxCalls) {
				System.out.println("Max calls reached: " + callsCounter + " " + maxCalls + " kill this..");
				isAlive = false;
				continue;
			}

			int randomNumber = this.interval.getRandomNumberInInterval();

			System.out.println(Thread.currentThread().getName() + " Interval: " + randomNumber);
			try {
				Thread.sleep(randomNumber);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Sleep failed");
			}
		}
	}



	public int getCallsCounter() {
		return callsCounter;
	}
	private JobPossition buildJobPostionObjetc() {
		JobPossition possiton = new JobPossition();
		possiton.setLabel("job label");
		possiton.setRefNumber("Ref number");
		possiton.setLocation("location");
		possiton.setDescription("description");
		possiton.setLink("link");
		possiton.setSalary("salary");
		return possiton;
	}

}
