package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;


public class DAHStage {
	private Pane topPane = new Pane();
	private Pane middlePane = new Pane();
	private Pane bottomPane = new Pane();
	private VBox container = new VBox(3);
	private Stage primaryStage;
	
	public DAHStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
			container.getChildren().add(topPane);
			container.getChildren().add(middlePane);
			container.getChildren().add(bottomPane);
			setBottomPane(Footer.getFooter());
			
			Scene scene = new Scene(container,1200,400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		

	}
	
	public void setTopPane(Pane topPane) {
		this.topPane = topPane;
		container.getChildren().set(0,topPane);
	}
	
	public void setMiddlePane(Pane middlePane) {
		this.middlePane = middlePane;
		container.getChildren().set(1,middlePane);
	}
	
	public void setBottomPane(Pane bottomPane) {
		this.bottomPane = bottomPane;
		container.getChildren().set(2,bottomPane);
	}
	

}
