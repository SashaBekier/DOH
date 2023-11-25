package view;

import controller.GetVipController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GetVipView extends DAHView{
	private GetVipController control;

	public GetVipView(GetVipController GetVipController) {
		control = GetVipController;
		drawTop();
		drawMiddle();
	}

	private void drawMiddle() {
		middlePane = new HBox();
		VBox container = new VBox();
		Image banner;
		Button toggleVIP;
		if(control.userHasVIP()) {
			banner = new Image("assets/noVIP.png");
			toggleVIP = new Button("Unsubscribe");
		} else {
			banner = new Image("assets/getVIP.png");
			toggleVIP = new Button("Yes!");
		}
		toggleVIP.setPrefWidth(banner.getWidth());
		container.getChildren().addAll(new ImageView(banner),toggleVIP);
		toggleVIP.setOnAction(e -> control.toggleVIP());
		middlePane.getChildren().add(container);
	}

	private void drawTop() {
		topPane = control.getDashboard();
	}
}
