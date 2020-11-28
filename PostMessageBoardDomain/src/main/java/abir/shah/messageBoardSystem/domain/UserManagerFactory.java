package abir.shah.messageBoardSystem.domain;


import java.util.ArrayList;
import java.util.List;

public class UserManagerFactory {

    private UserManagerFactory(){}

    public static UserManager createInstance()
    {
           return new UserManager() {
               @Override
               public List<String> getGroupsUserBelongTo(String userId) {
                   return new ArrayList<String>(List.of("G1","G2"));
               }

               @Override
               public boolean isAdmin(String requesterUserId) {
                   return  requesterUserId.equals("1");
               }
           };
    }
}
