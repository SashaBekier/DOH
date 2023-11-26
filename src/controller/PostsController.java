package controller;

import java.io.File;
import java.util.List;

import dao.CsvDao;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Post;
import model.User;
import view.PostsView;

public class PostsController extends ViewController {
	private PostsView posts;

	public PostsController(DAHController cont) {
		super(cont);
		posts = new PostsView(this);
		super.setView(posts);
	}

	public void callExportCsvView(List<Post> exportPosts) {
		FileChooser fileChooser = new FileChooser();
		File saveFile = fileChooser.showSaveDialog(control.getStage());
		if (saveFile != null) {
			CsvDao.exportPosts(exportPosts, saveFile);
		}
	}

	public void deletePost(Post post) {
		model.deletePost(post);
	}

	public User getActiveUser() {
		return model.getActiveUser();
	}

	public HBox getDashboard() {
		return control.getDashboard();
	}

	@Override
	public HBox[] getPanes() {
		posts.updatePostPane();
		return posts.getPanes();
	}

	public List<Post> getPosts() {
		return model.getPosts();
	}

	public Stage getStage() {
		return control.getStage();
	}
}
