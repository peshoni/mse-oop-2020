package com.mse.oop.crawler.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import com.mse.oop.crawler.core.Downloader;
import com.mse.oop.crawler.core.MultiPageDownloader;
import com.mse.oop.crawler.core.Timeouts;
import com.mse.oop.crawler.models.JobPosition;
import com.mse.oop.crawler.models.JobSite;
import com.mse.oop.crawler.models.TableElement;
import com.mse.oop.crawler.utils.CrawlerUtil;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class MainController implements Initializable, EventHandler<ActionEvent> {
	@FXML
	private Button btnDownload;
	@FXML
	private Button btnStop;
	@FXML
	private TableView<TableElement> sitesTable;
	@FXML
	private TableView<TableElement> resultTable;

	private HostServices hostServices;
	private Hashtable<Object, String> controls;
	private List<Downloader> downloaderPool;
	private ObservableList<TableElement> sites = FXCollections.observableArrayList();
	private ObservableList<TableElement> jobPossitions = FXCollections.observableArrayList();
	private AtomicInteger fetchCounter = new AtomicInteger(1);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		controls = new Hashtable<Object, String>();
		controls.put(btnDownload, btnDownload.getId());
		System.out.println(btnDownload.getId());
		controls.put(btnStop, btnStop.getId());
		btnDownload.setOnAction(this);
		btnStop.setOnAction(this);

		buildSitesTable();
		loadTableWithSites();
		addColumnsToResultTable();
		resultTable.setItems(jobPossitions);
		this.initThreadPool();
	}

	/**
	 * A method that makes {@link Observable} all {@link JobSite} objects. Builds
	 * {@link Hyperlink} for each site and attach listener to it.
	 */
	private void loadTableWithSites() {

		CrawlerApp.jobSites.forEach(site -> {
			try {
				Hyperlink link = new Hyperlink(site.getAddress());
				site.setHyperLink(link);
				link.setOnAction(this);
				sites.add(site);
			} catch (Exception e) {
			}
		});
		sitesTable.setItems(sites);
	}

	private void initThreadPool() {
		downloaderPool = new ArrayList<Downloader>();
		List<JobSite> sites = CrawlerUtil.getJobSitesCollection();
		JobSite jobsBg = sites.get(0);
		MultiPageDownloader downloader = new MultiPageDownloader(jobsBg, Timeouts.MIDDLE, 1);
		downloader.setParent(this);
		downloaderPool.add(downloader);
	}

	private void buildSitesTable() {
		addStringColumn(sitesTable, "#", "id");
		addStringColumn(sitesTable, "name", "name");
		addLinkColumn(sitesTable, "address", "hyperLink");
		addStringColumn(sitesTable, "type", "siteType");
		addStringColumn(sitesTable, "limit per download", "maxItemsPerDownload");
		addActionColumn(sitesTable, "edit", "edit");
	}

	private void addColumnsToResultTable() {
		addStringColumn(resultTable, "#", "id");
//		private String label;
//		private String link; 
//		private String description; 
		addStringColumn(resultTable, "downloaded", "downloadedAt");
		addStringColumn(resultTable, "position", "position");
		addStringColumn(resultTable, "salary", "salary");
		addStringColumn(resultTable, "refNumber", "refNumber");
		addStringColumn(resultTable, "location", "location");
		addLinkColumn(resultTable, "link", "hyperLink");

	}

	private void addStringColumn(TableView<TableElement> table, String label, String property) {
		TableColumn<TableElement, String> column = new TableColumn<>(label);
		column.setCellValueFactory(new PropertyValueFactory<>(property));

		table.getColumns().add(column);
	}

	private void addLinkColumn(TableView<TableElement> table, String label, String property) {
		TableColumn<TableElement, Hyperlink> column = new TableColumn<>(label);
		column.setCellValueFactory(new PropertyValueFactory<>(property));
		column.setCellFactory(new HyperlinkCell());
		table.getColumns().add(column);
	}

	private void addActionColumn(TableView<TableElement> table, String label, String buttonLabel) {
		TableColumn<TableElement, Void> column = new TableColumn<TableElement, Void>(label);
		column.setCellFactory(new ButtonCell(buttonLabel));
		table.getColumns().add(column);
	}

	@Override
	public void handle(ActionEvent event) {
		System.out.println(event.getSource().getClass().getSimpleName());
		switch (event.getSource().getClass().getSimpleName()) {
		case "Button":
			switch (controls.get(event.getSource())) {
			case "btnDownload":
				downloaderPool.forEach(e -> e.downloadJobsPosistions());
				break;
			case "btnStop":
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

	class HyperlinkCell implements Callback<TableColumn<TableElement, Hyperlink>, TableCell<TableElement, Hyperlink>> {
		@Override
		public TableCell<TableElement, Hyperlink> call(TableColumn<TableElement, Hyperlink> arg) {
			TableCell<TableElement, Hyperlink> cell = new TableCell<TableElement, Hyperlink>() {
				@Override
				protected void updateItem(Hyperlink item, boolean empty) {
					setGraphic(item);
				}
			};
			return cell;
		}
	}

	class ButtonCell implements Callback<TableColumn<TableElement, Void>, TableCell<TableElement, Void>> {
		private String supportColumn = "";

		public ButtonCell(String supportColumnType) {
			this.supportColumn = supportColumnType;

		}

		@Override
		public TableCell<TableElement, Void> call(TableColumn<TableElement, Void> param) {
			final TableCell<TableElement, Void> cell = new TableCell<TableElement, Void>() {
				private final Button btn = new Button(supportColumn);
				{
					btn.setOnAction((ActionEvent event) -> {
						if ("edit".equals(supportColumn)) {
							TableElement selected = getTableView().getItems().get(getIndex());
							openEditSiteDialog(selected);
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

	private void openEditSiteDialog(TableElement jobSite) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Dialog.fxml"));
			Parent root = loader.load();
			DialogController controller = loader.getController();
			controller.setSite((JobSite) jobSite);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Edit site data");
			stage.setScene(new Scene(root));
			stage.show();

			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					sites.clear();
					sites.addAll(CrawlerApp.jobSites);
					we.consume();
					stage.close();
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
			CrawlerUtil.showError(e.getMessage());
		}
	}

	/**
	 * Add rows with position info into result table
	 * 
	 * @param position
	 */
	public synchronized void showNewArrived(JobPosition position) {
		System.out.println(position);
		Hyperlink link = new Hyperlink(position.getLink());
		position.setHyperLink(link);
		link.setOnAction(this);
		position.setId(fetchCounter.getAndIncrement());
		jobPossitions.add(position);
	}
}
