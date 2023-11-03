package controller;

import javafx.scene.layout.Pane;
import model.DAHManager;
import view.DefaultView;

public class DefaultController implements Controller {
	private DefaultView Default;
	private DAHController control;
	private DAHManager model;
	
	public DefaultController(DAHController cont) {
		control = cont;
		Default = new DefaultView(this);
		model = DAHManager.getManager();
	}
	@Override
	public Pane[] getPanes() {
		// TODO Auto-generated method stub
		return null;
	}

}
