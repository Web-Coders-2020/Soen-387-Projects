package abir.shah.messageBoardSystem.usecase;

import abir.shah.messageBoardSystem.persistence.UserRepository;

import java.util.List;

public class GetGroupsUserBelongsToUsecase {

    public List<String> execute(String userId)
    {
        UserRepository userRepository = new UserRepository();
        return userRepository.fetchById(userId).getAllGroupsIAmMemberOf();
    }
}
