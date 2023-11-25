package controller;

import javafx.scene.layout.HBox;
import model.DAHModel;
import model.User;
import view.ProfileView;

public class ProfileController implements ViewController {
	private ProfileView profile;
	private DAHController control;
	private DAHModel model;

	public ProfileController(DAHController cont) {
		control = cont;
		model = DAHModel.getDAHModel();
		profile = new ProfileView(this);
	}

	public User getActiveUser() {
		return model.getActiveUser();
	}

	public HBox getDashboard() {
		return control.getDashboard();
	}

	@Override
	public HBox[] getPanes() {
		return profile.getPanes();
	}

	public void callVip() {
		control.updateStage(DAHScreen.GET_VIP);
	}

	public void updatePassword(String text) {
		model.updateActiveUserPassword(text);
	}

	public void updateUser(String firstName, String lastName) {
		model.updateActiveUserDetails(firstName, lastName);

	}

	public void updateView() {
		profile = new ProfileView(this);
		control.updateStage(DAHScreen.PROFILE);
	}

}
