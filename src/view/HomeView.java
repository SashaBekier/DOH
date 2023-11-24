package view;

import controller.HomeController;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import view.controls.DAHStyles;

public class HomeView extends DAHView{
	private HomeController control;

	public HomeView(HomeController homeController) {
		control = homeController;
		drawTop();
		drawMiddle();
	}

	private void drawMiddle() {
		middlePane = new HBox();
		VBox container = new VBox();
		String userDisplayName = control.getActiveUserDisplayName();
		Text greeting = new Text("Welcome to DAH\n\n" + userDisplayName);
		greeting.setFont(new Font(28));
		greeting.setTextAlignment(TextAlignment.CENTER);
		container.getChildren().addAll(DAHStyles.verticalSpacer(DAHStyles.MIN_STAGE_HEIGHT/6),greeting);
		middlePane.getChildren().add(container);
	}

	private void drawTop() {
		topPane = control.getDashboard();
		
	}

	
}
