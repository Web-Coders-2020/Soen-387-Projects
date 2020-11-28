package abir.shah.messageBoardSystem.persistence;


import abir.shah.messageBoardSystem.domain.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    

    public User fetchById(String id)
    {
       for(User user : fetchAll())
           if(user.getId().equals(id))
               return user;


       throw new  RuntimeException("there is no such user with id "+id);
    }
    
    public List<User> fetchAll()
    {
        List<User> users = new ArrayList<>();
        users.add(new User("1","user1","email@user1.com","hash1"));
        users.add(new User("2","user2","email@user2.com","hash2"));
        users.add(new User("3","user3","email@user3.com","hash3"));
        users.add(new User("4","user4","email@user4.com","hash4"));
        users.add(new User("5","user5","email@user5.com","hash5"));
        users.add(new User("6","user6","email@user6.com","hash6"));
        
        return users;
    }

    public boolean isThereUserWith(String userId, String hashPassword) {
        for(User user : fetchAll())
            if(user.getId().equals(userId) && user.getHashedPassword().equals(hashPassword))
                return true;


        return false;
    }
}
