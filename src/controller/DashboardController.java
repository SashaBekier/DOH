package controller;

import javafx.scene.input.Mnemonic;
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
		control.updateStage(DAHController.PROFILE);
	}
	public void loadPosts() {
		control.updateStage(DAHController.POSTS);
	}
	public void loadAdd() {
		control.updateStage(DAHController.ADD);
	}
	public void loadImportCsv() {
		control.refreshImport();
		control.updateStage(DAHController.IMPORT);
	}
	public void logOut() {
		model.logOut();
		control.updateStage(DAHController.LOG_IN);
	}
	public void loadGetVip() {
		control.updateStage(DAHController.GET_VIP);
	}
	
	public boolean userHasVip() {
		if(model == null) {
			return false;
		} 
		return model.getActiveUser().hasVIP();
	}


}