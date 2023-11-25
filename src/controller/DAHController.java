package controller;

import java.util.HashMap;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import view.DAHStage;

public class DAHController {
	private HashMap<DAHScreen, ViewController> controllers = new HashMap<>();
	private DAHStage window;
	private DashboardController dash;

	public DAHController(Stage primaryStage) {
		controllers.put(DAHScreen.LOG_IN, new LogInController(this));
		controllers.put(DAHScreen.REGISTER, new RegisterController(this));
		window = new DAHStage(primaryStage);
		updateStage(DAHScreen.LOG_IN);
	}

	public HBox getDashboard() {
		return dash.getPanes()[0];
	}

	public Stage getStage() {
		return window.getStage();
	}

	public void logInComplete() {
		dash = new DashboardController(this);
		controllers.put(DAHScreen.LOG_IN, new LogInController(this));
		controllers.put(DAHScreen.REGISTER, new RegisterController(this));
		controllers.put(DAHScreen.HOME, new HomeController(this));
		controllers.put(DAHScreen.PROFILE, new ProfileController(this));
		controllers.put(DAHScreen.GET_VIP, new GetVipController(this));
		controllers.put(DAHScreen.POSTS, new PostsController(this));
		controllers.put(DAHScreen.ADD, new AddController(this));
		controllers.put(DAHScreen.IMPORT, new ImportCsvController(this));
	}

	public void refreshImport() {
		controllers.put(DAHScreen.IMPORT, new ImportCsvController(this));
	}

	public void refreshVIP() {
		dash = new DashboardController(this);
		controllers.put(DAHScreen.PROFILE, new ProfileController(this));
		controllers.put(DAHScreen.GET_VIP, new GetVipController(this));
	}

	public void updateStage(DAHScreen page) {
		HBox[] panes = controllers.get(page).getPanes();
		window.setTopPane(panes[0]);
		window.setMiddlePane(panes[1]);
	}

}
