package dao;

public interface DBMS {
	public String addPost();
	public String addUser();
	public String createPostsTable();
	public String createUserAliasTable();
	public String createUsersTable();
	public String deletePostById();
	public String getAliasByUsername();
	public String getAllPosts();
	public String getJDBCConnectionString();
	public String getPostById();
	public String getUserByUsername();
	public String insertSuperAdmin();
	public String updateUser();
	public String validateLogIn();
}