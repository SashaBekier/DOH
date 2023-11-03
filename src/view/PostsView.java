package view;

import controller.Controller;
import controller.HomeController;
import controller.LogInController;
import controller.PostsController;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class PostsView extends View{
	private PostsController control;

	public PostsView(PostsController postController) {
		control = postController;
		drawTop();
		drawMiddle();
	}

	private void drawMiddle() {
		middlePane = new Pane();
		Label greeting = new Label("View Posts");
		middlePane.getChildren().add(greeting);
		
	}

	private void drawTop() {
		topPane = control.getDashboard();
		
	}

	
}
