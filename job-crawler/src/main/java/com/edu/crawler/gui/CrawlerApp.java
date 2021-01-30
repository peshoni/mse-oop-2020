package com.edu.crawler.gui;

import java.util.ArrayList;

import com.edu.crawler.models.JobSite;
import com.edu.crawler.utils.CUtil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CrawlerApp extends Application {
	protected static ArrayList<JobSite> jobSites = new ArrayList<JobSite>();

	public static void main(String[] args) {
		jobSites = CUtil.getJobSitesCollection();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
			Parent root = loader.load();// FXMLLoader.load(getClass().getResource("Crawler.fxml"));
			primaryStage.setTitle("Job crawler");

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
	 * Opens the specified URI in a new browser window or tab.
	 * 
	 * @param link
	 * @throws Exception
	 */
	public void openBrowser(String link) throws Exception {
		getHostServices().showDocument(link);
	}
}
