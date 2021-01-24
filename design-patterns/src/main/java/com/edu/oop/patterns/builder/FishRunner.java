package com.edu.oop.patterns.builder;

public class FishRunner {
	public static void main(String[] args) {
		Fish f = new Fish.Builder().setLength(100).setWeight(20).setIsSaltWater(true).setSpecies("asdasd").build();
	}
}
