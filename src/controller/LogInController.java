package controller;

import dao.InvalidLoginException;
import javafx.scene.layout.Pane;
import model.DAHManager;
import view.LogInView;

public class LogInController implements Controller {
	private LogInView logIn;
	private DAHController control;
	private DAHManager model;
	
	public LogInController(DAHController cont) {
		control = cont;
		logIn = new LogInView(this);
		model = DAHManager.getManager();
	}
	
	public Pane[] getPanes() {
		return logIn.getPanes();
	}

	public void submitLogin(String username, String password) throws InvalidLoginException {
			model.validateUser(username, password);
			control.logInComplete();
			control.updateStage("Home");
	}

	public void callRegisterView() {
		control.updateStage("Register");
	}
}
