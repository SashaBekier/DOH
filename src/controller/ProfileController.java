package controller;

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
	public Pane[] getPanes() {
		return profile.getPanes();
	}
	
	public Pane getDashboard() {
		return control.getDashboard();
	}
	public String getActiveUserDisplayName() {
		User user = model.getActiveUser();
		String[] attribs = user.getAttributes();
		return attribs[2] + " " + attribs[3];
	}

}
