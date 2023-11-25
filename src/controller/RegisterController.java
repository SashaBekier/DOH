package controller;

import javafx.scene.layout.HBox;
import model.DAHModel;
import view.RegisterView;

public class RegisterController implements ViewController {
	private RegisterView register;
	private DAHController control;
	private DAHModel model;

	public RegisterController(DAHController cont) {
		control = cont;
		register = new RegisterView(this);
		model = DAHModel.getDAHModel();
	}

	@Override
	public HBox[] getPanes() {
		return register.getPanes();
	}

	public void loadLogIn() {
		control.updateStage(DAHScreen.LOG_IN);
	}

	public void registerUser(String username, String password, String firstName,
			String lastName) {
		model.registerUser(username, password, firstName, lastName);
		control.logInComplete();
		control.updateStage(DAHScreen.HOME);

	}

}
