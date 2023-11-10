package controller;

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
	public Pane[] getPanes() {
		return getVip.getPanes();
	}
	
	public Pane getDashboard() {
		return control.getDashboard();
	}
	public String getActiveUserDisplayName() {
		return model.getActiveUserDisplayName();
	}

}
