package com.mse.oop.collections.lists;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mse.oop.collections.lists.Product;
import com.mse.oop.collections.lists.ShoppingList;

public class PriceSortingTest {

	// class under test
	private ShoppingList list = new ShoppingList();
	private Product eggs = new Product("Eggs", 1, 10);
	private Product beer = new Product("Beer", 2, 4);
	private Product yogurt = new Product("Yogurt", 3, 1);

	@Before
	public void init() {
		System.out.println("BEFORE");
		list.addProduct(eggs);
		list.addProduct(beer);
		list.addProduct(yogurt);
	}

	@Test

	public void testSortingByName() {

		List<Product> actual = list.sortByName();
		assertEquals(Arrays.asList(beer, eggs, yogurt), actual);
	}

	@Test
	public void testSortingByPrice() {

		List<Product> actual = list.sortByPrice();
		assertEquals(Arrays.asList(eggs, beer, yogurt), actual);
	}

	@Test
	public void testPrintList() {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);

		list.printList();

		System.setOut(old);
		String actual = baos.toString();

		String expected = "Product [name=Eggs, price=1, quantity=10]" + System.lineSeparator()
				+ "Product [name=Beer, price=2, quantity=4]" + System.lineSeparator()
				+ "Product [name=Yogurt, price=3, quantity=1]" + System.lineSeparator();
		assertEquals(expected, actual);
	}

	@Test
	public void testIsProductContained() {
		assertTrue(list.isProductContained("beer"));
	}

}
