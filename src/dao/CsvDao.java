package dao;

import java.util.ArrayList;

import model.Post;
import model.User;

public class CsvDao implements Dao{

	@Override
	public User validateUser(String username, String password)
			throws InvalidLoginException, DAOUnavailableException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Post> getPosts() throws DAOUnavailableException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(User user) throws DAOUnavailableException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) throws DAOUnavailableException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void savePosts(ArrayList<Post> posts)
			throws DAOUnavailableException {
		// TODO Auto-generated method stub
		
	}

}
