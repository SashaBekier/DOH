package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import model.DAHManager;
import model.Post;
import model.User;

public class Dao {
	private DAHManager model;
	private DatabaseManagementSystem dbms;
	private static final String CSV_HEADER = "ID,content,author,likes,shares,date-time,main_post_id";
	
	public Dao(DatabaseManagementSystem db) throws DAOUnavailableException {
		dbms = db;
		confirmDBStructure();
	}
	
	private void confirmSuperAdmin() {
		if(!usernameExists("Superadmin")) {
			dbms.insertSuperAdmin();
		}
	}



	private void confirmDBStructure() {
		Connection db = getConnection();
		Statement statement;
		try {
			statement = db.createStatement();
			String query = dbms.createUsersTable();
			statement.execute(query);
			
			query = dbms.createUserAliasTable();
			statement.execute(query);
			
			query = dbms.createPostsTable();
			statement.execute(query);
			
			confirmSuperAdmin();
			
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public User validateUser(String username, String password){
		User validatedUser = null;
		Connection db = getConnection();
		
		try {
			PreparedStatement validateLogIn = db.prepareStatement(dbms.validateLogIn());
			validateLogIn.setString(1, username);
			validateLogIn.setString(2, password);
			ResultSet result = validateLogIn.executeQuery();
			if(result.next()) {
				validatedUser = readUser(result);
				PreparedStatement getAlias = db.prepareStatement(dbms.getAliasByUsername());
				getAlias.setString(1, username);
				ResultSet alias = getAlias.executeQuery();
				while(alias.next()) {
					validatedUser.addAuthorAlias(alias.getString(0));
				}
			}
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return validatedUser;
	}

	public ArrayList<Post> getPosts()  {
		ArrayList<Post> answer = new ArrayList<Post>();
		Connection db = getConnection();
		Statement statement;
		try {
			statement = db.createStatement();
			ResultSet result = statement.executeQuery(dbms.getAllPosts());
			while(result.next()) {
				Post newPost = new Post(result.getInt("postId"),result.getString("content"),
						result.getString("authorId"), result.getInt("likes"),
						result.getInt("shares"), LocalDateTime.parse(result.getString("postDateTime")),
						result.getInt("parentId"));
				answer.add(newPost);
			}
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}

	public void addUser(User user)  {
		Connection db = getConnection();
		try{
			PreparedStatement addUserQuery = db.prepareStatement(dbms.addUser());
			addUserQuery.setString(1, user.getUserName());
			addUserQuery.setString(2, user.getPassword());
			addUserQuery.setString(3, user.getFirstName());
			addUserQuery.setString(4, user.getLastName());
			addUserQuery.setBoolean(5, user.hasAdmin());
			addUserQuery.setBoolean(6, user.hasVIP());
			addUserQuery.executeUpdate();	
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUser(User user) {
		Connection db = getConnection();
		try{
			PreparedStatement updateUserQuery = db.prepareStatement(dbms.updateUser());
			updateUserQuery.setString(1, user.getPassword());
			updateUserQuery.setString(2, user.getFirstName());
			updateUserQuery.setString(3, user.getLastName());
			updateUserQuery.setBoolean(4, user.hasAdmin());
			updateUserQuery.setBoolean(5, user.hasVIP());
			updateUserQuery.setString(6, user.getUserName());
			updateUserQuery.executeUpdate();	
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(dbms.getJDBCConnectionString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
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

	public void addNewPost(Post newPost) {
		Connection db = getConnection();
		try {
			if(!postIdExists(newPost.getId())) {
				PreparedStatement insertPost = db.prepareStatement(dbms.addPost());
				insertPost.setInt(1, newPost.getId());
				insertPost.setString(2, newPost.getContent());
				insertPost.setString(3, newPost.getAuthorId());
				insertPost.setInt(4, newPost.getLikes());
				insertPost.setInt(5, newPost.getShares());
				insertPost.setInt(6, newPost.getParentId());
				insertPost.setString(7, newPost.getPostedAt().toString());
				insertPost.executeUpdate();		
			}
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean postIdExists(int id) {
		Connection db = getConnection();
		boolean answer = false;
		try {
			PreparedStatement checkIdQuery = db.prepareStatement(dbms.getPostById());
			checkIdQuery.setInt(1, id);
			ResultSet test = checkIdQuery.executeQuery();
			while(test.next())answer = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	public boolean usernameExists(String username) {
		Connection db = getConnection();
		boolean answer = false;
		try {
			PreparedStatement usernameQuery = db.prepareStatement(dbms.getUserByUsername());
			usernameQuery.setString(1, username);
			ResultSet test = usernameQuery.executeQuery();
			while(test.next())answer = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}
	
	public void deletePost(int id) {
		Connection db = getConnection();
		try {
			PreparedStatement deletePostById = db.prepareStatement(dbms.deletePostById());
			deletePostById.setInt(1, id);
			deletePostById.executeUpdate();
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	public int[] importPostsFrom(String fileName) throws FileNotFoundException {
		if(model == null) {
			model = DAHManager.getManager();
		}
		File myFile = new File(fileName);
		int[] result = new int[2];
		Scanner file = new Scanner(myFile);
		String line = "";
		while (file.hasNextLine()) {
			line = file.nextLine();
			if (!line.equals(CSV_HEADER)) {
				try {
					Post newPost = Post.fromCSVRepr(line);
					model.addPost(newPost);
					result[0]++;
				} catch (NumberFormatException e) {
					result[1]++;
				} catch (DateTimeParseException e) {
					result[1]++;
				} catch (ArrayIndexOutOfBoundsException e) {
					result[1]++;
				}
			}
		}
		file.close();
		return result;
	}
	
	
	

}
