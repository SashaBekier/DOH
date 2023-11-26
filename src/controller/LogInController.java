package controller;

import dao.InvalidLoginException;
import view.LogInView;

public class LogInController extends ViewController {
	private LogInView logIn;

	public LogInController(DAHController cont) {
		super(cont);
		logIn = new LogInView(this);
		super.setView(logIn);
	}

	public void callRegisterView() {
		control.updateStage(DAHScreen.REGISTER);
	}

	public void submitLogin(String username, String password)
			throws InvalidLoginException {
		model.validateUser(username, password);
		control.logInComplete();
		control.updateStage(DAHScreen.HOME);
	}
}
