package controller;

import javafx.scene.layout.HBox;
import model.User;
import view.ProfileView;

public class ProfileController extends ViewController {
	private ProfileView profile;
	
	public ProfileController(DAHController cont) {
		super(cont);
		profile = new ProfileView(this);
		super.setView(profile);
	}

	public User getActiveUser() {
		return model.getActiveUser();
	}

	public HBox getDashboard() {
		return control.getDashboard();
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
