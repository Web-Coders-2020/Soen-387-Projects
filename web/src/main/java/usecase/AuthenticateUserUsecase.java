package abir.shah.messageBoardSystem.usecase;


import abir.shah.messageBoardSystem.persistence.UserRepository;

public class AuthenticateUserUsecase {

    public boolean isAuthenticated(String userId,String password)
    {
        UserRepository repository = new UserRepository();
        
        return repository.isThereUserWith(userId,hashPassword(password));
    }

    private String hashPassword(String password)
    {
        return password;
    }

}
