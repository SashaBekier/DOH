package controller;

import java.util.HashMap;

import dao.DAOUnavailableException;
import javafx.stage.Stage;
import model.DAHManager;
import view.DAHStage;

public class DAHController {
	private DAHManager model;
	private HashMap<String, Controller> controllers = new HashMap<String, Controller>();
	private DAHStage window;
	
	public DAHController(Stage primaryStage) {
		
		model = DAHManager.getManager();
		
		
		controllers.put("LogIn", new LogInController(this));
		controllers.put("Home", new HomeController(this));
		window = new DAHStage(primaryStage);
		updateStage("LogIn");
		
	}
	
	public void updateStage(String viewController) {
		window.setTopPane(controllers.get(viewController).getPanes()[0]);
		window.setMiddlePane(controllers.get(viewController).getPanes()[1]);
	}
}
