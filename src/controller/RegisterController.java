package controller;

import view.RegisterView;

public class RegisterController extends ViewController {
	private RegisterView register;


	public RegisterController(DAHController cont) {
		super(cont);
		register = new RegisterView(this);
		super.setView(register);
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
