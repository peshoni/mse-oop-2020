package com.mse.db.pharma.enums;

public enum ItemTypes {
	/**
	 * medicine
	 */
	MEDICINE(1, "лекарство"),
	/**
	 * Customers
	 */
	SUPPLEMENT(2, "хранителни добавки");

	private String label;
	private int number;

	private ItemTypes(int num, String type) {
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
