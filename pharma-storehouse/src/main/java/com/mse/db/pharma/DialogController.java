/**
 * 
 */
package com.mse.db.pharma;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import com.mse.db.pharma.data.item.Item;
import com.mse.db.pharma.data.item.Medicine;
import com.mse.db.pharma.data.item.Supplement;
import com.mse.db.pharma.repository.ItemRepository;
import com.mse.db.pharma.repository.Repository;
import com.mse.db.pharma.utils.FXUtill;
import com.mse.db.pharma.utils.TableElement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public class DialogController implements Initializable, EventHandler<ActionEvent> {
	private TableElement element;
	private Repository repo;
	private boolean isCreate;
	private Stage stage;
	@FXML
	private Label labelHeaderDialog;
	@FXML
	private AnchorPane dialogAnchorPane;
	@FXML
	private GridPane dialogGridPane;
	@FXML
	private Button buttonDialogSave, buttonDialogCancel;
	@FXML
	private ChoiceBox<String> dialogChoiceBox;

	private Map<String, TextArea> itemPropertiesToTextAreaMap = new HashMap<String, TextArea>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buttonDialogCancel.setOnAction(this);
		buttonDialogSave.setOnAction(this);
		dialogChoiceBox.setVisible(false);
	}

	@Override
	public void handle(ActionEvent event) {
		Button b = (Button) event.getSource();
		switch (b.getId()) {
		case "buttonDialogSave":
			applyChangesOverEditedTableElement();
			break;
		default:
			break;
		}
		closeDialog();
	}

	private void closeDialog() {
		Stage stage = (Stage) buttonDialogCancel.getScene().getWindow();
		stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

	public void setParameters(boolean isCreate, TableElement element, Repository repo, Stage stage) {
		this.element = element;
		this.repo = repo;
		this.isCreate = isCreate;
		this.stage = stage;
		switch (element.getClass().getSimpleName()) {
		case "Item":
			System.out.println("build create item form");
			buildItemForm((Item) element);
			break;
		case "Medicine":
			System.out.println("build edit medicine form");
			buildItemForm((Medicine) element);
			break;
		case "Supplement":
			System.out.println("build edit medicine form");
			buildItemForm((Supplement) element);
			break;
		case "Contragent":
			System.out.println("build create contragent form");
			break;
		case "Order":
			System.out.println("build " + (isCreate ? "add" : "edit") + " order form");
			break;
		case "Supply":
			System.out.println("build " + (isCreate ? "add" : "edit") + " supply form");
			break;
		default:
			break;
		}
	}

	/**
	 * Builds form for items data processing
	 * 
	 * @param item
	 */
	private void buildItemForm(Item item) {
		if (isCreate) {
			this.element = item = null;
			dialogChoiceBox.setVisible(true);
			dialogChoiceBox.getItems().addAll("лекарство", "хранителна добавка");
			dialogChoiceBox.setOnAction(new EventHandler<ActionEvent>() {
				@SuppressWarnings("unchecked")
				@Override
				public void handle(ActionEvent event) {
					System.out.println("selected..." + ((ChoiceBox<String>) event.getSource()).getValue());
					if (((ChoiceBox<String>) event.getSource()).getValue().contains("лек")) {
						element = new Medicine();
						addContentToGridPane((Item) element, !isCreate);
						stage.sizeToScene();
					} else {
						element = new Supplement();
						addContentToGridPane((Item) element, !isCreate);
						stage.sizeToScene();
					}
				}
			});
		} else {
			addContentToGridPane(item, !isCreate);
			stage.sizeToScene();
		}
	}

	private void addContentToGridPane(Item item, boolean isUpdate) {
		itemPropertiesToTextAreaMap.clear();
		dialogGridPane.getChildren().clear();
		AtomicInteger row = new AtomicInteger();
		itemPropertiesToTextAreaMap.put("sku",
				addRowToGrid(row, "артикул", item == null ? "" : item.getSku(), isUpdate));
		itemPropertiesToTextAreaMap.put("price", addRowToGrid(row, "цена", item == null ? "" : item.getPrice(), false));
		itemPropertiesToTextAreaMap.put("salePrice",
				addRowToGrid(row, "продажна цена", item == null ? "" : item.getSalePrice(), false));
		itemPropertiesToTextAreaMap.put("description",
				addRowToGrid(row, "описание", item == null ? "" : item.getDescription(), false));
		if (item instanceof Medicine) {
			item = (Medicine) item;
			itemPropertiesToTextAreaMap.put("recipe",
					addRowToGrid(row, "рецепта", item == null ? "" : ((Medicine) item).getResipeRequired(), false));
			itemPropertiesToTextAreaMap.put("minAge",
					addRowToGrid(row, "години", item == null ? "" : ((Medicine) item).getMinAge(), false));
			itemPropertiesToTextAreaMap.put("contraindications", addRowToGrid(row, "противопоказания",
					item == null ? "" : ((Medicine) item).getContraindications(), false));
		} else if (item instanceof Supplement) {
			itemPropertiesToTextAreaMap.put("usefulFor",
					addRowToGrid(row, "показания", item == null ? "" : ((Supplement) item).getUsefulFor(), false));
		}
	}

	private TextArea addRowToGrid(AtomicInteger row, String labelText, Object text, boolean readOnly) {
		Label label = new Label(labelText);
		label.setMaxHeight(24);
		TextArea textArea = new TextArea();
		textArea.setMaxHeight(24);
		textArea.setText(text.toString());
		textArea.setDisable(readOnly);
		dialogGridPane.addRow(row.getAndIncrement(), label, textArea);
		return textArea;
	}

	private void applyChangesOverEditedTableElement() {
		if (this.element instanceof Medicine) {
			try {
				((Medicine) element).setMinAge(Integer.parseInt((itemPropertiesToTextAreaMap.get("minAge").getText())));
				((Medicine) element)
						.setContraindications(itemPropertiesToTextAreaMap.get("contraindications").getText());
				((Medicine) element).setDescription(itemPropertiesToTextAreaMap.get("description").getText());
				((Medicine) element).setPrice(Double.parseDouble(itemPropertiesToTextAreaMap.get("price").getText()));
				((Medicine) element)
						.setSalePrice(Double.parseDouble(itemPropertiesToTextAreaMap.get("salePrice").getText()));
				((Medicine) element)
						.setResipeRequired(Integer.parseInt(itemPropertiesToTextAreaMap.get("recipe").getText()));
				if (isCreate) {
					((Medicine) element).setSku((itemPropertiesToTextAreaMap.get("sku").getText()));
					((ItemRepository) repo).insertMedicine((Medicine) this.element);
				} else {
					((ItemRepository) repo).updateMedicine((Medicine) this.element);
				}

			} catch (SQLException e) {
				FXUtill.showError(e.getMessage());
			}
		} else if (this.element instanceof Supplement) {
			try {
				((Supplement) element).setDescription(itemPropertiesToTextAreaMap.get("description").getText());
				((Supplement) element).setPrice(Double.parseDouble(itemPropertiesToTextAreaMap.get("price").getText()));
				((Supplement) element)
						.setSalePrice(Double.parseDouble(itemPropertiesToTextAreaMap.get("salePrice").getText()));
				((Supplement) element).setUsefulFor(itemPropertiesToTextAreaMap.get("usefulFor").getText());
				if (isCreate) {
					((Supplement) element).setSku((itemPropertiesToTextAreaMap.get("sku").getText()));
					((ItemRepository) repo).insertSupplement((Supplement) this.element);
				} else {
					((ItemRepository) repo).updateSupplement((Supplement) this.element);
				}
			} catch (SQLException e) {
				FXUtill.showError(e.getMessage());
			}
		}
	}
}
