package view;

import controller.Controller;
import javafx.scene.layout.Pane;

public abstract class View {
	protected Pane topPane;
	protected Pane middlePane;
	
	public Pane[] getPanes() {
		Pane[] panes = {topPane, middlePane};
		return panes;
	}

}
