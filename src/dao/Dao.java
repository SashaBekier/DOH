package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Post;
import model.User;

public class Dao {
	
	public Dao() throws DAOUnavailableException {
		confirmSuperAdmin();
		
	}

	public User validateUser(String username, String password){
		User validatedUser = null;
		Connection connection = getConnection();
		Statement statement;
		try {
			statement = connection.createStatement();
		
			ResultSet result = statement.executeQuery("SELECT * FROM users WHERE username = '"+username+"' AND " +
					"password = '"+password+"'");
			if(result.next()) {
				validatedUser = readUser(result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return validatedUser;
	}

	public ArrayList<Post> getPosts()  {
		// TODO Auto-generated method stub
		return null;
	}

	public void addUser(User user)  {
		// TODO Auto-generated method stub

	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	public void savePosts(ArrayList<Post> posts) {
		// TODO Auto-generated method stub
	}
	
	private Connection getConnection() {
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:dah.db");
		} catch (SQLException e) {
			// TODO Quit program
			e.printStackTrace();
		}
		return connection;
	}
	
	private void confirmSuperAdmin()  {
		Connection connection = getConnection();
		Statement statement;
		try {
			statement = connection.createStatement();
			if(statement.execute("SELECT * FROM users WHERE username = 'superadmin'")
					!= true){
				statement.executeUpdate("INSERT INTO users "
						+ "VALUES ('superadmin','admin','Super','Admin',1,1)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private User readUser(ResultSet result) throws SQLException {
		User user = new User(result.getString("username"),
				result.getString("password"),
				result.getString("firstName"),
				result.getString("lastName"));
		if(result.getInt("isAdmin")==1) {
			user.promoteAdmin();
		} else if(result.getInt("isVIP")==1) {
			user.promoteVIP();
		}
		return user;
	}
}
