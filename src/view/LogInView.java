package view;

import controller.LogInController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class LogInView {
	private Pane topPane;
	private Pane middlePane;
	private LogInController control;
	
	public LogInView(LogInController control) {
		this.control = control;
		drawTop();
		
		middlePane = new Pane();
	}
	
	public Pane[] getPanes() {
		Pane[] panes = {getTopPane(), getMiddlePane()};
		return panes;
	}
	
	private Pane getTopPane() {
		return topPane;
	}
	
	private Pane getMiddlePane() {
		middlePane.getChildren().add(new Label("Log In"));
		return middlePane;
	}
	
	private void drawTop() {
		topPane = new VBox(2);
		Pane blankBar = new Pane();
		blankBar.setMinHeight(50);
		topPane.getChildren().add(blankBar);
		Pane banner = new Pane();
		banner.setBackground(new Background(new BackgroundFill(
				Color.DODGERBLUE,null,null)));
		
		banner.setMinHeight(50);
		
		Text bannerText = new Text(0,33,"Data Analytics Hub");
		bannerText.setStroke(Color.ALICEBLUE);
		bannerText.setFill(Color.ALICEBLUE);
		bannerText.setFont(new Font(24));
		bannerText.setWrappingWidth(400);
		bannerText.setTextAlignment(TextAlignment.CENTER);
				
		banner.getChildren().add(bannerText);
		topPane.getChildren().add(banner);
	}
	
	
	
}
