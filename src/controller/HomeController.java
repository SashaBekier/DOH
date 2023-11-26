package controller;

import javafx.scene.layout.HBox;
import view.HomeView;

public class HomeController extends ViewController {
	private HomeView home;
	
	public HomeController(DAHController cont) {
		super(cont);
		home = new HomeView(this);
		super.setView(home);
	}

	public String getActiveUserDisplayName() {
		return model.getActiveUser().getDisplayName();
	}

	public HBox getDashboard() {
		return control.getDashboard();
	}

}
