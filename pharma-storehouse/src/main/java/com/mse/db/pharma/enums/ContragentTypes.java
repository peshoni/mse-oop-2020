package com.mse.db.pharma.enums;

/**
 * Provides: {@link #SUPPLIER} ,{@link #CUSTOMER} ,{@link #SHIPPER}
 *
 * 
 */
public enum ContragentTypes {
	/**
	 * Suppliers
	 */
	SUPPLIER(1, "доставчици"),
	/**
	 * Customers
	 */
	CUSTOMER(2, "клиенти"),
	/**
	 * Shippers
	 */
	SHIPPER(3, "спедитори");

	private String label;
	private int number;

	private ContragentTypes(int num, String type) {
		this.label = type;
		this.number = num;
	}

	public String toString() {
		return this.label;
	}

	public int getId() {
		return this.number;
	}
}
