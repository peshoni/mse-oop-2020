/**
 * 
 */
package com.edu.crawler.core;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Random;

/**
 *
 * Timeouts that can be used {@link #SMALL} {@link #MIDDLE} {@link #LARGE}
 * 
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public enum Timeouts {
	/**
	 * Type 1 with random value in range : 500ms - 1000ms
	 */
	SMALL(1, 500, 5000),
	/**
	 * Type 3 with random value in range : 1000 - 10000
	 */
	MIDDLE(2, 1000, 10000),
	/**
	 * Type 3 with random value in range : 5000 - 20000
	 */
	LARGE(3, 5000, 20000);

	private int typeId;
	private int min;
	private int max;
	private final static Map<Integer, Timeouts> timeoutsMap = stream(Timeouts.values())
			.collect(toMap(timeout -> timeout.typeId, timeout -> timeout));

	private Timeouts(int type, int min, int max) {
		this.typeId = type;
		this.min = min;
		this.max = max;
	}

	/**
	 * Gets random integer value between minimum and maximum of this enumerator.
	 * 
	 * @return
	 */
	public int getRandomNumberInInterval() {
		return getRandomNumberUsingNextInt(min, max);
	}

	private int getRandomNumberUsingNextInt(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}

	public int getTypeId() {
		return this.typeId;
	}

	public static Timeouts valueOf(int typeId) {
		return timeoutsMap.get(typeId);
	}

}
