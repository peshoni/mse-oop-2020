package com.mse.db.pharma.repository;

import static com.mse.db.pharma.utils.StaticQueries.DELETE_ORDER;
import static com.mse.db.pharma.utils.StaticQueries.DELETE_ORDER_LINE;
import static com.mse.db.pharma.utils.StaticQueries.DELETE_SHIPPING;
import static com.mse.db.pharma.utils.StaticQueries.DELETE_SUPPLY;
import static com.mse.db.pharma.utils.StaticQueries.DELETE_SUPPLY_LINE;
import static com.mse.db.pharma.utils.StaticQueries.INSERT_ORDER;
import static com.mse.db.pharma.utils.StaticQueries.INSERT_ORDER_LINE;
import static com.mse.db.pharma.utils.StaticQueries.INSERT_SHIPPING;
import static com.mse.db.pharma.utils.StaticQueries.INSERT_SUPPLY;
import static com.mse.db.pharma.utils.StaticQueries.INSERT_SUPPLY_LINE;
import static com.mse.db.pharma.utils.StaticQueries.SELECT_ALL_ORDERS;
import static com.mse.db.pharma.utils.StaticQueries.SELECT_ALL_SHIPPINGS;
import static com.mse.db.pharma.utils.StaticQueries.SELECT_ALL_SUPPLIES;
import static com.mse.db.pharma.utils.StaticQueries.SELECT_ORDER_LINES_BY_ORDER_ID;
import static com.mse.db.pharma.utils.StaticQueries.SELECT_SUPPLY_LINES_BY_SUPPLY_ID;
import static com.mse.db.pharma.utils.StaticQueries.UPDATE_ORDER;
import static com.mse.db.pharma.utils.StaticQueries.UPDATE_SHIPPING;
import static com.mse.db.pharma.utils.StaticQueries.UPDATE_SUPPLY;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mse.db.pharma.data.contragents.Address;
import com.mse.db.pharma.data.contragents.Customer;
import com.mse.db.pharma.data.contragents.Shipper;
import com.mse.db.pharma.data.contragents.Supplier;
import com.mse.db.pharma.data.item.Item;
import com.mse.db.pharma.data.transaction.Order;
import com.mse.db.pharma.data.transaction.OrderLine;
import com.mse.db.pharma.data.transaction.Shipping;
import com.mse.db.pharma.data.transaction.Supply;
import com.mse.db.pharma.data.transaction.SupplyLine;
import com.mse.db.pharma.utils.DBUtil;

public class TransactionsRepository implements Repository {

	private Connection conn;

	public TransactionsRepository(DBUtil dbUtil) {
		this.conn = dbUtil.getConnection();
	}

	public List<Order> findAllOrders() {
		List<Order> orders;
		try (ResultSet resultset = conn.createStatement().executeQuery(SELECT_ALL_ORDERS)) {
			orders = buildOrdersCollectionFromResultSet(resultset);
			for (Order o : orders) {
				setOrderLines(o);
			}
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public List<Shipping> findAllShippings() {
		List<Shipping> shippings;
		try (ResultSet resultset = conn.createStatement().executeQuery(SELECT_ALL_SHIPPINGS)) {
			shippings = buildShippingsCollectionFromResultSet(resultset);
			return shippings;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public List<Supply> findAllSupplies() {
		List<Supply> supplies;
		try (ResultSet result = conn.createStatement().executeQuery(SELECT_ALL_SUPPLIES)) {
			supplies = buildSupplyCollectionFromResultSet(result);
			return supplies;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public Order findOrderById(long id) throws SQLException {
		String query = SELECT_ALL_ORDERS + " WHERE o.id = ?";
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setLong(1, id);
			Order order = buildOrdersCollectionFromResultSet(statement.executeQuery()).get(0);
			setOrderLines(order);
			return order;
		}
	}

	public Shipping findShippingById(long id) throws SQLException {
		String query = SELECT_ALL_SHIPPINGS + " WHERE s.id = ?";
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setLong(1, id);
			return buildShippingsCollectionFromResultSet(statement.executeQuery()).get(0);
		}
	}

	public Supply findSupplyById(long id) throws SQLException {
		String query = SELECT_ALL_SUPPLIES + " WHERE a.id = ?";
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setLong(1, id);
			return buildSupplyCollectionFromResultSet(statement.executeQuery()).get(0);
		}
	}

	public void insertOrder(Order order) throws SQLException {
		PreparedStatement statement = conn.prepareStatement(INSERT_ORDER);
		order.decorateStatement(statement, true);
		System.out.println("Insert statement executed. Result present: " + statement.execute());
	}

	public void insertShipping(Shipping shipping) throws SQLException {
		PreparedStatement statement = conn.prepareStatement(INSERT_SHIPPING);
		shipping.decorateStatement(statement, true);
		System.out.println("Insert statement executed. Result present: " + statement.execute());
	}

	public void insertSupply(Supply supply) throws SQLException {
		PreparedStatement statement = conn.prepareStatement(INSERT_SUPPLY);
		supply.decorateStatement(statement, true);
		System.out.println("Insert statement executed. Result present: " + statement.execute());
	}

	public void insertSupplyLine(SupplyLine supplyLine) throws SQLException {
		PreparedStatement statement = conn.prepareStatement(INSERT_SUPPLY_LINE);
		supplyLine.decorateStatement(statement);
		System.out.println("Insert statement executed. Result present: " + statement.execute());
	}

	public void insertOrderLine(OrderLine orderLine) throws SQLException {
		PreparedStatement statement = conn.prepareStatement(INSERT_ORDER_LINE);
		orderLine.decorateStatement(statement);
		System.out.println("Insert statement executed. Result present: " + statement.execute());
	}

	public void updateOrders(Order order) throws SQLException {
		PreparedStatement statement = conn.prepareStatement(UPDATE_ORDER);
		order.decorateStatement(statement, false);
		System.out.println("Update statement executed. Result present: " + statement.execute());
	}

	public void updateShipping(Shipping shipping) throws SQLException {
		PreparedStatement statement = conn.prepareStatement(UPDATE_SHIPPING);
		shipping.decorateStatement(statement, false);
		System.out.println("Update statement executed. Result present: " + statement.execute());
	}

	public void updateSupply(Supply supply) throws SQLException {
		PreparedStatement statement = conn.prepareStatement(UPDATE_SUPPLY);
		supply.decorateStatement(statement, false);
		System.out.println("Update statement executed. Result present: " + statement.execute());
	}

	public void deleteOrder(Order order) throws SQLException {
		try (PreparedStatement statement = conn.prepareStatement(DELETE_ORDER)) {
			order.getChilds().forEach(line -> {
				try (PreparedStatement stmt = conn.prepareStatement(DELETE_ORDER_LINE)) {
					stmt.setLong(1, line.getOrderId());
				} catch (SQLException throwables) {
					System.out.println("Could not delete order line for order id: " + line.getOrderId());
				}
			});
			statement.setLong(1, order.getId());
			System.out.println("Delete statement executed. Result present: " + statement.execute());
		}
	}

	public void deleteShipping(Shipping shipping) throws SQLException {
		try (PreparedStatement statement = conn.prepareStatement(DELETE_SHIPPING)) {
			statement.setLong(1, shipping.getId());
			System.out.println("Delete statement executed. Result present: " + statement.execute());
		}
	}

	public void deleteSupply(Supply supply) throws SQLException {
		try (PreparedStatement statement = conn.prepareStatement(DELETE_SUPPLY)) {
			supply.getChilds().forEach(line -> {
				try (PreparedStatement stmt = conn.prepareStatement(DELETE_SUPPLY_LINE)) {
					stmt.setLong(1, line.getSupplyId());
				} catch (SQLException throwables) {
					System.out.println("Could not delete supply line for supply id: " + line.getSupplyId());
				}
			});
			statement.setLong(1, supply.getId());
			System.out.println("Delete statement executed. Result present: " + statement.execute());
		}
	}

	private void setOrderLines(Order o) throws SQLException {
		List<OrderLine> lines = new ArrayList<>();
		try (PreparedStatement statement = conn.prepareStatement(SELECT_ORDER_LINES_BY_ORDER_ID)) {
			statement.setLong(1, o.getId());
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				OrderLine line = new OrderLine();
				line.setOrderId(o.getId());
				line.setQuantity(res.getDouble("qty"));
				line.setPrice(res.getDouble("price"));
				Item item = getItemFromResultsetAsRef(res);
				line.setItem(item);
				lines.add(line);
			}
		}
		o.setChilds(lines);
	}

	/**
	 * Builds collection with {@link Order}} from {@link ResultSet}
	 *
	 * @param resultset {@link ResultSet}
	 * @return
	 */
	private static List<Order> buildOrdersCollectionFromResultSet(ResultSet resultset) {
		List<Order> orders = new ArrayList<>();
		try {
			while (resultset.next()) {
				Order order = new Order();
				order.setId(resultset.getInt("ID"));
				order.setAddress(getAddressObjectFromResultset(resultset));
				order.setCreatedAt(resultset.getTimestamp("created_at"));
				order.setShippingId(resultset.getInt("shipping_id"));
				order.setShippingTime(resultset.getTimestamp("shipping_time"));
				order.setTotalPrice(resultset.getDouble("total_price"));
				Customer customer = getCustomerFromResultestAsRef(resultset);
				order.setCustomer(customer);
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	/**
	 * findAll {@link Supply}
	 *
	 * @return
	 */
	private List<Supply> buildSupplyCollectionFromResultSet(ResultSet resultset) {
		List<Supply> supplyList = new ArrayList<>();
		try {
			while (resultset.next()) {
				long id = resultset.getLong("id");
				Timestamp createdAt = resultset.getTimestamp("supplied_at");
				double totalPrice = resultset.getDouble("total_price");
				new Supplier();
				Supplier supplier;
				try {
					supplier = getSupplierFromResultsetAsRef(resultset);
					Supply supply = new Supply(id, createdAt, totalPrice, supplier);
					List<SupplyLine> lines = new ArrayList<>();
					try (PreparedStatement statement = conn.prepareStatement(SELECT_SUPPLY_LINES_BY_SUPPLY_ID)) {
						statement.setLong(1, supply.getId());
						ResultSet resultsetLines = statement.executeQuery();
						while (resultsetLines.next()) {
							SupplyLine line = new SupplyLine();
							line.setSupplyId(resultsetLines.getInt("supply_id"));
							line.setQuantity(resultsetLines.getInt("qty"));
							line.setPrice(resultsetLines.getDouble("price"));
							Item item = getItemFromResultsetAsRef(resultsetLines);
							line.setItem(item);
							lines.add(line);
						}
					}
					supply.setChilds(lines);
					supplyList.add(supply);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return supplyList;
	}

	private static List<Shipping> buildShippingsCollectionFromResultSet(ResultSet resultset) {
		List<Shipping> shippings = new ArrayList<>();
		try {
			while (resultset.next()) {
				Shipping shipping = new Shipping();
				shipping.setId(resultset.getLong("id"));
				shipping.setCreatedAt(resultset.getTimestamp("created_at"));
				shipping.setShipper(getShipperFromResultsetRef(resultset));
				shipping.setDistrict(resultset.getString("district"));
				shipping.setShippDate(resultset.getTimestamp("shipp_date"));
				shipping.setDeliveryDate(resultset.getTimestamp("delivery_date"));
				shippings.add(shipping);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shippings;
	}

	/**
	 * Get {@link Supplier} from {@link Ref}
	 */
	private static Supplier getSupplierFromResultsetAsRef(ResultSet resultset) throws SQLException {
		Ref ref = (Ref) resultset.getObject("supplier");
		Object[] attributes = ((Struct) ref.getObject()).getAttributes();
		Supplier supplier = new Supplier();
		supplier.setId(((BigDecimal) attributes[0]).intValue());
		supplier.setCompanyName(attributes[6].toString());
		supplier.setVat(attributes[7].toString());
		supplier.setBankNum(attributes[3].toString());
		supplier.setCreditCard(attributes[4].toString());
		supplier.setEmail(attributes[2].toString());
		supplier.setPhone(attributes[1].toString());
		supplier.setAddress(getAddressFromStruct((Struct) attributes[5]));
		return supplier;
	}

	private static Customer getCustomerFromResultestAsRef(ResultSet resultset) throws SQLException {
		Ref ref = (Ref) resultset.getObject("customer");
		Object[] attributes = ((Struct) ref.getObject()).getAttributes();
		Customer customer = new Customer();
		customer.setId(((BigDecimal) attributes[0]).intValue());
		customer.setFirstName(attributes[6].toString());
		customer.setLastName(attributes[7].toString());
		customer.setBankNum(attributes[3].toString());
		customer.setCreditCard(attributes[4].toString());
		customer.setEmail(attributes[2].toString());
		customer.setPhone(attributes[1].toString());
		customer.setAddress(getAddressFromStruct((Struct) attributes[5]));
		return customer;
	}

	private static Address getAddressFromStruct(Struct struct) throws SQLException {
		Object[] attributes = struct.getAttributes();
		return new Address(attributes[0].toString(), attributes[1].toString(), attributes[2].toString(),
				attributes[3].toString());
	}

	private static Item getItemFromResultsetAsRef(ResultSet resultset) throws SQLException {
		Ref ref = (Ref) resultset.getObject("item");

		Object[] attributes = ((Struct) ref.getObject()).getAttributes();
		Item item = new Item();
		item.setSku(attributes[0].toString());
		item.setDescription(attributes[1].toString());
		item.setPrice(((BigDecimal) attributes[2]).doubleValue());
		item.setSalePrice(((BigDecimal) attributes[3]).doubleValue());
		item.setDeleted(((BigDecimal) attributes[4]).intValue());
		return item;
	}

	public static Address getAddressObjectFromResultset(ResultSet resultset) throws SQLException {
		String city = resultset.getString("city");
		String street = resultset.getString("street");
		String province = resultset.getString("province");
		String postalCode = resultset.getString("postal_code");
		return new Address(street, city, province, postalCode);
	}

	private static Shipper getShipperFromResultsetRef(ResultSet resultset) throws SQLException {
		try {
			Ref ref = (Ref) resultset.getObject("shipper");
			Object[] attributes = ((Struct) ref.getObject()).getAttributes();
			Shipper shipper = new Shipper();
			shipper.setId(((BigDecimal) attributes[0]).intValue());
			shipper.setCompanyName(attributes[6].toString());
			shipper.setVat(attributes[7].toString());
			shipper.setBankNum(attributes[3].toString());
			shipper.setCreditCard(attributes[4].toString());
			shipper.setEmail(attributes[2].toString());
			shipper.setPhone(attributes[1].toString());
			shipper.setAddress(getAddressFromStruct((Struct) attributes[5]));
			return shipper;
		} catch (Exception e) {
			return null;
		}
	}
}
