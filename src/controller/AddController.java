package controller;

import java.util.ArrayList;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.DAHModel;
import model.User;
import view.AddView;



public class AddController implements ViewController {
	private AddView add;
	private DAHController control;
	private DAHModel model;
	
	public AddController(DAHController cont) {
		control = cont;
		model = DAHModel.getDAHModel();
		add = new AddView(this);
		
	}
	@Override
	public HBox[] getPanes() {
		return add.getPanes();
	}
	
	public HBox getDashboard() {
		return control.getDashboard();
	}
	public String getActiveUserDisplayName() {
		return model.getActiveUserDisplayName();
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
	public void showPostsView() {
		control.updateStage("Posts");
		
	}
	

}
