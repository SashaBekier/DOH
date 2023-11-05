package controller;

import javafx.scene.layout.Pane;
import model.DAHManager;
import model.Post;
import model.User;
import view.PostsView;
import java.util.List;

public class PostsController implements Controller {
	private PostsView posts;
	private DAHController control;
	private DAHManager model;
	
	public PostsController(DAHController cont) {
		control = cont;
		model = DAHManager.getManager();
		posts = new PostsView(this);
		
	}
	@Override
	public Pane[] getPanes() {
		return posts.getPanes();
	}
	
	public Pane getDashboard() {
		return control.getDashboard();
	}
	public String getActiveUserDisplayName() {
		User user = model.getActiveUser();
		String[] attribs = user.getAttributes();
		return attribs[2] + " " + attribs[3];
	}
	public List<Post> getPosts() {
		return model.getPosts();
	}
	public void callExportCsvView(List<Post> exportPosts) {
		// TODO Auto-generated method stub
		
	}

}
