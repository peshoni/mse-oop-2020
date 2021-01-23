/**
 * 
 */
package com.edu.oop.infosys.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public class InfoSysUtil {
	private InfoSysUtil() {

	}

	/**
	 * Rounding double to given scale using RoundingMode.HALF_UP
	 * 
	 * @param value
	 * @param places
	 * @return
	 */
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();
		try {
			BigDecimal bd = new BigDecimal(value);
			bd = bd.setScale(places, RoundingMode.HALF_UP);
			return bd.doubleValue();
		} catch (Exception e) {
			return 0.0;
		}

	}
}
