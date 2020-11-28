package implementations;


import abir.shah.messageBoardSystem.domain.UserManager;

import java.util.List;

public class UserManagementImplementation  implements UserManager {

    @Override
    public List<String> getGroupsUserBelongTo(String userId) {

        return null;
    }

    @Override
    public boolean isAdmin(String requesterUserId) {
        return false;
    }
}
