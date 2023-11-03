package view;

import controller.DashboardController;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class DashboardView extends View {
	private DashboardController control;

	public DashboardView(DashboardController control) {
		this.control = control;
		drawTop();
		drawMiddle();
	}
	
	private void drawTop() {
		topPane = new HBox(8);
		Button profileB = new Button("Profile");
		Button getVipB = new Button("Get VIP");
		topPane.getChildren().addAll(profileB, getVipB);
		
		profileB.setOnAction(e -> control.loadProfile());
		getVipB.setOnAction(e -> control.loadGetVip());
	}
	
	private void drawMiddle() {
		middlePane = new Pane();
	}
}
