package controller;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.DAHModel;
import model.Post;
import model.User;
import view.PostsView;
import java.util.List;

public class PostsController implements Controller {
	private PostsView posts;
	private DAHController control;
	private DAHModel model;
	
	public PostsController(DAHController cont) {
		control = cont;
		model = DAHModel.getDAHModel();
		posts = new PostsView(this);
		
	}
	@Override
	public HBox[] getPanes() {
		posts.updatePostPane();
		return posts.getPanes();
	}
	
	public HBox getDashboard() {
		return control.getDashboard();
	}
	public String getActiveUserDisplayName() {
		return model.getActiveUserDisplayName();
	}
	public List<Post> getPosts() {
		return model.getPosts();
	}
	public void callExportCsvView(List<Post> exportPosts) {
		// TODO Auto-generated method stub
		
	}
	public User getActiveUser() {
		return model.getActiveUser();
	}
	public void deletePost(Post post) {
		model.deletePost(post);
	}

}
