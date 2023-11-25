package view;

import javafx.scene.layout.HBox;

public abstract class DAHView {
	protected HBox topPane;
	protected HBox middlePane;
	
	public HBox[] getPanes() {
		HBox[] panes = {topPane, middlePane};
		return panes;
	}

}
