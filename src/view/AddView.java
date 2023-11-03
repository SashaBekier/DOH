package view;

import controller.AddController;
import controller.Controller;
import controller.HomeController;
import controller.LogInController;
import controller.PostsController;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class AddView extends View{
	private AddController control;

	public AddView(AddController addController) {
		control = addController;
		drawTop();
		drawMiddle();
	}

	private void drawMiddle() {
		middlePane = new Pane();
		Label greeting = new Label("Add Post");
		middlePane.getChildren().add(greeting);
		
	}

	private void drawTop() {
		topPane = control.getDashboard();
		
	}

	
}
