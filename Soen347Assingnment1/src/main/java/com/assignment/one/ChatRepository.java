package com.assignment.one;

import java.util.ArrayList;
import java.util.List;

public class ChatRepository {

    private ChatRepository() {}
    private static  ChatRepository repo;
    public static  ChatRepository instance()
    {
        if(repo == null)
            repo  = new ChatRepository();

        return repo;
    }


    private ArrayList<Chat> chats = new ArrayList<>();


    public void save(String userName, String message)
    {
        chats.add(new Chat(userName,message));
    }


    public void deleteAll()
    {
        chats.clear();
    }

    public List<Chat> getAllMessges(){
        return chats;
    }

}
