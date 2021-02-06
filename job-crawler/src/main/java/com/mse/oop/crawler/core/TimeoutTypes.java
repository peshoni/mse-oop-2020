/**
 * 
 */
package com.mse.oop.crawler.core;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Random;

/**
 *
 * Timeouts that can be used {@link #TEST} {@link #FAST} {@link #SLOWER}
 * {@link #SLOWEST}
 * 
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public enum TimeoutTypes {
	/**
	 * This is only for test purposes! Method for get random number will always
	 * returns ZERO.
	 */
	TEST(0, 0, 0),
	/**
	 * Type 1 with random value in range : 500 - 2000
	 */
	FAST(1, 500, 2000),
	/**
	 * Type 2 with random value in range : 2000 - 5000
	 */
	SLOWER(2, 2000, 5000),
	/**
	 * Type 3 with random value in range : 5000 - 8000
	 */
	SLOWEST(3, 5000, 8000);

	private int typeId;
	private int min;
	private int max;
	private final static Map<Integer, TimeoutTypes> timeoutsMap = stream(TimeoutTypes.values())
			.collect(toMap(timeout -> timeout.typeId, timeout -> timeout));

	private TimeoutTypes(int type, int min, int max) {
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
		if ((min + max) == 0) {
			return 0;
		}
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}

	public int getTypeId() {
		return this.typeId;
	}

	public static TimeoutTypes valueOf(int typeId) {
		return timeoutsMap.get(typeId);
	}

}
