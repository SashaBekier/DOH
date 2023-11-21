package controller;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.DAHModel;
import model.User;
import view.HomeView;
import view.LogInView;

public class HomeController implements ViewController {
	private HomeView home;
	private DAHController control;
	private DAHModel model;
	
	public HomeController(DAHController cont) {
		control = cont;
		model = DAHModel.getDAHModel();
		home = new HomeView(this);
		
	}
	@Override
	public HBox[] getPanes() {
		return home.getPanes();
	}
	
	public HBox getDashboard() {
		return control.getDashboard();
	}
	public String getActiveUserDisplayName() {
		return model.getActiveUserDisplayName();
	}

}
