package com.edu.serialize;

public class SerializeRunner {
	public static void main(String[] args) {
		String fileName = "F:\\fish.txt";

		FishSerializer.serialize(new Fish("barakuda", 8, true), fileName);

		Fish f = FishSerializer.deserialize(fileName);
		System.out.println(f);

	}
}
