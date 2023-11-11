package view;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Banner {
	
	public static VBox getBanner() {
		VBox container = new VBox(2);
		HBox blankBar = new HBox();
		
		blankBar.setMinHeight(50);
		container.getChildren().add(blankBar);
		HBox banner = new HBox();
		banner.setBackground(new Background(new BackgroundFill(
				Color.DODGERBLUE,null,null)));
		
		banner.setMinHeight(50);
		banner.setAlignment(Pos.CENTER);
		banner.prefWidthProperty().bind(container.widthProperty());
		
		Text bannerText = new Text("Data Analytics Hub");
		bannerText.setStroke(Color.ALICEBLUE);
		bannerText.setFill(Color.ALICEBLUE);
		bannerText.setFont(new Font(24));
		
		banner.getChildren().add(bannerText);
		container.getChildren().add(banner);
		container.setAlignment(Pos.CENTER);
		return container;
	}
}
