package view;

import controller.DashboardController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import view.controls.DashButton;

public class DashboardView extends DAHView {
	private DashboardController control;

	public DashboardView(DashboardController control) {
		this.control = control;
		drawTop();
		drawMiddle();
	}
	
	private void drawTop() {
		topPane = new HBox(5);
		topPane.setPadding(new Insets(5));
		
		Button profileB = new DashButton("My _Profile","assets/profile.png" );
		Button postsB = new DashButton("_View Posts","assets/posts.png");
		Button addB = new DashButton("_Add Post","assets/add.png");
		Button importB = new DashButton("_Import CSV","assets/import.png");
		Button logOutB = new DashButton("Log Out","assets/logout.png");
		logOutB.setCancelButton(true);
		HBox.setMargin(logOutB,new Insets(0,5,0,5));
		Button getVipB = new DashButton("Get VIP","assets/getVIPButton.png");
		Region rightR = new Region();
		HBox.setHgrow(rightR, Priority.ALWAYS);
		
		topPane.getChildren().addAll(profileB,postsB, addB,importB, getVipB,rightR, logOutB);
		
		profileB.setOnAction(e -> control.loadProfile());
		postsB.setOnAction(e -> control.loadPosts());
		addB.setOnAction(e -> control.loadAdd());
		importB.setOnAction(e -> control.loadImportCsv());
		getVipB.setOnAction(e -> control.loadGetVip());
		logOutB.setOnAction(e -> control.logOut());
		
		if(control.userHasVip()) {
			topPane.getChildren().remove(getVipB);
		} else {
			topPane.getChildren().remove(importB);
		}
		
	}
	
	private void drawMiddle() {
		middlePane = new HBox();
	}
	
	
}
