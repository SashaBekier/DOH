package controller;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.DAHManager;
import model.User;
import view.ProfileView;
import view.LogInView;

public class ProfileController implements Controller {
	private ProfileView profile;
	private DAHController control;
	private DAHManager model;
	
	public ProfileController(DAHController cont) {
		control = cont;
		model = DAHManager.getManager();
		profile = new ProfileView(this);
		
	}
	@Override
	public HBox[] getPanes() {
		return profile.getPanes();
	}
	
	public HBox getDashboard() {
		return control.getDashboard();
	}
	public User getActiveUser() {
		return model.getActiveUser();
	}
	public void updatePassword(String text) {
		model.updateActiveUserPassword(text);
	}
	public void updateUser(String firstName, String lastName) {
		model.updateActiveUserDetails(firstName, lastName);
		
	}
	
	public void updateView() {
		profile = new ProfileView(this);
		control.updateStage("Profile");
	}

}
