package controller;

import javafx.scene.layout.Pane;
import model.DAHManager;
import view.HomeView;
import view.LogInView;

public class HomeController implements Controller {
	private HomeView home;
	private DAHController control;
	private DAHManager model;
	
	public HomeController(DAHController cont) {
		control = cont;
		home = new HomeView(this);
		model = DAHManager.getManager();
	}
	@Override
	public Pane[] getPanes() {
		return home.getPanes();
	}

}
