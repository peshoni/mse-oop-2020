package com.edu.oop.patterns.proxy;

import java.util.ArrayList;
import java.util.List;

public class ProxyInternet implements Internet {
	private Internet realInternet = new RealInternet();
	private List<String> bannedSites = new ArrayList<>();

	public ProxyInternet() {
		bannedSites.add("www.google.com");
	}

	@Override
	public void connectTo(String serverHost) {
		if (bannedSites.contains(serverHost)) {
			throw new IllegalAccessError("This site is banned for your country");
		}

		realInternet.connectTo(serverHost);
	}

}
