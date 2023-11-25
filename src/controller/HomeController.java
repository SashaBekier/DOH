package controller;

import javafx.scene.layout.HBox;
import model.DAHModel;
import view.HomeView;

public class HomeController implements ViewController {
	private HomeView home;
	private DAHController control;
	private DAHModel model;

	public HomeController(DAHController cont) {
		control = cont;
		model = DAHModel.getDAHModel();
		home = new HomeView(this);
	}

	public String getActiveUserDisplayName() {
		return model.getActiveUser().getDisplayName();
	}

	public HBox getDashboard() {
		return control.getDashboard();
	}

	@Override
	public HBox[] getPanes() {
		return home.getPanes();
	}

}
