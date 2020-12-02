package abir.shah.messageBoardSystem.domain;


import java.util.List;

public interface UserManager {


    List<String> getGroupsUserBelongTo(String userId);
    boolean isAdmin(String requesterUserId);
}
