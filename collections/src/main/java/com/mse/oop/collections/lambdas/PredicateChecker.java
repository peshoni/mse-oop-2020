/**
 * 
 */
package com.mse.oop.collections.lambdas;

import java.util.function.Predicate;

/**
 * @author Petar Ivanov - petarivanovgap@gmail.com/pesho02@abv.bg
 *
 */
public class PredicateChecker {

	private static Predicate<Integer> isNegative = n -> (n < 0);

	private static Predicate<String> isCapitalized = str -> {
		char first = str.charAt(0);
		return 65 <= first && first <= 90;
	};

	private static Arithmetics<Integer> sum = (firstNumber, secondNumber) -> (firstNumber + secondNumber);
	private static Arithmetics<Float> sumFloat = (firstNumber, secondNumber) -> (firstNumber + secondNumber);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(isNegative.test(5));
		System.out.println(isCapitalized.test("Brada"));
		System.out.println(sum.calculate(2, 2));
		System.out.println(sumFloat.calculate(2.2f, 2.2f));
	}

}
