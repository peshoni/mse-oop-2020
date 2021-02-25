package com.mse.db.pharma.data.transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import com.mse.db.pharma.data.contragents.Address;
import com.mse.db.pharma.data.contragents.Customer;

/**
 * @author ivelin.dimitrov
 */
public class Order extends Parent<OrderLine> {
	private long id;
	private Customer customer;
	private Timestamp createdAt;
	private long shippingId;
	private Timestamp shippingTime;
	private Address address;
	private double totalPrice;

	public Order() {

	}

	public Order(long id, Customer customer, long shippingId, Timestamp shippingTime, Address address,
			double totalPrice) {
		this.id = id;
		this.customer = customer;
		this.shippingId = shippingId;
		this.shippingTime = shippingTime;
		this.address = address;
		this.totalPrice = totalPrice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public long getShippingId() {
		return shippingId;
	}

	public void setShippingId(long shippingId) {
		this.shippingId = shippingId;
	}

	public Timestamp getShippingTime() {
		return shippingTime;
	}

	public void setShippingTime(Timestamp shippingTime) {
		this.shippingTime = shippingTime;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void decorateStatement(PreparedStatement statement, boolean create) throws SQLException {
		statement.setLong(index.getAndIncrement(), customer.getId());
		if (create) {
			statement.setTimestamp(index.getAndIncrement(), Timestamp.from(Calendar.getInstance().toInstant()));
		}
		statement.setLong(index.getAndIncrement(), shippingId);
		statement.setTimestamp(index.getAndIncrement(), shippingTime);
		statement.setString(index.getAndIncrement(), address.getCity());
		statement.setString(index.getAndIncrement(), address.getStreet());
		statement.setString(index.getAndIncrement(), address.getProvince());
		statement.setString(index.getAndIncrement(), address.getZipCode());
		statement.setDouble(index.getAndIncrement(), totalPrice);
		if (!create) {
			statement.setLong(index.getAndIncrement(), id);
		}
	}

	public String getCustomerFirstName() {
		return customer.getFirstName();
	}

	public String getCustomerLastName() {
		return customer.getLastName();
	}

	public String getCustomerCity() {
		return address.getCity();
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", customer=" + customer + ", createdAt=" + createdAt + ", shippingId=" + shippingId
				+ ", shippingTime=" + shippingTime + ", address=" + address + ", totalPrice=" + totalPrice + ", lines="
				+ super.toString() + "]";
	}
}
