package com.mse.db.pharma.utils;

import com.mse.db.pharma.MainController;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ButtonCell implements Callback<TableColumn<TableElement, Void>, TableCell<TableElement, Void>> {
	private String supportColumn = "";
	private MainController controller;

	// ADD CONTROLLER REF
	public ButtonCell(String supportColumnType, MainController controller) {
		this.supportColumn = supportColumnType;
		this.controller = controller;
	}

	@Override
	public TableCell<TableElement, Void> call(TableColumn<TableElement, Void> param) {
		final TableCell<TableElement, Void> cell = new TableCell<TableElement, Void>() {
			private final Button btn = new Button(supportColumn);
			{
				btn.setOnAction((ActionEvent event) -> {
					if ("edit".equals(supportColumn)) {
						TableElement selected = getTableView().getItems().get(getIndex());
						controller.edit(selected);
					}
				});
			}

			@Override
			public void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(btn);
				}
			}
		};
		return cell;
	}
}