package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

import dao.CsvDao;
import dao.DAHDao;
import dao.InvalidLoginException;
import dao.SQLite;

public class DAHModel {
	private static DAHModel dahModel;
	private ArrayList<Post> posts = new ArrayList<>();
	private User activeUser;
	private DAHDao data;

	private DAHModel() {
			data = new DAHDao(new SQLite());
			posts = data.getPosts();
	}
	
	public static DAHModel getDAHModel() {
		if (dahModel == null) {
			dahModel = new DAHModel();
		}
		return dahModel;
	}

	public void addPost(int id, String content, String author, int likes,
			int shares, LocalDateTime dateTime, int parentID) {
		Post newPost = new Post(id, content, author, likes, shares, dateTime,
				parentID);
		addPost(newPost);
	}

	public void addPost(Post post) {
		try {
			getPostById(post.getId());
		} catch (InvalidPostIdException e) {
			posts.add(post);
		}
		data.addNewPost(post);
	}

	public void deletePost(Post post) {
		posts.remove(post);
		data.deletePost(post.getId());
	}

	public User getActiveUser() {
		return activeUser;
	}

	public Post getPostById(int id) throws InvalidPostIdException {
		for (Post p : posts) {
			if (p.id == id) {
				return p;
			}
		}
		throw new InvalidPostIdException();
	}

	public ArrayList<Post> getPosts() {
		posts.sort(Comparator.comparing(Post::getId));
		return posts;
	}

	public int[] importPostsFrom(File file) throws FileNotFoundException {
		return CsvDao.importPostsFrom(file);
	}

	public void logOut() {
		activeUser = null;
	}

	public void registerUser(String username, String password, String firstName,
			String lastName) {
		User newUser = new User(username, password, firstName, lastName);
		activeUser = newUser;
		data.addUser(newUser);

	}

	public void removePost(int id) throws InvalidPostIdException {
		posts.remove(this.getPostById(id));
	}

	public void submitPost(Post newPost) {
		posts.add(newPost);
		data.addNewPost(newPost);
	}

	public void toggleVIP() {
		activeUser.toggleVIP();
		data.updateUser(activeUser);
	}

	public void updateActiveUserDetails(String firstName, String lastName) {
		activeUser.setFirstName(firstName);
		activeUser.setLastName(lastName);
		data.updateUser(activeUser);
	}

	public void updateActiveUserPassword(String text) {
		activeUser.setPassword(text);
		data.updateUser(activeUser);
	}

	public boolean usernameExists(String username) {
		return data.usernameExists(username);
	}

	public void validateUser(String username, String password)
			throws InvalidLoginException {
		activeUser = data.validateUser(username, password);
		if (activeUser == null)
			throw new InvalidLoginException();
	}

	public void submitPost(String postId, String content, String authorId,
			String likes, String shares, String dateTime, String parent) {
		Post post = new Post(postId, content, authorId, likes, shares, dateTime, parent);
		submitPost(post);
	}
}
