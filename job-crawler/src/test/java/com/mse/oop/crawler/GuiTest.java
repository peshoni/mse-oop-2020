/**
 * 
 */
package com.mse.oop.crawler;

import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import com.mse.oop.crawler.gui.CrawlerApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//import org.testfx.internal.;
/**
 * @author Lenovo
 *
 */
public class GuiTest extends ApplicationTest {
	// private Button button;

//	 private Scene scene;
//	 @Override
//	 public void start (Stage stage) throws Exception {
//		// StackPane page = (StackPane) FXMLLoader.load(CrawlerApp.class.getResource("gui/CrawlerApp.fxml"));
////	     FXMLLoader loader = FXMLLoader.load(CrawlerApp.class.getResource("/gui/CrawlerApp.fxml"));
////	     stage.setScene(scene = new Scene(loader.load(), 300, 300));
////	     stage.show();
//
//
////	     Thread t = new Thread("JavaFX Init Thread") {
////	         public void run() {
////	             Application.launch(CrawlerApp.class, new String[0]);
////	         }
////	     };
////	     t.setDaemon(true);
////	     t.start();
////	     System.out.printf("FX App thread started\n");
////	     Thread.sleep(2000);
//	//	 scene = CrawlerApp.getMainScene();
//
////	        stage.setScene(scene);
////	        stage.show();
////
////		     System.out.printf("FX App thread started\n");
////		     Thread.sleep(500);
//	 }
	 
//	 @Test
//	 public void testBlueHasOnlyOneEntry() {
//
//	     clickOn("#saveServerAddres").write("HELLO");
//	     FxAssert. verifyThat("#labelCount", LabeledMatchers.hasText("HELLO"));
//	 }

	 
	 
//	    /**
//	     * Will be called with {@code @Before} semantics, i. e. before each test method.
//	     */
//	    @Override
//	    public void start(Stage stage) {
//	        button = new Button("click me!");
//	        button.setOnAction(actionEvent -> button.setText("clicked!"));
//	        stage.setScene(new Scene(new StackPane(button), 100, 100));
//	        stage.show();
//	    }
//	    
//	    
//	    
//	    
//	    
//	    @Test
//	    public void should_contain_button_with_text() {
//	      //  FxAssert.verifyThat("#saveServerAddres", LabeledMatchers.hasText("save settings"));
//	    }
//
//	    @Test
//	    public void when_button_is_clicked_text_changes() {
//	        // when:
//	        clickOn(".button");
//
//	        // then:
//	        FxAssert.verifyThat(".button", LabeledMatchers.hasText("clicked!"));
//	    }
}
