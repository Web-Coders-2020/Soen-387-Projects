package abir.shah.messageBoardSystem.usecase;


import abir.shah.messageBoardSystem.domain.entity.User;
import abir.shah.messageBoardSystem.exception.NoSuchUser;
import abir.shah.messageBoardSystem.persistence.UserRepository;

public class AuthenticateUserUsecase {

    public Token authenticate(String userId,String password)
    {
        UserRepository repository = new UserRepository();
        
        if(!repository.isThereUserWith(userId,hashPassword(password)))
            throw new NoSuchUser();

        return new Token(userId);

    }

    private String hashPassword(String password)
    {
        return password;
    }

    public User decryptToken(String content)
    {
        UserRepository repository = new UserRepository();
        return repository.fetchById(content);
    }


    public class Token
    {
        private String content;

        public Token(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }

}
