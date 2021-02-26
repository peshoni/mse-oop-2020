package com.mse.db.pharma.data.transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mse.db.pharma.data.item.Item;
import com.mse.db.pharma.utils.AtomicIndex;

public class SupplyLine extends AtomicIndex {
	private long id;
	private long supplyId;
	private Item item;
	private int quantity;
	private double price;

	public SupplyLine(long id, long supplyId, Item item, int quantity) {
		this.id = id;
		this.supplyId = supplyId;
		this.item = item;
		this.quantity = quantity;
		this.price = item.getPrice() * quantity;
	}

	public void decorateStatement(PreparedStatement statement) throws SQLException {
		statement.setLong(index.getAndIncrement(), supplyId);
		statement.setString(index.getAndIncrement(), item.getSku());
		statement.setInt(index.getAndIncrement(), quantity);
		statement.setDouble(index.getAndIncrement(), price);
	}

	@Override
	public String toString() {
		return "SupplyLine{" + "id=" + id + ", supplyId=" + supplyId + ", item=" + item + ", quantity=" + quantity
				+ ", price=" + price + '}';
	}

	public SupplyLine() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public long getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(long supplyId) {
		this.supplyId = supplyId;
	}

	public String getItemSku() {
		return item.getSku();
	}

	public double getLinePrice() {
		return this.quantity * price;
	}

	public double getLineSalePrice() {
		return this.quantity * item.getSalePrice();
	}

}
