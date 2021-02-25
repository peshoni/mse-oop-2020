package com.mse.db.pharma.fxutils;

import com.mse.db.pharma.MainController;
import com.mse.db.pharma.data.contragents.Contragent;
import com.mse.db.pharma.data.item.Item;
import com.mse.db.pharma.data.transaction.Order;
import com.mse.db.pharma.enums.ContragentTypes;
import com.mse.db.pharma.enums.ItemTypes;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXUtill {
	/**
	 * Private constructor for utility classes
	 */
	private FXUtill() {
	}

	/**
	 * Utility method that builds table for the {@link Item} data.
	 * 
	 * @param table
	 * @param observableList
	 * @param controller
	 * @param itemTypes
	 */
	public static void buildItemsTable(TableView<TableElement> table, ObservableList<TableElement> observableList,
			MainController controller, ItemTypes itemTypes) {
		addStringColumn(table, "артикул", "sku", 0);
		addStringColumn(table, "доставна цена", "price", 120);
		addStringColumn(table, "продажна цена", "salePrice", 120);
		switch (itemTypes) {
		case MEDICINE:
			addStringColumn(table, "рецепта", "resipeRequired", 60);
			addStringColumn(table, "години", "minAge", 60);
			addStringColumn(table, "противопоказания", "contraindications", 0);
			break;
		case SUPPLEMENT:
			FXUtill.addStringColumn(table, "полезно за", "usefulFor", 0);
			break;
		default:
			break;
		}
		FXUtill.addActionColumn(table, "редактиране", new ButtonCell("edit", controller));
		table.setItems(observableList);
	}

	/**
	 * Utility method that builds table for the {@link Contragent} data.
	 * 
	 * @param table
	 * @param observableList
	 * @param controller
	 * @param contragentType
	 */
	public static void buildContragentsTable(TableView<TableElement> table, ObservableList<TableElement> observableList,
			MainController controller, ContragentTypes contragentType) {
		addStringColumn(table, "#", "id", 30);
		switch (contragentType) {
		case CUSTOMER:
			addStringColumn(table, "име", "firstName", 0);
			addStringColumn(table, "фамилия", "lastName", 0);
			break;
		case SUPPLIER:
		case SHIPPER:
			FXUtill.addStringColumn(table, "наименование", "companyName", 0);
			FXUtill.addStringColumn(table, "булстат", "vat", 0);
			break;
		default:
			break;
		}
		addStringColumn(table, "телефон", "phone", 0);
		addStringColumn(table, "поща", "email", 0);
		addStringColumn(table, "банкова сметка", "bankNum", 0);
		addActionColumn(table, "редактиране", new ButtonCell("edit", controller));
		table.setItems(observableList);
	}

	/**
	 * Utility method that builds table for the {@link Order} data.
	 * 
	 * @param table
	 * @param observableList
	 * @param controller
	 */
	public static void buildOrdersTable(TableView<TableElement> table, ObservableList<TableElement> observableList,
			MainController controller) {
		addStringColumn(table, "#", "id", 30);
		addStringColumn(table, "създаден", "createdAt", 0);
		addStringColumn(table, "име ", "customerFirstName", 0);
		addStringColumn(table, "фамилия", "customerLastName", 0);
		addStringColumn(table, "град", "customerCity", 0);
		addStringColumn(table, "време за изпращане", "shippingTime", 0);
		addStringColumn(table, "сума", "totalPrice", 0);
		addActionColumn(table, "редактиране", new ButtonCell("edit", controller));
		table.setItems(observableList);
	}

	public static void buildOrdersLinesTable(TableView<TableElement> table,
			ObservableList<TableElement> observableList) {
		addStringColumn(table, "order", "orderId", 30);
		addStringColumn(table, "артикул", "itemSku", 0);
		addStringColumn(table, "количество", "quantity", 0);
		addStringColumn(table, "цена", "price", 0);
		addStringColumn(table, "сума", "linePrice", 0);
		table.setItems(observableList);
	}

	public static void buildSuppliesTable(TableView<TableElement> table, ObservableList<TableElement> observableList,
			MainController controller) {
		addStringColumn(table, "#", "id", 30);
		addStringColumn(table, "доставена на", "suppliedAt", 0);
		addStringColumn(table, "фирма", "suplierCompanyName", 0);
		addStringColumn(table, "булстат", "suplierVat", 0);
		addStringColumn(table, "сума", "totalPrice", 0);
		addActionColumn(table, "редактиране", new ButtonCell("edit", controller));
		table.setItems(observableList);
	}

	public static void buildSupplyLinesTable(TableView<TableElement> table,
			ObservableList<TableElement> observableList) {
		addStringColumn(table, "order", "supplyId", 30);
		addStringColumn(table, "артикул", "itemSku", 0);
		addStringColumn(table, "количество", "quantity", 0);
		addStringColumn(table, "доставна", "price", 0);
		addStringColumn(table, "сума", "linePrice", 0);
		addStringColumn(table, "продажна", "price", 0);
		addStringColumn(table, "сума", "lineSalePrice", 0);
		table.setItems(observableList);
	}

	/**
	 * Adding column in table with String values
	 * 
	 * @param table
	 * @param label
	 * @param property
	 * @param width
	 */
	private static void addStringColumn(TableView<TableElement> table, String label, String property, double width) {
		TableColumn<TableElement, String> column = new TableColumn<>(label);
		column.setCellValueFactory(new PropertyValueFactory<>(property));
		if (width > 0) {
			column.setMinWidth(width);
			column.setMaxWidth(width);
		}
		table.getColumns().add(column);
	}

	/**
	 * Adds column with buttons
	 * 
	 * @param table       {@link TableView}
	 * @param label       Column label
	 * @param buttonLabel
	 */
	private static void addActionColumn(TableView<TableElement> table, String label, ButtonCell button) {
		TableColumn<TableElement, Void> column = new TableColumn<TableElement, Void>(label);
		column.setCellFactory(button);
		column.setMinWidth(100);
		column.setMaxWidth(100);
		table.getColumns().add(column);
	}
}
