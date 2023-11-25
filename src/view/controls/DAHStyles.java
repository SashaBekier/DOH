package view.controls;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DAHStyles {
	public static final Background WINDOW_BG = new Background(new BackgroundFill(Color.FLORALWHITE, null , null));
	public static final Background BUTTON_BG = new Background(new BackgroundFill(Color.ALICEBLUE, new CornerRadii(5.0) , null));
	public static final Border BUTTON_BORDER = new Border(new BorderStroke(Color.DARKBLUE,BorderStrokeStyle.SOLID, new CornerRadii(5.0),null));
	public static final Background INVALID_BG = new Background(new BackgroundFill(Color.LIGHTPINK, new CornerRadii(5.0) , null));
	public static final Border INVALID_BORDER = new Border(new BorderStroke(Color.DARKRED,BorderStrokeStyle.SOLID, new CornerRadii(5.0),null));
	public static final Background VALID_BG = new Background(new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(5.0) , null));
	public static final Border VALID_BORDER = new Border(new BorderStroke(Color.DARKGREEN,BorderStrokeStyle.SOLID, new CornerRadii(5.0),null));
	public static final double STAGE_HEIGHT = 600;
	public static final double STAGE_WIDTH = 800;
	public static final double MIN_STAGE_HEIGHT = 400;
	public static final double MIN_STAGE_WIDTH = 400;
	public static final Font HEADING = new Font(16);
	
	
	
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
