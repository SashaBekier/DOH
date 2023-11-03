package view;

import controller.Controller;
import controller.ProfileController;
import controller.LogInController;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ProfileView extends View{
	private ProfileController control;

	public ProfileView(ProfileController ProfileController) {
		control = ProfileController;
		drawTop();
		drawMiddle();
	}

	private void drawMiddle() {
		middlePane = new Pane();
		String userDisplayName = control.getActiveUserDisplayName();
		Label greeting = new Label("Profile Of " + userDisplayName);
		middlePane.getChildren().add(greeting);
		
	}

	private void drawTop() {
		topPane = control.getDashboard();
		
	}

	
}
