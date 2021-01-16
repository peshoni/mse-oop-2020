package com.edu.oop.threads;

public class CounterThread implements Runnable {
	int counter = 0;

	@Override
	public void run() {

		while (counter < 50) {
			// counter++;

			System.out.println("count: " + counter++);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("counter: " + counter);

	}

}
