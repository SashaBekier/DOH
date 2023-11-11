package controller;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.DAHManager;
import view.RegisterView;

public class RegisterController implements Controller {
	private RegisterView register;
	private DAHController control;
	private DAHManager model;
	
	public RegisterController(DAHController cont) {
		control = cont;
		register = new RegisterView(this);
		model = DAHManager.getManager();
	}
	@Override
	public HBox[] getPanes() {
		return register.getPanes();
	}
	public void registerUser(String username, String password, String firstName,
			String lastName) {
		model.registerUser(username, password, firstName, lastName);
		control.logInComplete();
		control.updateStage("Home");
		
	}

}
