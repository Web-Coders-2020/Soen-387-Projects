package com.assignment.one;

public class Chat {
    public String user;
    public String message;

    public Chat(String user, String message) {
        super();
        this.user = user;
        this.message = message;
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return user+":"+message +"\n";
    }
}
