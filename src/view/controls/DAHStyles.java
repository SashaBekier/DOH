package view.controls;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class DAHStyles {
	public static final Background BLUE_BG = new Background(new BackgroundFill(Color.ALICEBLUE, new CornerRadii(5.0) , null));
	public static final Border BLUE_BORDER = new Border(new BorderStroke(Color.DARKBLUE,BorderStrokeStyle.SOLID, new CornerRadii(5.0),null));
	public static final double STAGE_HEIGHT = 600;
	public static final double STAGE_WIDTH = 800;
	
	
	
	public static Pane verticalSpacer(double size) {
		Pane pane = new Pane();
		pane.setMinHeight(size);
		return pane;
	}
	public static Pane horizontalSpacer(double size) {
		Pane pane = new Pane();
		pane.setMinWidth(size);
		return pane;
	}
}
