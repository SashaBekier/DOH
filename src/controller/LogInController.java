package controller;

import dao.InvalidLoginException;
import javafx.scene.layout.HBox;
import model.DAHModel;
import view.LogInView;

public class LogInController implements ViewController {
	private LogInView logIn;
	private DAHController control;
	private DAHModel model;

	public LogInController(DAHController cont) {
		control = cont;
		logIn = new LogInView(this);
		model = DAHModel.getDAHModel();
	}

	public void callRegisterView() {
		control.updateStage(DAHScreen.REGISTER);
	}

	@Override
	public HBox[] getPanes() {
		return logIn.getPanes();
	}

	public void submitLogin(String username, String password)
			throws InvalidLoginException {
		model.validateUser(username, password);
		control.logInComplete();
		control.updateStage(DAHScreen.HOME);
	}
}
