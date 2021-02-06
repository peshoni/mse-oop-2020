/**
 * 
 */
package com.mse.oop.crawler.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.mse.oop.crawler.core.TimeoutTypes;
import com.mse.oop.crawler.models.JobSite;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public class DialogController implements Initializable, EventHandler<ActionEvent> {
	private JobSite site;
	@FXML
	private TextArea siteName;
	@FXML
	private TextArea selectorPosition;
	@FXML
	private TextArea selectorJobDescription;
	@FXML
	private TextArea selectorRefNumber;
	@FXML
	private TextArea selectorLocation;
	@FXML
	private TextArea selectorSalary;
	@FXML
	private Button btnDialogOk;
	@FXML
	private Button btnDialogCancel;
	@FXML
	private ComboBox<TimeoutTypes> comboTimeout;
	@FXML
	private TextField positionLimit;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnDialogCancel.setOnAction(this);
		btnDialogOk.setOnAction(this);
		ObservableList<TimeoutTypes> options = FXCollections.observableArrayList(TimeoutTypes.FAST, TimeoutTypes.SLOWER,
				TimeoutTypes.SLOWEST);
		comboTimeout.setItems(options);

		positionLimit.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.matches("\\d*"))
				return;
			positionLimit.setText(newValue.replaceAll("[^\\d]", ""));
		});
	}

	/**
	 * Setter method for JobSite
	 * 
	 * @param site
	 */
	public void setSite(JobSite site) {
		this.site = site;
		siteName.setText(site.getName());
		selectorJobDescription.setText(site.getSelectorJobDescription());
		selectorPosition.setText(site.getSelectorPosition());
		selectorJobDescription.setText(site.getSelectorJobDescription());
		selectorRefNumber.setText(site.getSelectorRefNumber());
		selectorLocation.setText(site.getSelectorLocaion());
		selectorSalary.setText(site.getSelectorSalary());
		comboTimeout.getSelectionModel().select(site.getTimeoutType().getTypeId() - 1);
		positionLimit.setText(String.valueOf(site.getDownloadLimit()));
	}

	@Override
	public void handle(ActionEvent event) {
		Button b = (Button) event.getSource();
		switch (b.getId()) {
		case "btnDialogOk":
			setSiteNewConfigurations();
			break;
		default:
			break;
		}
		closeDialog();
	}

	private void setSiteNewConfigurations() {
		site.setName(siteName.getText());
		site.setSelectorJobDescription(selectorJobDescription.getText());
		site.setSelectorPosition(selectorPosition.getText());
		site.setSelectorJobDescription(selectorJobDescription.getText());
		site.setSelectorRefNumber(selectorRefNumber.getText());
		site.setSelectorLocaion(selectorLocation.getText());
		site.setSelectorSalary(selectorSalary.getText());
		site.setTimeoutType(comboTimeout.getValue());
		site.setDownloadLimit(Integer.parseInt(positionLimit.getText()));
	}

	public JobSite getSite() {
		return site;
	}

	private void closeDialog() {
		Stage stage = (Stage) btnDialogCancel.getScene().getWindow();
		stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}
}
