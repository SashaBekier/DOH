package controller;

import java.util.HashMap;

import dao.DAOUnavailableException;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.DAHModel;
import view.DAHStage;

public class DAHController {
	private DAHModel model;
	private HashMap<String, Controller> controllers = new HashMap<String, Controller>();
	private DAHStage window;
	private DashboardController dash;
	
	public DAHController(Stage primaryStage) {
		
		model = DAHModel.getDAHModel();
		
		controllers.put("LogIn", new LogInController(this));
		controllers.put("Register", new RegisterController(this));
		window = new DAHStage(primaryStage);
		updateStage("LogIn");
		
	}
	
	public void updateStage(String viewController) {
		window.setTopPane(controllers.get(viewController).getPanes()[0]);
		window.setMiddlePane(controllers.get(viewController).getPanes()[1]);
	}

	public HBox getDashboard() {
		return dash.getPanes()[0];
	}
	
	public void logInComplete() {
		dash = new DashboardController(this);
		
		controllers.put("Home", new HomeController(this));
		controllers.put("Profile", new ProfileController(this));
		controllers.put("GetVip", new GetVipController(this));
		controllers.put("Posts", new PostsController(this));
		controllers.put("Add", new AddController(this));
		controllers.put("ImportCsv", new ImportCsvController(this));
		
		
	}

	
}
