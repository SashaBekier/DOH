package dao;

import java.util.ArrayList;

import model.Post;
import model.User;



public interface Dao {
	public User validateUser(String username, String password) throws
		InvalidLoginException, DAOUnavailableException;
	public ArrayList<Post> getPosts() throws DAOUnavailableException;
	public void addUser(User user) throws DAOUnavailableException;
	public void updateUser(User user) throws DAOUnavailableException;
	public void savePosts(ArrayList<Post> posts) throws DAOUnavailableException;	
}
