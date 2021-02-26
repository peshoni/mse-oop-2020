package com.mse.db.pharma.data.transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mse.db.pharma.data.item.Item;
import com.mse.db.pharma.utils.AtomicIndex;

public class OrderLine extends AtomicIndex {
	private long orderId;
	private Item item;
	private double quantity;
	private double price;

	public OrderLine(long orderId, Item item, double quantity) {
		this.orderId = orderId;
		this.item = item;
		this.quantity = quantity;
		this.price = item.getPrice() * quantity;
	}

	public OrderLine() {
	}

	public String getItemSku() {
		return item.getSku();
	}

	public double getLinePrice() {
		return this.quantity * item.getSalePrice();
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderLines{" + "orderId=" + orderId + ", item=" + item + ", quantity=" + quantity + ", price=" + price
				+ '}';
	}

	public void decorateStatement(PreparedStatement statement) throws SQLException {
		statement.setLong(index.getAndIncrement(), orderId);
		statement.setString(index.getAndIncrement(), item.getSku());
		statement.setDouble(index.getAndIncrement(), quantity);
		statement.setDouble(index.getAndIncrement(), price);
	}
}
