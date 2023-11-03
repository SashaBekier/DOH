package controller;

import java.util.HashMap;

import dao.DAOUnavailableException;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.DAHManager;
import view.DAHStage;

public class DAHController {
	private DAHManager model;
	private HashMap<String, Controller> controllers = new HashMap<String, Controller>();
	private DAHStage window;
	private DashboardController dash;
	
	public DAHController(Stage primaryStage) {
		
		model = DAHManager.getManager();
		dash = new DashboardController(this);
		
		controllers.put("LogIn", new LogInController(this));
		controllers.put("Register", new RegisterController(this));
		window = new DAHStage(primaryStage);
		updateStage("LogIn");
		
	}
	
	public void updateStage(String viewController) {
		window.setTopPane(controllers.get(viewController).getPanes()[0]);
		window.setMiddlePane(controllers.get(viewController).getPanes()[1]);
	}

	public Pane getDashboard() {
		return dash.getPanes()[0];
	}
	
	public void logInComplete() {
		controllers.put("Home", new HomeController(this));
		controllers.put("Profile", new ProfileController(this));
		controllers.put("GetVip", new GetVipController(this));
		
		
	}
}
