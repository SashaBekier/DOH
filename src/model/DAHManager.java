/*

 * SmaManager
 *
 * Version 1.0
 *
 * 29 Sep 2023
 *
 * © 2023 Sasha Bekier & RMIT.
 */
package model;

import java.io.File;

import dao.DAOUnavailableException;
import dao.Dao;
import dao.InvalidLoginException; 
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;



/**
 * The SmaManager class is responsible for collecting, maintaining and analysing
 * the posts to be posts to be processed by the system.
 * 
 * @author Sasha Bekier
 * @version 1.0
 *
 */
public class DAHManager {
	private ArrayList<Post> posts = new ArrayList<>();
	private User activeUser;
	private static DAHManager dahManager;
	private Dao data;
	/**
	 * constructor method
	 * @throws DAOUnavailableException 
	 */

	
	private DAHManager()  {
		try {
			data = new Dao();
			posts = data.getPosts();
		} catch (DAOUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static DAHManager getManager() {
		if(dahManager == null) {
			dahManager = new DAHManager();
		}
		return dahManager;
	}
	

	/**
	 * takes validated input from the SmaUI, creates a new Post from the input
	 * data and adds it to the collection of Posts used for the analysis.
	 * 
	 * @param id       this posts id.
	 * @param content  the text body of this post.
	 * @param author   the author code for this post. The system anonymises the
	 *                 posts however post authors are given a code so that posts
	 *                 by the same author can be identified.
	 * @param likes    the recorded number of likes for this post.
	 * @param shares   the recorded number of shares for this post.
	 * @param dateTime the date and time this post was first made.
	 * @param parentId the post id of the post this post was replying to. If
	 *                 this post is a top level post then parentID should be set
	 *                 to 0.
	 */
	public void addPost(int id, String content, String author, int likes,
			int shares, LocalDateTime dateTime, int parentID) {
		Post newPost = new Post(id, content, author, likes, shares, dateTime, parentID);
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

	/**
	 * sorts the post collection by like count descending and then by date
	 * posted also descending
	 * 
	 * @return sorted ArrayList of posts
	 */
	public ArrayList<Post> getMostLikedPosts() {
		posts.sort(Comparator.comparing(Post::getLikes)
				.thenComparing(Post::getPostedAt).reversed());
		return posts;
	}

	/**
	 * sorts the post collection by share count descending and then by date
	 * posted also descending
	 * 
	 * @return sorted ArrayList of posts
	 */
	public ArrayList<Post> getMostSharedPosts() {
		posts.sort(Comparator.comparing(Post::getShares)
				.thenComparing(Post::getPostedAt).reversed());
		return posts;
	}

	/**
	 * searches post collection for specified id and returns the post if it
	 * finds a match.
	 * 
	 * @param id the target post id to be returned.
	 * @return a single Post which has the searched id.
	 * @throws InvalidPostIdException if the post id cannot be found in the
	 *                                collection.
	 */
	public Post getPostById(int id) throws InvalidPostIdException {
		for (Post p : posts) {
			if (p.id == id) {
				return p;
			}
		}
		throw new InvalidPostIdException();
	}

	/**
	 * sorts posts by id ascending and then returns them as an ArrayList
	 * 
	 * @return sorted ArrayList of Posts
	 */
	public ArrayList<Post> getPosts() {
		posts.sort(Comparator.comparing(Post::getId));
		return posts;
	}

	/**
	 * searches the post collection seeking all posts which have a parentID
	 * equal to the targetId argument.
	 * 
	 * @param targetID the id for which replies will be sought.
	 * @return ArrayList of reply posts sorted by postedAt ascending.
	 */
	public ArrayList<Post> getReplies(int targetId) {
		ArrayList<Post> answer = new ArrayList<>();
		for (Post p : posts) {
			if (p.parentId == targetId) {
				answer.add(p);
			}
		}
		answer.sort(Comparator.comparing(Post::getPostedAt));
		return answer;
	}

	/**
	 * reads a csv file with one Post csv representation per line. The CSV file
	 * requires a header line as specified by Post.CSV_HEADER and its body
	 * should be populated by Post csv representations as generated by
	 * Post.toCSVRepr
	 * 
	 * @param fileName the full file name of the target .csv file
	 * @return int array with two elements, element 0 contains the number of
	 *         Posts successfully imported while element 1 contains the number
	 *         of lines that were unable to be imported, excluding the header
	 *         line.
	 * @throws FileNotFoundException in the event that the passed file name does
	 *                               not map to a file
	 * @See Post.toCSVRepr
	 */

	/**
	 * removes a post from the collection based on its id
	 * 
	 * @param id the target id to be removed
	 * @throws InvalidPostIdException if the target id cannot be found.
	 */
	public void removePost(int id) throws InvalidPostIdException {
		posts.remove(this.getPostById(id));
	}

	public void validateUser(String username, String password) throws InvalidLoginException {
		activeUser = data.validateUser(username, password);
		if(activeUser == null) throw new InvalidLoginException();
		
	}

	public void registerUser(String username, String password, String firstName,
			String lastName) {
		User newUser = new User(username, password, firstName, lastName);
		activeUser = newUser;
		data.addUser(newUser);
				
	}
	
	public User getActiveUser() {
		return activeUser;
	}

	public void submitPost(String postId, String content, String authorId,
			String likes, String shares, String parent, String dateTime) {
		Post newPost = new Post(postId,content,authorId,likes,shares,parent,dateTime);
		posts.add(newPost);
		data.addNewPost(newPost);
		
	}

	public void logOut() {
		activeUser = null;
	}

	public void importPostsFrom(String file) throws FileNotFoundException {
		data.importPostsFrom(file);
	}

	public void updateActiveUserPassword(String text) {
		activeUser.setPassword(text);
		data.updateUser(activeUser);
		
	}
	
	public String getActiveUserDisplayName() {
		return activeUser.getDisplayName();
	}

	public void updateActiveUserDetails(String firstName, String lastName) {
		activeUser.setFirstName(firstName);
		activeUser.setLastName(lastName);
		data.updateUser(activeUser);
		
	}
	
	public void deletePost(Post post) {
		posts.remove(post);
		data.deletePost(post.getId());
	}
}
