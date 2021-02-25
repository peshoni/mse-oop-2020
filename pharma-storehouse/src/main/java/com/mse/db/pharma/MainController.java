package com.mse.db.pharma;

import java.net.URL;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import com.mse.db.pharma.data.contragents.Customer;
import com.mse.db.pharma.data.contragents.Shipper;
import com.mse.db.pharma.data.contragents.Supplier;
import com.mse.db.pharma.data.item.Medicine;
import com.mse.db.pharma.data.item.Supplement;
import com.mse.db.pharma.data.transaction.Order;
import com.mse.db.pharma.data.transaction.Shippings;
import com.mse.db.pharma.data.transaction.Supply;
import com.mse.db.pharma.enums.ContragentTypes;
import com.mse.db.pharma.enums.ItemTypes;
import com.mse.db.pharma.fxutils.DBUtil;
import com.mse.db.pharma.fxutils.FXUtill;
import com.mse.db.pharma.fxutils.TableElement;
import com.mse.db.pharma.repository.ContragentsRepository;
import com.mse.db.pharma.repository.ItemRepository;
import com.mse.db.pharma.repository.TransactionsRepository;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;

public class MainController implements Initializable, EventHandler<ActionEvent>, ChangeListener<Tab> {
	ContragentsRepository contragentsRepository;
	ItemRepository itemRepository;
	TransactionsRepository transactionsRepository;
//ITEMS
	@FXML
	private TabPane tabPahe;
	@FXML
	private Tab tabItems;
	@FXML
	private ChoiceBox<ItemTypes> choiceItemType;
	@FXML
	private Button buttonAddItem;
	@FXML
	private TableView<TableElement> tableItems;
//CONTRAGENTS
	@FXML
	private Tab tabContragents;
	@FXML
	private Button buttonAddContragent;
	@FXML
	private ChoiceBox<ContragentTypes> chooseContragentsType;
	@FXML
	private TableView<TableElement> tableContragents;
//TRANSACTIONS
	@FXML
	private Tab tabSupply;
	@FXML
	private Button buttonAddSupply;
	@FXML
	private TableView<TableElement> tableSupplyParent, tableSupplyChild;
	@FXML
	private Tab tabOrders;
	@FXML
	private Button buttonAddOrder;
	@FXML
	private TableView<TableElement> tableOrderParent, tableOrderLines;
	@FXML
	private Tab tabShipping;
	@FXML
	private TableView<TableElement> tableShippingParent, tableShippingChild;

	private Hashtable<Object, String> controls;

	private ObservableList<TableElement> contragents = FXCollections.observableArrayList();
	private ObservableList<TableElement> items = FXCollections.observableArrayList();

	private ObservableList<TableElement> supplies = FXCollections.observableArrayList();
	private ObservableList<TableElement> supplyLines = FXCollections.observableArrayList();

	private ObservableList<TableElement> orders = FXCollections.observableArrayList();
	private ObservableList<TableElement> orderLines = FXCollections.observableArrayList();

	private ObservableList<TableElement> shippings = FXCollections.observableArrayList();

	// Labels for properties of transactions
	@FXML
	private Label labelOrderDate, labelOrderCustomer, labelOrderAddress, labelOrderPhone, labelOrderId;
	@FXML
	private Label labelSupplyId, labelSupplyDate, labelSupplySupplier, labelSupplyPhone;

	// private ObservableList<TableElement> jobPositions =
	// FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		DBUtil dbUtil = new DBUtil();
		contragentsRepository = new ContragentsRepository(dbUtil);
		itemRepository = new ItemRepository(dbUtil);
		transactionsRepository = new TransactionsRepository(dbUtil);
		attachListenersToButtons(buttonAddContragent, buttonAddItem, buttonAddOrder, buttonAddSupply);
		loadChoiceBoxes();

		tabPahe.getSelectionModel().selectedItemProperty().addListener(this);
		// choiceItemType.getSelectionModel().selectFirst();
		// tabPahe.getSelectionModel().selectLast();
		tabPahe.getSelectionModel().selectFirst();

		tableOrderParent.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				this.showOrderParameters((Order) newSelection);
			}
		});
		tableSupplyParent.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				this.showSupplyParameters((Supply) newSelection);
			}
		});

		// buildMedicinesTable();
//		buildSupplementsTable();
//

//		try {
//			List<Medicine> medicines = itemRepository.findAllMedicines();
//			tableItems.getItems().addAll(medicines);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		try {
//			List<Supplement> supplements = itemRepository.findAllSupplement();
//			tableItems.getItems().addAll(supplements);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

//		Medicine m = new Medicine();
//		m.setSku("alabala");
//		m.setContraindications("no");
//		tableItems.getItems().add(m);
//
//		Supplement sup = new Supplement();
//		sup.setSku("brada");
//		sup.setUsefulFor("big beard");
//		tableItems.getItems().add(sup);

		// this.items.add(m);

		// buildOrdersTable();
		// buildSuppliesTable();
		// buildShippingTable();
		FXUtill.buildOrdersTable(tableOrderParent, orders, this);
		FXUtill.buildOrdersLinesTable(tableOrderLines, orderLines);
		FXUtill.buildSuppliesTable(tableSupplyParent, supplies, this);
		FXUtill.buildSupplyLinesTable(tableSupplyChild, supplyLines);
	}

	private void showOrderParameters(Order order) {
		labelOrderId.setText("Поръчка : " + order.getId());
		labelOrderDate.setText(order.getCreatedAt().toString());
		labelOrderCustomer.setText(order.getCustomerFirstName() + " " + order.getCustomerLastName());
		labelOrderAddress.setText(order.getAddress().getDisplayAddress());
		labelOrderPhone.setText(order.getCustomer().getPhone());
		orderLines.clear();
		orderLines.addAll(order.getChilds());

	}

	private void showSupplyParameters(Supply supply) {
		System.out.println(supply.toString());
		labelSupplyId.setText("доставка: " + supply.getId());
		labelSupplyDate.setText(supply.getSuppliedAt().toString());
		labelSupplySupplier.setText(supply.getSuplierCompanyName());
		labelSupplyPhone.setText(supply.getSupplier().getPhone());
		supplyLines.clear();
		supplyLines.addAll(supply.getChilds());
	}

//	private int resipeRequired;
//	private int minAge;
//	private String contraindications;

	/**
	 * Loads {@link ChoiceBox} with values and sets default to `all`.
	 */
	private void loadChoiceBoxes() {
		choiceItemType.getItems().addAll(ItemTypes.values());
		choiceItemType.setOnAction(this);

		chooseContragentsType.getItems().addAll(ContragentTypes.values());
		chooseContragentsType.setOnAction(this);

		controls.put(choiceItemType, choiceItemType.getId());
		controls.put(chooseContragentsType, chooseContragentsType.getId());

		choiceItemType.getSelectionModel().selectFirst();
		chooseContragentsType.getSelectionModel().selectFirst();
	}

	private void attachListenersToButtons(Button... buttons) {
		controls = new Hashtable<Object, String>();
		for (Button button : buttons) {
			controls.put(button, button.getId());
			button.setOnAction(this);
		}
	}

	/**
	 * Event handler
	 */
	@SuppressWarnings("unchecked")
	public void handle(ActionEvent event) {
		// System.out.println(event.getSource().getClass().getSimpleName());

		switch (event.getSource().getClass().getSimpleName()) {
		case "Button":
			switch (controls.get(event.getSource())) {
			case "buttonAddItem":
				System.out.println("add item");
				break;
			case "buttonAddContragent":
				System.out.println("add contragent");
				break;
			case "buttonAddOrder":
				System.out.println("add order");
				break;
			case "buttonAddSupply":
				System.out.println("add supply");
				break;
			default:
				break;
			}
			break;

		case "ChoiceBox":

			switch (controls.get(event.getSource())) {

			case "choiceItemType":

				tableItems.getColumns().clear();
				ItemTypes itemType = ((ChoiceBox<ItemTypes>) event.getSource()).getValue();
				switch (itemType) {
				case MEDICINE:
					FXUtill.buildItemsTable(tableItems, items, this, itemType);
					try {
						List<Medicine> medicines = itemRepository.findAllMedicines();
						items.clear();
						items.addAll(medicines);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				case SUPPLEMENT:
					FXUtill.buildItemsTable(tableItems, items, this, itemType);
					try {
						List<Supplement> supplements = itemRepository.findAllSupplement();
						items.clear();
						items.addAll(supplements);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				default:
					break;
				}

				break;

			case "chooseContragentsType":
				tableContragents.getColumns().clear();
				ContragentTypes contragentType = ((ChoiceBox<ContragentTypes>) event.getSource()).getValue();
				switch (contragentType) {
				case SUPPLIER:
					FXUtill.buildContragentsTable(tableContragents, contragents, this, contragentType);
					try {
						List<Supplier> suppliers = contragentsRepository.findAllSuppliers();
						contragents.clear();
						contragents.addAll(suppliers);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				case CUSTOMER:
					FXUtill.buildContragentsTable(tableContragents, contragents, this, contragentType);
					try {
						List<Customer> customers = contragentsRepository.findAllCustomers();
						contragents.clear();
						contragents.addAll(customers);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				case SHIPPER:
					FXUtill.buildContragentsTable(tableContragents, contragents, this, contragentType);
					try {
						List<Shipper> shippers = contragentsRepository.findAllShippers();
						contragents.clear();
						contragents.addAll(shippers);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				default:
					break;
				}

				break;
			default:
				break;
			}

			break;
		default:
			// do nothing
			break;
		}
		event.consume();
	}

	@Override
	public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
		switch (newValue.getId()) {
		case "tabSupply":
			supplies.clear();
			supplies.addAll(transactionsRepository.findAllSupplies());
			selectFirstLineFromTable(tableSupplyParent);
			break;
		case "tabOrders":
			orders.clear();
			orders.addAll(transactionsRepository.findAllOrders());
			selectFirstLineFromTable(tableOrderParent);
			break;
		case "tabShipping":
			System.out.println("load shipping");
			List<Shippings> shippings = transactionsRepository.findAllShippings();
			shippings.forEach(System.out::println);
			break;
		default:
			break;
		}
	}

	/**
	 * Sets the focus over first row of given table.
	 * 
	 * @param table
	 */
	private void selectFirstLineFromTable(TableView<TableElement> table) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				table.requestFocus();
				table.getSelectionModel().select(0);
				table.getFocusModel().focus(0);
			}
		});
	}

	public void edit(TableElement element) {
		System.out.println("Edit: " + element.getClass().getSimpleName());
	}

}
