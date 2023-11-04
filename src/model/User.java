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
	
	public String[] getAttributes() {
		String[] attribs = new String[6];
		attribs[0] = username;
		attribs[1] = password;
		attribs[2] = firstName;
		attribs[3] = lastName;
		if(isAdmin){
			attribs[4] = "1";
		} else {
			attribs[4] = "0";
		}
		if(isVIP) {
			attribs[5] = "1";
		} else {
			attribs[5] = "0";
		}
		return attribs;		
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
	
	
}
