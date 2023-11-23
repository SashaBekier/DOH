package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import view.controls.DAHStyles;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;


public class DAHStage {
	private HBox topPane = new HBox();
	private HBox middlePane = new HBox();
	private HBox bottomPane = new HBox();
	private VBox container = new VBox(3);
	private Stage primaryStage;
	
	public DAHStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
			container.getChildren().add(topPane);
			container.getChildren().add(middlePane);
			container.getChildren().add(bottomPane);
			setBottomPane(Footer.getFooter());
			
			Scene scene = new Scene(container,DAHStyles.STAGE_WIDTH,DAHStyles.STAGE_HEIGHT);
			primaryStage.setScene(scene);
			primaryStage.show();
			container.prefWidthProperty().bind(primaryStage.widthProperty());
			container.prefHeightProperty().bind(primaryStage.heightProperty());
			bottomPane.prefHeightProperty().bind(container.heightProperty());
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		this.primaryStage.setTitle("Data Analytics Hub");

	}
	
	public void setTopPane(HBox topPane) {
		topPane.setAlignment(Pos.CENTER);
		this.topPane = topPane;
		container.getChildren().set(0,topPane);
	}
	
	public void setMiddlePane(HBox middlePane) {
		middlePane.setAlignment(Pos.CENTER);
		this.middlePane = middlePane;
		container.getChildren().set(1,middlePane);
		
	}
	
	private void setBottomPane(HBox bottomPane) {
		bottomPane.setAlignment(Pos.BOTTOM_CENTER);
		
		this.bottomPane = bottomPane;
		container.getChildren().set(2,bottomPane);
	}

	public Stage getStage() {
		return primaryStage;
	}
	

}
