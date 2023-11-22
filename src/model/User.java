package model;

import java.util.ArrayList;

public class User {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private boolean isAdmin = false;
	private boolean isVIP = false;
	private ArrayList<String> authorIds = new ArrayList<String>();
	
	public User(String username, String password, String firstName, 
			String lastName) {
		this.username = username;
		authorIds.add(username);
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public void addAuthorAlias(String alias) {
		authorIds.add(alias);
	}
	
	public String getDisplayName() {
		return firstName + " " + lastName;
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
	
	public boolean hasAdmin() {
		return isAdmin;
	}
	
	public boolean hasVIP() {
		return isVIP;
	}
	
	public ArrayList<String> getAuthorIDs() {
		return authorIds;
	}

	public String getUserName() {
		return username;
	}
	
	public boolean matchesPassword(String text) {
		return text.equals(password);
	}

	public void setPassword(String text) {
		password = text;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;		
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;		
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public void toggleVIP() {
		isVIP = !isVIP;
	}
	
	
}
