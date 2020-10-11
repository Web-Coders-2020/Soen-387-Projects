package com.assignment.one;

public class Chat {
	public String user;
	public String message;
	public Chat(String user, String message) {
		super();
		this.user = user;
		this.message = message;
	}

	public String toString() {
		return user+":"+message +"\n";
	}
	
}
