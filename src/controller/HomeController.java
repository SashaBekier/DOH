package controller;

import javafx.scene.layout.Pane;
import model.DAHManager;
import model.User;
import view.HomeView;
import view.LogInView;

public class HomeController implements Controller {
	private HomeView home;
	private DAHController control;
	private DAHManager model;
	
	public HomeController(DAHController cont) {
		control = cont;
		model = DAHManager.getManager();
		home = new HomeView(this);
		
	}
	@Override
	public Pane[] getPanes() {
		return home.getPanes();
	}
	
	public Pane getDashboard() {
		return control.getDashboard();
	}
	public String getActiveUserDisplayName() {
		return model.getActiveUserDisplayName();
	}

}
