package com.mse.db.pharma;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import com.mse.db.pharma.data.contragents.Contragent;
import com.mse.db.pharma.data.contragents.Customer;
import com.mse.db.pharma.data.contragents.Shipper;
import com.mse.db.pharma.data.contragents.Supplier;
import com.mse.db.pharma.data.item.Item;
import com.mse.db.pharma.data.item.Medicine;
import com.mse.db.pharma.data.item.Supplement;
import com.mse.db.pharma.data.transaction.Order;
import com.mse.db.pharma.data.transaction.Shipping;
import com.mse.db.pharma.data.transaction.Supply;
import com.mse.db.pharma.enums.ContragentTypes;
import com.mse.db.pharma.enums.ItemTypes;
import com.mse.db.pharma.repository.ContragentsRepository;
import com.mse.db.pharma.repository.ItemRepository;
import com.mse.db.pharma.repository.Repository;
import com.mse.db.pharma.repository.TransactionsRepository;
import com.mse.db.pharma.utils.DBUtil;
import com.mse.db.pharma.utils.FXUtill;
import com.mse.db.pharma.utils.TableElement;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainController implements Initializable, EventHandler<ActionEvent>, ChangeListener<Tab> {
	private ContragentsRepository contragentsRepository;
	private ItemRepository itemRepository;
	private TransactionsRepository transactionsRepository;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		DBUtil dbUtil = new DBUtil();
		contragentsRepository = new ContragentsRepository(dbUtil);
		itemRepository = new ItemRepository(dbUtil);
		transactionsRepository = new TransactionsRepository(dbUtil);
		attachListenersToButtons(buttonAddContragent, buttonAddItem, buttonAddOrder, buttonAddSupply);
		loadChoiceBoxes();

		tabPahe.getSelectionModel().selectedItemProperty().addListener(this);
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

		FXUtill.buildOrdersTable(tableOrderParent, orders, this);
		FXUtill.buildOrdersLinesTable(tableOrderLines, orderLines);
		FXUtill.buildSuppliesTable(tableSupplyParent, supplies, this);
		FXUtill.buildSupplyLinesTable(tableSupplyChild, supplyLines);
		FXUtill.buildShippingsTable(tableShippingParent, shippings, this);
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
		labelSupplyId.setText("доставка: " + supply.getId());
		labelSupplyDate.setText(supply.getSuppliedAt().toString());
		labelSupplySupplier.setText(supply.getSuplierCompanyName());
		labelSupplyPhone.setText(supply.getSupplier().getPhone());
		supplyLines.clear();
		supplyLines.addAll(supply.getChilds());
	}

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
	 * Event handler for {@link Button} and {@link ChoiceBox}
	 */
	@SuppressWarnings("unchecked")
	public void handle(ActionEvent event) {
		switch (event.getSource().getClass().getSimpleName()) {
		case "Button":
			switch (controls.get(event.getSource())) {
			case "buttonAddItem":
				openDialog(true, new Item(), "добавяне на артикул", itemRepository);
				break;
			case "buttonAddContragent":
				openDialog(true, new Contragent(), "добавяне на на контрагент", contragentsRepository);
				break;
			case "buttonAddOrder":
				openDialog(true, new Order(), "създаване на поръчка", transactionsRepository);
				break;
			case "buttonAddSupply":
				openDialog(true, new Supply(), "добавяне на доставка", transactionsRepository);
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
			List<Shipping> shippings = transactionsRepository.findAllShippings();
			this.shippings.clear();
			this.shippings.addAll(shippings);
			selectFirstLineFromTable(tableShippingParent);
			break;
		default:
			break;
		}
	}

	/**
	 * Sets the focus over first row of given table, to invoke loading of child
	 * table.
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
		String label = "редактиране на ";
		switch (element.getClass().getSimpleName()) {
		case "Medicine":
			label += "данни за лекарство";
			openDialog(false, element, label, itemRepository);
			break;
		case "Supplement":
			label += "данни за хранителна добавка";
			openDialog(false, element, label, itemRepository);
			break;
		case "Supply":
			label += "данни за доставка";
			openDialog(false, element, label, transactionsRepository);
			break;
		case "Order":
			label += "данни за поръчка";
			openDialog(false, element, label, transactionsRepository);
			break;
		default:
			break;
		}
	}

	private void openDialog(boolean isCreate, TableElement element, String label, Repository repo) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Dialog.fxml"));
			Parent root = loader.load();
			DialogController controller = loader.getController();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle(label);
			stage.setScene(new Scene(root));
			stage.show();
			controller.setParameters(isCreate, element, repo, stage);
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					we.consume();
					stage.close();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
			FXUtill.showError(e.getMessage());
		}
	}
}
