package view;

import controller.ViewController;
import controller.HomeController;
import controller.LogInController;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class HomeView extends DAHView{
	private HomeController control;

	public HomeView(HomeController homeController) {
		control = homeController;
		drawTop();
		drawMiddle();
	}

	private void drawMiddle() {
		middlePane = new HBox();
		String userDisplayName = control.getActiveUserDisplayName();
		Label greeting = new Label("Welcome to DAH " + userDisplayName);
		middlePane.getChildren().add(greeting);
		
	}

	private void drawTop() {
		topPane = control.getDashboard();
		
	}

	
}
