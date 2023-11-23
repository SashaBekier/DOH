package view;

import controller.DashboardController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import view.controls.DashButton;

public class DashboardView extends DAHView {
	private DashboardController control;

	public DashboardView(DashboardController control) {
		this.control = control;
		drawTop();
		drawMiddle();
	}
	
	private void drawTop() {
		topPane = new HBox(8);
		Button profileB = new DashButton("My Profile","assets/profile.png" );
		Button postsB = new DashButton("View Posts","assets/posts.png");
		Button addB = new DashButton("Add Post","assets/add.png");
		Button importB = new DashButton("Import CSV","assets/import.png");
		Button logOutB = new DashButton("Log Out","assets/logout.png");
		Button getVipB = new Button("Get VIP");
		logOutB.setTranslateX(250);
		
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
		middlePane = new HBox();
	}
	
	
}
