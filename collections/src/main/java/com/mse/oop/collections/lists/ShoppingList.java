package com.mse.oop.collections.lists;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class ShoppingList {
	List<Product> products = new LinkedList<Product>();

	public void addProduct(Product product) {
		products.add(product);

	}

	public List<Product> sortByName() {
		Comparator<Product> productComparatorByName = (p1, p2) -> (p1.getName().compareTo(p2.getName()));
		Collections.sort(products, productComparatorByName);
		return products;
	}

	public void printList() {
		products.forEach(System.out::println);
	}

	public List<Product> sortByPrice() {
		Comparator<Product> productComparatorPrice = (p1, p2) -> (p1.getPrice() - p2.getPrice());
		Collections.sort(products, productComparatorPrice);
		return products;
	}

	public boolean isProductContained(String name) {
		Predicate<Product> predicate = (p -> p.getName().toLowerCase().equals(name.toLowerCase()));
		return products.stream().anyMatch(predicate);
	}
}
