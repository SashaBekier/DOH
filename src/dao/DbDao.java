package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Post;
import model.User;

public class DbDao {
	
	public void testConnection() {
		try {
			getConnection();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public User validateUser(String username, String password)
			throws InvalidLoginException, DAOUnavailableException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Post> getPosts() throws DAOUnavailableException {
		// TODO Auto-generated method stub
		return null;
	}

	public void addUser(User user) throws DAOUnavailableException {
		// TODO Auto-generated method stub

	}

	public void updateUser(User user) throws DAOUnavailableException {
		// TODO Auto-generated method stub

	}

	public void savePosts(ArrayList<Post> posts)
			throws DAOUnavailableException {
		// TODO Auto-generated method stub

	}
	
	private Connection getConnection() throws SQLException{
		Connection connection = DriverManager.getConnection("jdbc:sqlite:dah");
		return connection;
	}

}
