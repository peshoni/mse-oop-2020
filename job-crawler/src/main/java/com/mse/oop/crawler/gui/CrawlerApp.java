package com.mse.oop.crawler.gui;

import java.util.ArrayList;

import com.mse.oop.crawler.models.JobSite;
import com.mse.oop.crawler.utils.CrawlerUtil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CrawlerApp extends Application {
	protected static ArrayList<JobSite> jobSites = new ArrayList<JobSite>();

	public static void main(String[] args) {
		jobSites = CrawlerUtil.getJobSitesCollection();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Crawler.fxml"));
			Parent root = loader.load();
			primaryStage.setTitle("crawler");
			primaryStage.setMaximized(true);
			MainController controller = loader.getController();
			controller.setHostServices(getHostServices());
			primaryStage.setScene(new Scene(root, 800, 600));
			primaryStage.show();

			primaryStage.setOnCloseRequest(s -> {
				s.consume();
				primaryStage.close();
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens the specified URI in a new browser window.
	 * 
	 * @param link
	 * @throws Exception
	 */
	public void openBrowser(String link) throws Exception {
		getHostServices().showDocument(link);
	}
}
