package dao;

public interface DatabaseManagementSystem {
	public String getJDBCConnectionString();
	public String createUsersTable();
	public String createUserAliasTable();
	public String createPostsTable();
	public String validateLogIn();
	public String getUserByUsername();
	public String getAliasByUsername();
	public String getAllPosts();
	public String addUser();
	public String updateUser();
	public String insertSuperAdmin();
	public String getPostById();
	public String addPost();
	public String deletePostById();
	
}

























/*SAMPLE IMPLEMENTATION  
 * 
public class SQLite implements DatabaseManagementSystem {

	@Override
	public String getJDBCConnectionString() {
		return "jdbc:sqlite:dah.db";
	}

	@Override
	public String createUsersTable() {
		return "CREATE TABLE IF NOT EXISTS users ("
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
	}

	@Override
	public String createUserAliasTable() {
		return "CREATE TABLE IF NOT EXISTS userAlias ("
				+ "    username TEXT (20) REFERENCES users (username) "
				+ "                       NOT NULL,"
				+ "    alias    TEXT (20) NOT NULL"
				+ "                       UNIQUE,"
				+ "    PRIMARY KEY ("
				+ "        username,"
				+ "        alias"
				+ "    )"
				+ ");";
	}

	@Override
	public String createPostsTable() {
		return "CREATE TABLE IF NOT EXISTS posts ("
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
	}

	@Override
	public String validateLogIn() {
		return "SELECT * FROM users WHERE username = ? AND " +
				"password = ?;";
	}
	
	@Override
	public String getUserByUsername() {
		return "SELECT * FROM users WHERE username = ?;";
	}

	@Override
	public String getAliasByUsername() {
		return "SELECT alias FROM userAlias WHERE username = ?;";
	}

	@Override
	public String getAllPosts() {
		return "SELECT * FROM posts;";
	}

	@Override
	public String addUser() {
		return "INSERT INTO users (username, password, firstName, lastName, isAdmin, isVIP)"
				+ " VALUES (?,?,?,?,?,?)'";
	}

	@Override
	public String updateUser() {
		return "UPDATE users SET password = ?, firstName = ?, lastName = ?, isAdmin = ?,"
				+ "isVIP = ? WHERE username = ?";
	}

	
	@Override
	public String insertSuperAdmin() {
		return "INSERT INTO users VALUES ('superadmin','admin','Super','Admin',1,1)";
	}

	@Override
	public String getPostById() {
		return "SELECT * FROM posts WHERE postId = ?";
	}

	@Override
	public String addPost() {
		return "INSERT INTO posts VALUES (?,?,?,?,?,?,?);";
	}

	@Override
	public String deletePostById() {
		return "DELETE FROM posts WHERE postId = ?";
	}

}

 */
