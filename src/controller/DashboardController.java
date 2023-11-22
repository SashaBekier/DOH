package controller;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.DAHModel;
import model.User;
import view.DashboardView;

public class DashboardController implements ViewController {
	private DashboardView dashboard;
	private DAHController control;
	private DAHModel model;
	
	public DashboardController(DAHController cont) {
		control = cont;
		model = DAHModel.getDAHModel();
		dashboard = new DashboardView(this);
	}
	@Override
	public HBox[] getPanes() {
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
		control.refreshImport();
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