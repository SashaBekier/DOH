package controller;

import javafx.scene.layout.Pane;
import model.DAHManager;
import model.User;
import view.PostsView;
import view.AddView;
import view.LogInView;

public class AddController implements Controller {
	private AddView add;
	private DAHController control;
	private DAHManager model;
	
	public AddController(DAHController cont) {
		control = cont;
		model = DAHManager.getManager();
		add = new AddView(this);
		
	}
	@Override
	public Pane[] getPanes() {
		return add.getPanes();
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
