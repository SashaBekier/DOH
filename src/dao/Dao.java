package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
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
	private static final String CSV_HEADER = "ID,content,author,likes,shares,date-time,main_post_id";
	
	
	//TODO https://www.baeldung.com/sql-injection - Prepared Statements to avoid SQL injection in JDBC
	
	public Dao() throws DAOUnavailableException {
		confirmDBStructure();
		confirmSuperAdmin();
		
	}

	private void confirmDBStructure() {
		Connection db = getConnection();
		Statement statement;
		try {
			statement = db.createStatement();
		
			String query = "CREATE TABLE IF NOT EXISTS users ("
					+ "    username  TEXT (20)   PRIMARY KEY"
					+ "                          UNIQUE"
					+ "                          NOT NULL,"
					+ "    password  TEXT (20)   NOT NULL,"
					+ "    firstName TEXT (30),"
					+ "    lastName  TEXT (30)   NOT NULL,"
					+ "    isAdmin   INTEGER (1) NOT NULL"
					+ "                          DEFAULT (0),"
					+ "    isVIP     INTEGER (1) NOT NULL"
					+ "                          DEFAULT (0) "
					+ ");";
			statement.execute(query);
			
			query = "CREATE TABLE IF NOT EXISTS userAlias ("
					+ "    username TEXT (20) REFERENCES users (username) "
					+ "                       NOT NULL,"
					+ "    alias    TEXT (20) NOT NULL"
					+ "                       UNIQUE,"
					+ "    PRIMARY KEY ("
					+ "        username,"
					+ "        alias"
					+ "    )"
					+ ");";
			statement.execute(query);
			query = "CREATE TABLE IF NOT EXISTS posts ("
					+ "    postId       INTEGER     PRIMARY KEY"
					+ "                             UNIQUE"
					+ "                             NOT NULL,"
					+ "    content      TEXT        NOT NULL,"
					+ "    authorId     TEXT (20)   NOT NULL,"
					+ "    likes        INTEGER (6) NOT NULL"
					+ "                             DEFAULT (0),"
					+ "    shares       INTEGER (6) NOT NULL"
					+ "                             DEFAULT (0),"
					+ "    parentId     INTEGER     NOT NULL"
					+ "                             DEFAULT (0),"
					+ "    postDateTime TEXT (16)   NOT NULL"
					+ ");";
			statement.execute(query);
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public User validateUser(String username, String password){
		User validatedUser = null;
		Connection db = getConnection();
		Statement statement;
		try {
			statement = db.createStatement();
		
			ResultSet result = statement.executeQuery("SELECT * FROM users WHERE username = '"+username+"' AND " +
					"password = '"+password+"'");
			if(result.next()) {
				validatedUser = readUser(result);
				ResultSet alias = statement.executeQuery("SELECT alias FROM userAlias WHERE username = '\"+username+\"'");
				if(alias.next()) {
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
			ResultSet result = statement.executeQuery("SELECT * FROM posts");
			while(result.next()) {
				Post newPost = new Post(result.getInt("postId"),result.getString("content"),
						result.getString("authorId"), result.getInt("likes"),
						result.getInt("shares"), LocalDateTime.parse(result.getString("postDateTime")),
						result.getInt("parentId"));
				answer.add(newPost);
			}

			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return answer;
	}

	public void addUser(User user)  {
		Connection db = getConnection();
		try{
			Statement statement = db.createStatement();
			String[] userAttribs = user.getAttributes();
			String query = "INSERT INTO users VALUES "
					+ "('" + userAttribs[0] + "', "
					+ "'" + userAttribs[1] + "', "
					+ "'" + userAttribs[2] + "', "
					+ "'" + userAttribs[3] + "', "
					+ "'" + userAttribs[4] + "', "
					+ "'" + userAttribs[5] + "')";
			statement.execute(query);	
			db.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	public void updateUser(User user) {
		Connection db = getConnection();
		try{
			Statement statement = db.createStatement();
			String[] userAttribs = user.getAttributes();
			String query = "UPDATE users SET "
					+ "password = '" + userAttribs[1] + "', "
					+ "firstName = '" + userAttribs[2] + "', "
					+ "lastName = '" + userAttribs[3] + "', "
					+ "isAdmin = '" + userAttribs[4] + "', "
					+ "isVIP = '" + userAttribs[5] + "'"
					+ " WHERE username = '" + userAttribs[0] + "'";
			statement.executeUpdate(query);	
			db.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

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
		Connection db = getConnection();
		Statement statement;
		try {
			statement = db.createStatement();
			int rowCount = 0;
			ResultSet res = statement.executeQuery("SELECT * FROM users WHERE username = 'superadmin'");
			while(res.next()) {
				rowCount++;
			}
			if(rowCount < 1){
				statement.executeUpdate("INSERT INTO users "
						+ "VALUES ('superadmin','admin','Super','Admin',1,1)");
			}
			db.close();
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

	public void addNewPost(Post newPost) {
		String testQuery = "SELECT * FROM posts WHERE postId = '" +newPost.getId() + "'";
		Connection db = getConnection();
		
		try {
			Statement statement = db.createStatement();
			ResultSet test = statement.executeQuery(testQuery);
			int rowCount = 0;
			while(test.next()) rowCount++;
			if(rowCount < 1) {
				String insert = "INSERT INTO posts VALUES ('" + newPost.getId() + "', "
						+ "'" + newPost.getContent() + "', "
						+ "'" + newPost.getAuthorId() + "', "
						+ "'" + newPost.getLikes() + "', "
						+ "'" + newPost.getShares() + "', "
						+ "'" + newPost.getParentId() + "', "
						+ "'" + newPost.getPostedAt() + "')";
				statement.executeUpdate(insert);		
			}
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	public void deletePost(int id) {
		Connection db = getConnection();
		
		try {
			Statement statement = db.createStatement();
			statement.executeUpdate("DELETE FROM posts WHERE postId = " + id);
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
