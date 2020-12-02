package abir.shah.messageBoardSystem.usecase;


import abir.shah.messageBoardSystem.persistence.UserRepository;

public class CheckIfIsAdminUsecase {

    public boolean execute(String userId)
    {
        UserRepository userRepository = new UserRepository();
        return userRepository.fetchById(userId).isMemberOfGroup("admin");
    }
}
