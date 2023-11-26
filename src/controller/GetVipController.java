package controller;

import javafx.scene.layout.HBox;
import view.GetVipView;

public class GetVipController extends ViewController {
	private GetVipView getVip;
	
	public GetVipController(DAHController cont) {
		super(cont);
		getVip = new GetVipView(this);
		super.setView(getVip);
	}

	public HBox getDashboard() {
		return control.getDashboard();
	}

	public void toggleVIP() {
		model.toggleVIP();
		control.refreshVIP();
		control.updateStage(DAHScreen.PROFILE);
	}

	public boolean userHasVIP() {
		return model.getActiveUser().hasVIP();
	}

}
