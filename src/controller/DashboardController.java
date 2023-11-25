package controller;

import javafx.scene.layout.HBox;
import model.DAHModel;
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
	public void loadAdd() {
		control.updateStage(DAHScreen.ADD);
	}
	public void loadGetVip() {
		control.updateStage(DAHScreen.GET_VIP);
	}
	public void loadImportCsv() {
		control.refreshImport();
		control.updateStage(DAHScreen.IMPORT);
	}
	public void loadLogOut() {
		model.logOut();
		control.updateStage(DAHScreen.LOG_IN);
	}
	public void loadPosts() {
		control.updateStage(DAHScreen.POSTS);
	}
	public void loadProfile() {
		control.updateStage(DAHScreen.PROFILE);
	}
	
	public boolean userHasVip() {
		if(model == null) {
			return false;
		} 
		return model.getActiveUser().hasVIP();
	}


}