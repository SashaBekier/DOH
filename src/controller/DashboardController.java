package controller;

import javafx.scene.layout.Pane;
import model.DAHManager;
import model.User;
import view.DashboardView;

public class DashboardController implements Controller {
	private DashboardView dashboard;
	private DAHController control;
	private DAHManager model;
	
	public DashboardController(DAHController cont) {
		control = cont;
		model = DAHManager.getManager();
		dashboard = new DashboardView(this);
	}
	@Override
	public Pane[] getPanes() {
		return dashboard.getPanes();
	}
	public void loadProfile() {
		control.updateStage("Profile");
	}
	public void loadPosts() {
		control.updateStage("Posts");
	}
	public void loadAdd() {
		control.updateStage("Add");
	}
	public void loadImportCsv() {
		control.updateStage("ImportCsv");
	}
	public void logOut() {
		model.logOut();
		control.updateStage("LogIn");
	}
	public void loadGetVip() {
		control.updateStage("GetVip");
	}
	
	public boolean userHasVip() {
		if(model == null) {
			return false;
		} 
		return model.getActiveUser().hasVIP();
	}

}