package controller;

import javafx.scene.layout.Pane;
import model.DAHManager;
import view.DashboardView;

public class DashboardController implements Controller {
	private DashboardView dashboard;
	private DAHController control;
	private DAHManager model;
	
	public DashboardController(DAHController cont) {
		control = cont;
		dashboard = new DashboardView(this);
		model = DAHManager.getManager();
	}
	@Override
	public Pane[] getPanes() {
		return dashboard.getPanes();
	}
	public void loadProfile() {
		control.updateStage("Profile");
	}
	public void loadGetVip() {
		control.updateStage("GetVip");
	}

}