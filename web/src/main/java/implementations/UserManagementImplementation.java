package implementations;


import abir.shah.messageBoardSystem.domain.UserManager;
import abir.shah.messageBoardSystem.usecase.CheckIfIsAdminUsecase;
import abir.shah.messageBoardSystem.usecase.GetGroupsUserBelongsToUsecase;

import java.util.List;

public class UserManagementImplementation  implements UserManager {

    @Override
    public List<String> getGroupsUserBelongTo(String userId) {

        GetGroupsUserBelongsToUsecase usecase = new GetGroupsUserBelongsToUsecase();
        return usecase.execute(userId);
    }

    @Override
    public boolean isAdmin(String requesterUserId) {
        CheckIfIsAdminUsecase usecase = new CheckIfIsAdminUsecase();
        return usecase.execute(requesterUserId);
    }
}
