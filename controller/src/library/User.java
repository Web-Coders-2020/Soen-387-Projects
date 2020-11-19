package library;

import java.util.HashMap;

public class User {
	
	String id;
	String fullName;
	String email;
	String password;
	
	public User(String id, String fullName, String email, String password) {
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	
	public String fullName() {
		return fullName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
}
