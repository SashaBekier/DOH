package controller;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import model.DAHManager;
import model.User;
import view.AddView;



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
	public ArrayList<String> getAuthorIds() {
		return model.getActiveUser().getAuthorIDs();
	}
	public boolean userHasAdmin() {
		return model.getActiveUser().hasAdmin();
	}
	public void submitPost( String postId, String content,String authorId,
			String likes, String shares, String dateTime, String parent) {
		model.submitPost(postId,content,authorId,likes,shares,dateTime,parent);
		
	}

}
