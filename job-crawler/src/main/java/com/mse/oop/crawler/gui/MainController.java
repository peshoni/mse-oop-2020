package com.mse.oop.crawler.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.mse.oop.crawler.core.DownloaderFactory;
import com.mse.oop.crawler.core.WorkersPool;
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
	private TableView<TableElement> sitesTable;
	@FXML
	private TableView<TableElement> resultTable;

	private HostServices hostServices;
	private Hashtable<Object, String> controls;
	private WorkersPool pool;
	private ObservableList<TableElement> jobSites = FXCollections.observableArrayList();
	private ObservableList<TableElement> jobPositions = FXCollections.observableArrayList();
	private AtomicInteger fetchCounter = new AtomicInteger(1);
	private WebDriver webDriver;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addChromeDriver();
		turnOnButtons();
		buildSitesTable();
		loadSitesTableWithData();
		buildResultTable();
		resultTable.setItems(jobPositions);
		pool = new WorkersPool();
	}

	/**
	 * Loads the {@link ChromeDriver} asynchronously.
	 */
	private void addChromeDriver() {
		Thread thr = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.setProperty("webdriver.chrome.driver", "C:\\tmp\\selenium\\chromedriver.exe");
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--headless");
					options.addArguments("--disable-gpu");
					options.addArguments("--window-size=1400,800");
					webDriver = new ChromeDriver(options);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		thr.setDaemon(true);
		thr.start();
	}

	/**
	 * A method that makes {@link Observable} all {@link JobSite} objects. Builds
	 * {@link Hyperlink} for each site and attach listener to it.
	 */
	private void loadSitesTableWithData() {
		CrawlerApp.jobSites.forEach(site -> {
			try {
				Hyperlink link = new Hyperlink(site.getAddress());
				site.setHyperLink(link);
				link.setOnAction(this);
				jobSites.add(site);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});
		sitesTable.setItems(jobSites);
	}

	private void buildSitesTable() {
		addStringColumn(sitesTable, "#", "id", 30);
		addStringColumn(sitesTable, "name", "name", 0);
		addLinkColumn(sitesTable, "address", "hyperLink");
		addStringColumn(sitesTable, "type", "siteType", 0);
		addStringColumn(sitesTable, "download limit", "downloadLimit", 0);
		addActionColumn(sitesTable, "edit", "edit");
	}

	private void buildResultTable() {
		addStringColumn(resultTable, "#", "id", 30);
		addStringColumn(resultTable, "site", "siteName", 200);
		addStringColumn(resultTable, "downloaded", "downloadedAt", 0);
		addStringColumn(resultTable, "position", "position", 0);
		addStringColumn(resultTable, "salary", "salary", 0);
		addStringColumn(resultTable, "refNumber", "refNumber", 0);
		addStringColumn(resultTable, "location", "location", 0);
		addLinkColumn(resultTable, "link", "hyperLink");
	}

	/**
	 * Adding column in table with String values
	 * 
	 * @param table
	 * @param label
	 * @param property
	 * @param width
	 */
	private void addStringColumn(TableView<TableElement> table, String label, String property, double width) {
		TableColumn<TableElement, String> column = new TableColumn<>(label);
		column.setCellValueFactory(new PropertyValueFactory<>(property));
		if (width > 0) {
			column.setMinWidth(width);
			column.setMaxWidth(width);
		}
		table.getColumns().add(column);
	}

	/**
	 * Adds a column with hyper links
	 * 
	 * @param table    {@link TableView}
	 * @param label    Column label
	 * @param property value for linking
	 */
	private void addLinkColumn(TableView<TableElement> table, String label, String property) {
		TableColumn<TableElement, Hyperlink> column = new TableColumn<>(label);
		column.setCellValueFactory(new PropertyValueFactory<>(property));
		column.setCellFactory(new HyperlinkCell());
		table.getColumns().add(column);
	}

	/**
	 * Adds column with buttons
	 * 
	 * @param table       {@link TableView}
	 * @param label       Column label
	 * @param buttonLabel
	 */
	private void addActionColumn(TableView<TableElement> table, String label, String buttonLabel) {
		TableColumn<TableElement, Void> column = new TableColumn<TableElement, Void>(label);
		column.setCellFactory(new ButtonCell(buttonLabel));
		table.getColumns().add(column);
	}

	/**
	 * Puts buttons in map, and attach listeners
	 */
	private void turnOnButtons() {
		controls = new Hashtable<Object, String>();
		controls.put(btnDownload, btnDownload.getId());
		btnDownload.setOnAction(this);
	}

	/**
	 * Event handler for buttons and links
	 */
	@Override
	public void handle(ActionEvent event) {
		System.out.println(event.getSource().getClass().getSimpleName());
		switch (event.getSource().getClass().getSimpleName()) {
		case "Button":
			switch (controls.get(event.getSource())) {
			case "btnDownload":
				try {
					if (webDriver != null) {
						// jobSites.forEach(site -> pool.addWorker(DownloaderFactory.getDownloader(this,
						// (JobSite) site, webDriver)));

						pool.addWorker(DownloaderFactory.getDownloader(this, (JobSite) jobSites.get(2), webDriver));
						pool.runAll();
					} else {
						CrawlerUtil.showError("ChromeDriver driver loading failed.");
					}
				} catch (Exception e) {
					System.out.println("Pool init was failed.");
					e.printStackTrace();
				}
				event.consume();
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
							openEditSiteDialog((JobSite) selected);
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

	/**
	 * Opens edit dialog for {@link JobSite} data
	 * 
	 * @param jobSite
	 */
	private void openEditSiteDialog(JobSite jobSite) {

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
					jobSites.clear();
					jobSites.addAll(CrawlerApp.jobSites);
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
		jobPositions.add(position);
	}
}
