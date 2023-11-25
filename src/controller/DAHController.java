package controller;

import java.util.HashMap;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.DAHModel;
import view.DAHStage;

public class DAHController {
	//private DAHModel model;
	private HashMap<Integer, ViewController> controllers = new HashMap<Integer, ViewController>();
	private DAHStage window;
	private DashboardController dash;
	
	public static final int LOG_IN = 0;
	public static final int REGISTER = 1;
	public static final int HOME = 2;
	public static final int PROFILE = 3;
	public static final int GET_VIP = 4;
	public static final int POSTS = 5;
	public static final int ADD = 6;
	public static final int IMPORT = 7;
	
	
	public DAHController(Stage primaryStage) {
		
		//model = DAHModel.getDAHModel();
		
		controllers.put(LOG_IN, new LogInController(this));
		controllers.put(REGISTER, new RegisterController(this));
		window = new DAHStage(primaryStage);
		
		updateStage(LOG_IN);
	}
	
	public void updateStage(int page) {
		HBox[] panes = controllers.get(page).getPanes();
		window.setTopPane(panes[0]);
		window.setMiddlePane(panes[1]);
	}

	public HBox getDashboard() {
		return dash.getPanes()[0];
	}
	
	public void logInComplete() {
		dash = new DashboardController(this);
		
		controllers.put(LOG_IN, new LogInController(this));
		controllers.put(REGISTER, new RegisterController(this));
		controllers.put(HOME, new HomeController(this));
		controllers.put(PROFILE, new ProfileController(this));
		controllers.put(GET_VIP, new GetVipController(this));
		controllers.put(POSTS, new PostsController(this));
		controllers.put(ADD, new AddController(this));
		controllers.put(IMPORT, new ImportCsvController(this));
		
		
	}

	public void refreshVIP() {
		dash = new DashboardController(this);
		controllers.put(PROFILE, new ProfileController(this));
		controllers.put(GET_VIP, new GetVipController(this));
		
	}
	
	public void refreshImport() {
		controllers.put(IMPORT, new ImportCsvController(this));
	}

	public Stage getStage() {
		return window.getStage();
	}


	

	
}
