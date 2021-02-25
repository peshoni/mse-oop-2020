package com.mse.db.pharma.data.transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import com.mse.db.pharma.data.contragents.Supplier;

public class Supply extends Parent<SupplyLine> {
	private long id;
	private Timestamp suppliedAt;
	private double totalPrice;
	private Supplier supplier;

	public Supply() {
	}

	public Supply(long id, Timestamp suppliedAt, double totalPrice, Supplier supplier) {
		super();
		this.id = id;
		this.suppliedAt = suppliedAt;
		this.totalPrice = totalPrice;
		this.supplier = supplier;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getSuppliedAt() {
		return suppliedAt;
	}

	public void setSuppliedAt(Timestamp suppliedAt) {
		this.suppliedAt = suppliedAt;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getSuplierCompanyName() {
		return this.supplier.getCompanyName();
	}

	public String getSuplierVat() {
		return this.supplier.getVat();
	}

	public void decorateStatement(PreparedStatement statement, boolean create) throws SQLException {
		statement.setLong(index.getAndIncrement(), supplier.getId());
		if (create) {
			statement.setTimestamp(index.getAndIncrement(), Timestamp.from(Calendar.getInstance().toInstant()));
		} else {
			statement.setTimestamp(index.getAndIncrement(), suppliedAt);
		}
		statement.setDouble(index.getAndIncrement(), totalPrice);
		if (!create) {
			statement.setLong(index.getAndIncrement(), id);
		}
	}

	@Override
	public String toString() {
		return "Supply [id=" + id + ", suppliedAt=" + suppliedAt + ", totalPrice=" + totalPrice + ", supplier="
				+ supplier + ", lines=" + super.toString() + "]";
	}
}
