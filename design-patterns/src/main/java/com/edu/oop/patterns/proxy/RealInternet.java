package com.edu.oop.patterns.proxy;

public class RealInternet implements Internet {

	@Override
	public void connectTo(String serverHost) {
		System.out.println("connecting to : " + serverHost);

	}

}
