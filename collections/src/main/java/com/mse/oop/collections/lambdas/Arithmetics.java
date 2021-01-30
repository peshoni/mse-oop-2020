package com.mse.oop.collections.lambdas;

@FunctionalInterface
public interface Arithmetics<T> {

	T calculate(T firstNumber, T secondNumber);

}
