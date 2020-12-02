package abir.shah.messageBoardSystem.domain;


import java.util.Properties;

public class UserManagerFactory {

    private UserManagerFactory(){}

    public static UserManager createInstance()
    {
        try {

            Properties properties = new  Properties();
            properties.load(UserManagerFactory.class.getResourceAsStream("/Dependencies"));

            String implementationClassFQN = properties.getProperty("abir.shah.messageBoardSystem.domain.UserManager");
            return  (UserManager)Class.forName(implementationClassFQN).newInstance() ;

        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
