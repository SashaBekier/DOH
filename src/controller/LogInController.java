package controller;

import javafx.scene.layout.Pane;
import view.LogInView;

public class LogInController implements Controller {
	private LogInView logIn;
	private DAHController control;
	
	public LogInController(DAHController cont) {
		control = cont;
		logIn = new LogInView(this);
	}
	
	public Pane[] getPanes() {
		return logIn.getPanes();
	}
}
