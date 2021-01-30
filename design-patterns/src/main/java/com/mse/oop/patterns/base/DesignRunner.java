/**
 * 
 */
package com.mse.oop.patterns.base;

/**
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class DesignRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(SingletonDemo.instance().hashCode());
		System.out.println(SingletonDemo.instance().hashCode());
		System.out.println(SingletonDemo.instance().hashCode());
		System.out.println(SingletonDemo.instance().hashCode());

	}

}
