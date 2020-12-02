package abir.shah.messageBoardSystem.usecase;


import abir.shah.messageBoardSystem.domain.entity.User;
import abir.shah.messageBoardSystem.persistence.UserRepository;

public class GetUserInfoUsecase {

    public User execute(String userId)
    {
        return  new UserRepository().fetchById(userId);
    }

}
