package com.mse.db.pharma.data.contragents;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Supplier extends Contragent {
	private String companyName;
	private String vat;

	public Supplier() {
	}

	public Supplier(Integer id, String phone, String email, String bankNum, String creditCard, Address address,
			String companyName, String vat) {
		super(id, phone, email, bankNum, creditCard, address);
		this.companyName = companyName;
		this.vat = vat;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	@Override
	public String toString() {
		return "Supplier{" + super.toString() + "companyName='" + companyName + '\'' + ", vat='" + vat + '\'' + '}';
	}

	public void decorateStatement(PreparedStatement preparedStatement, boolean create) throws SQLException {
		super.decorateStatement(preparedStatement, create);
		preparedStatement.setString(index.getAndIncrement(), companyName);
		preparedStatement.setString(index.getAndIncrement(), vat);
		if (!create) {
			preparedStatement.setLong(index.getAndIncrement(), getId());
		}
	}
}
