/**
 * 
 */
package com.edu.oop.threads;

/**
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class ThreadRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CounterThread thread = new CounterThread();
		// thread.run();
		Thread thr = new Thread(thread);
		thr.start();
		System.out.println("End");
	}

}
