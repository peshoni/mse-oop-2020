/**
 * 
 */
package com.mse.db.pharma;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Petar Ivanov - pesho02@abv.bg
 *
 */
public class PharmaApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PharmaApp.fxml"));
			Parent root = loader.load();
			primaryStage.setTitle("Аптечен склад");
			primaryStage.setMaximized(true);
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
}
