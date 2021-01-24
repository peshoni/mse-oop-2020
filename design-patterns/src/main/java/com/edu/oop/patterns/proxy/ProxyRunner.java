/**
 * 
 */
package com.edu.oop.patterns.proxy;

/**
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class ProxyRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Internet proxy = new ProxyInternet();
		proxy.connectTo("www.google.com");
	}

}
