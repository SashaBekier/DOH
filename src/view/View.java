package view;

import controller.Controller;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public abstract class View {
	protected HBox topPane;
	protected HBox middlePane;
	
	public HBox[] getPanes() {
		HBox[] panes = {topPane, middlePane};
		return panes;
	}

}
