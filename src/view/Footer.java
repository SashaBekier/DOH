package view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Footer {
	public static HBox getFooter() {
		HBox footer = new HBox();
		footer.getChildren().add(
				new Label("©2023 ~ Sasha Bekier s3335379 - Default Admin"
						+ " - Username: superadmin Password: admin"));
		return footer;
	}
}
