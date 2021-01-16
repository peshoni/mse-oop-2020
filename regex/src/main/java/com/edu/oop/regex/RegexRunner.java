/**
 * 
 */
package com.edu.oop.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class RegexRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("[a]{2}");

		Matcher matcher = pattern.matcher("8");
		System.out.println(matcher.matches());
	}

}
