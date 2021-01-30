package com.edu.crawler.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.ResourceBundle;

import com.edu.crawler.models.JobSite;
import com.edu.crawler.utils.CUtil;

import javafx.application.HostServices;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class MainController implements Initializable, EventHandler<ActionEvent> {
	private HostServices hostServices;
	private final String LS = System.lineSeparator();
	private Hashtable<Object, String> controls;
	@FXML
	private Button btnDownload;
	@FXML
	private Button btnStop;
	@FXML
	private TextArea resultTextArea;
	@FXML
	private TableView<JobSite> sitesTable;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		controls = new Hashtable<Object, String>();
		controls.put(btnDownload, btnDownload.getId());
		System.out.println(btnDownload.getId());
		controls.put(btnStop, btnStop.getId());
		btnDownload.setOnAction(this);
		btnStop.setOnAction(this);

		ObservableList<JobSite> sites = FXCollections.observableArrayList();
		CrawlerApp.jobSites.forEach(site -> {
			try {
				Hyperlink link = new Hyperlink(site.getUrl());
				site.setHyperLink(link);
				link.setOnAction(this);
				sites.add(site);
			} catch (Exception e) {
			}
		});

		addColumns();
		sitesTable.setItems(sites);
	}

	private void addColumns() {

		TableColumn<JobSite, Integer> idColumn = new TableColumn<>("id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		sitesTable.getColumns().add(idColumn);

		TableColumn<JobSite, String> nameColumn = new TableColumn<>("name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		sitesTable.getColumns().add(nameColumn);

		TableColumn<JobSite, Hyperlink> urlColumn = new TableColumn<>("address");
		urlColumn.setCellValueFactory(new PropertyValueFactory<>("hyperLink"));
		urlColumn.setCellFactory(new HyperlinkCell());
		sitesTable.getColumns().add(urlColumn);

		TableColumn<JobSite, String> siteType = new TableColumn<>("type");
		siteType.setCellValueFactory(new PropertyValueFactory<>("siteType"));
		sitesTable.getColumns().add(siteType);

		TableColumn<JobSite, String> maxItemsPerDownload = new TableColumn<>("limit per download");
		maxItemsPerDownload.setCellValueFactory(new PropertyValueFactory<>("maxItemsPerDownload"));
		sitesTable.getColumns().add(maxItemsPerDownload);

		TableColumn<JobSite, Void> editColumn = new TableColumn<JobSite, Void>("edit");
		editColumn.setCellFactory(new ButtonCell("edit"));
		sitesTable.getColumns().add(editColumn);

		TableColumn<JobSite, Void> runColumn = new TableColumn<JobSite, Void>("run");
		runColumn.setCellFactory(new ButtonCell("run"));
		sitesTable.getColumns().add(runColumn);

	}

	@Override
	public void handle(ActionEvent event) {
		System.out.println(event.getSource().getClass().getSimpleName());
		switch (event.getSource().getClass().getSimpleName()) {
		case "Button":
			switch (controls.get(event.getSource())) {
			case "btnDownload":
				resultTextArea.appendText(Timestamp.valueOf(LocalDateTime.now()) + ": download" + LS);
				// CUtil.showError("Hillou");
				break;
			case "btnStop":
				resultTextArea.appendText(Timestamp.valueOf(LocalDateTime.now()) + ": Stop" + LS);
				break;
			default:
				break;
			}
			break;
		case "Hyperlink":
			Hyperlink h = (Hyperlink) event.getTarget();
			hostServices.showDocument(h.getText());
			break;
		default:
			// do nothing
			break;
		}
		event.consume();
	}

	public void setHostServices(HostServices hostServices) {
		this.hostServices = hostServices;
	}

	class HyperlinkCell implements Callback<TableColumn<JobSite, Hyperlink>, TableCell<JobSite, Hyperlink>> {
		@Override
		public TableCell<JobSite, Hyperlink> call(TableColumn<JobSite, Hyperlink> arg) {
			TableCell<JobSite, Hyperlink> cell = new TableCell<JobSite, Hyperlink>() {
				@Override
				protected void updateItem(Hyperlink item, boolean empty) {
					setGraphic(item);
				}
			};
			return cell;
		}
	}

	class ButtonCell implements Callback<TableColumn<JobSite, Void>, TableCell<JobSite, Void>> {
		private String supportColumn = "";

		public ButtonCell(String supportColumnType) {
			this.supportColumn = supportColumnType;

		}

		@Override
		public TableCell<JobSite, Void> call(TableColumn<JobSite, Void> param) {
			final TableCell<JobSite, Void> cell = new TableCell<JobSite, Void>() {
				private final Button btn = new Button(supportColumn);
				{
					btn.setOnAction((ActionEvent event) -> {

						if ("edit".equals(supportColumn)) {
							JobSite selected = getTableView().getItems().get(getIndex());
							openEditSiteDialog(selected);
						} else if ("run".equals(supportColumn)) {
							System.out.println("RUN");
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

	private void openEditSiteDialog(JobSite jobSite) {
		System.out.println("selectedData: " + jobSite);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Dialog.fxml"));
			Parent root = loader.load();
			DialogControler controller = loader.getController();
			// controller.initObject(jobSite);

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Edit site data");
			stage.setScene(new Scene(root));
			stage.show();
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					System.out.println("Stage is closing");
					System.out.println("Job site new URL: " + jobSite.getUrl());

					we.consume();
					stage.close();
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
			CUtil.showError(e.getMessage());
		}

	}

}
