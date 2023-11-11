package controller;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.DAHManager;
import model.User;
import view.GetVipView;
import view.LogInView;

public class GetVipController implements Controller {
	private GetVipView getVip;
	private DAHController control;
	private DAHManager model;
	
	public GetVipController(DAHController cont) {
		control = cont;
		model = DAHManager.getManager();
		getVip = new GetVipView(this);
		
	}
	@Override
	public HBox[] getPanes() {
		return getVip.getPanes();
	}
	
	public HBox getDashboard() {
		return control.getDashboard();
	}
	public String getActiveUserDisplayName() {
		return model.getActiveUserDisplayName();
	}

}
