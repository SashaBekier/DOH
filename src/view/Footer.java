package view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Footer {
	public static HBox getFooter() {
		HBox footer = new HBox();
		footer.getChildren().add(
				new Label("©2023 ~ Sasha Bekier s3335379")
				);
		return footer;
	}
}
