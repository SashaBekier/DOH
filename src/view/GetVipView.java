package view;

import controller.Controller;
import controller.GetVipController;
import controller.LogInController;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class GetVipView extends View{
	private GetVipController control;

	public GetVipView(GetVipController GetVipController) {
		control = GetVipController;
		drawTop();
		drawMiddle();
	}

	private void drawMiddle() {
		middlePane = new Pane();
		String userDisplayName = control.getActiveUserDisplayName();
		Label greeting = new Label("GetVip Of " + userDisplayName);
		middlePane.getChildren().add(greeting);
		
	}

	private void drawTop() {
		topPane = control.getDashboard();
		
	}

	
}
