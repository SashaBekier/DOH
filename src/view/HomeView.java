package view;

import controller.Controller;
import controller.HomeController;
import controller.LogInController;
import javafx.scene.layout.Pane;

public class HomeView extends View{
	private HomeController control;

	public HomeView(HomeController homeController) {
		control = homeController;
		drawTop();
		drawMiddle();
	}

	private void drawMiddle() {
		middlePane = new Pane();
		
	}

	private void drawTop() {
		topPane = new Pane();
		
	}

	
}
