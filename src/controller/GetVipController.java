package controller;

import javafx.scene.layout.HBox;
import model.DAHModel;
import view.GetVipView;

public class GetVipController implements ViewController {
	private GetVipView getVip;
	private DAHController control;
	private DAHModel model;

	public GetVipController(DAHController cont) {
		control = cont;
		model = DAHModel.getDAHModel();
		getVip = new GetVipView(this);
	}

	public HBox getDashboard() {
		return control.getDashboard();
	}

	@Override
	public HBox[] getPanes() {
		return getVip.getPanes();
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
