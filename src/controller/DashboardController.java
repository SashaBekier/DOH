package controller;

import view.DashboardView;

public class DashboardController extends ViewController {
	private DashboardView dashboard;
	
	public DashboardController(DAHController cont) {
		super(cont);
		dashboard = new DashboardView(this);
		super.setView(dashboard);
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