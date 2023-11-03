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
		Button postsB = new Button("View Posts");
		Button addB = new Button("Add Post");
		Button importB = new Button("Import CSV");
		Button logOutB = new Button("Log Out");
		Button getVipB = new Button("Get VIP");
		topPane.getChildren().addAll(profileB,postsB, addB,importB, getVipB, logOutB);
		
		profileB.setOnAction(e -> control.loadProfile());
		postsB.setOnAction(e -> control.loadPosts());
		addB.setOnAction(e -> control.loadAdd());
		logOutB.setOnAction(e -> control.logOut());
		
		if(control.userHasVip()) {
			importB.setOnAction(e -> control.loadImportCsv());
			topPane.getChildren().remove(getVipB);
		} else {
			getVipB.setOnAction(e -> control.loadGetVip());
			importB.setOnAction(e -> control.loadGetVip());
		}
		
	}
	
	private void drawMiddle() {
		middlePane = new Pane();
	}
}
