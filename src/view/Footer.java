package view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Footer {
	public static Pane getFooter() {
		Pane footer = new Pane();
		footer.getChildren().add(
				new Label("©2023 ~ Sasha Bekier s3335379")
				);
		return footer;
	}
}
