package view;

import controller.ViewController;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public abstract class DAHView {
	protected HBox topPane;
	protected HBox middlePane;
	
	public HBox[] getPanes() {
		HBox[] panes = {topPane, middlePane};
		return panes;
	}

}
