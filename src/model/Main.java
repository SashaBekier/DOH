package model;
	
import controller.DAHController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		new DAHController(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
