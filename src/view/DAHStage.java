package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import view.controls.DAHStyles;
import javafx.scene.layout.VBox;

public class DAHStage {
	private HBox topPane = new HBox();
	private HBox middlePane = new HBox();
	private HBox bottomPane = new HBox();
	private VBox container = new VBox(3);
	private Scene primaryScene;
	private Stage primaryStage;

	public DAHStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		container.getChildren().add(topPane);
		container.getChildren().add(middlePane);
		container.getChildren().add(bottomPane);
		setBottomPane(Footer.getFooter());

		primaryScene = new Scene(container, DAHStyles.STAGE_WIDTH,
				DAHStyles.STAGE_HEIGHT);
		primaryStage.setScene(primaryScene);
		primaryStage.show();
		container.prefWidthProperty().bind(primaryStage.widthProperty());
		container.prefHeightProperty().bind(primaryStage.heightProperty());
		bottomPane.prefHeightProperty().bind(container.heightProperty());
		container.setBackground(DAHStyles.WINDOW_BG);

		this.primaryStage.setTitle("Data Analytics Hub");
		this.primaryStage.setMinWidth(DAHStyles.MIN_STAGE_WIDTH);
		this.primaryStage.setMinHeight(DAHStyles.MIN_STAGE_HEIGHT);
	}

	public void setTopPane(HBox topPane) {
		topPane.setAlignment(Pos.CENTER);
		this.topPane = topPane;
		container.getChildren().set(0, topPane);
	}

	public void setMiddlePane(HBox middlePane) {
		middlePane.setAlignment(Pos.CENTER);
		this.middlePane = middlePane;
		container.getChildren().set(1, middlePane);

	}
	
	public Stage getStage() {
		return primaryStage;
	}

	private void setBottomPane(HBox bottomPane) {
		bottomPane.setAlignment(Pos.BOTTOM_CENTER);
		this.bottomPane = bottomPane;
		container.getChildren().set(2, bottomPane);
	}

}
