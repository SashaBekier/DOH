package controller;

import javafx.scene.layout.HBox;
import model.DAHModel;
import view.DAHView;

public abstract class ViewController {
	protected DAHController control;
	protected DAHModel model;
	protected DAHView view;
	
	public ViewController(DAHController cont) {
		control = cont;
		model = DAHModel.getDAHModel();
	}
	
	public HBox[] getPanes() {
		return view.getPanes();
	};
	
	protected void setView(DAHView view) {
		this.view = view;
	}

}
