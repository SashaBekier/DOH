package model;


public class User {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private boolean isAdmin = false;
	private boolean isVIP = false;
	
	public User(String username, String password, String firstName, 
			String lastName) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public void promoteAdmin() {
		isAdmin = true;
		isVIP = true;
	}
	public void demoteAdmin() {
		isAdmin = false;
	}
	public void promoteVIP() {
		isVIP = true;
	}
	public void demoteVIP() {
		isVIP = false;
	}
	
	
}
