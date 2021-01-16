package com.edu.oop.annotation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AnnoRunner {
	public static void main(String[] args) {

		Fish f1 = new BigFish("whale", 100, true);
		Fish f2 = new MediumFish("tuna", 5, true);
		Fish f3 = new SmallFish("salmon", 20, true);

		List<Fish> fishes = Arrays.asList(f1, f2, f3);

		Comparator<Fish> fishComparator = (fa, fb) -> {
			Order annotationA = (Order) fa.getClass().getAnnotations()[0];
			Order annotationB = (Order) fa.getClass().getAnnotations()[0];
			return annotationA.value() - annotationB.value();
		};
		fishes.sort(fishComparator);

		System.out.println(fishes);
	}
}
