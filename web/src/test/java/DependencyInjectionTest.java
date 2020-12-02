import abir.shah.messageBoardSystem.domain.UserManagerFactory;
import org.junit.Test;

public class DependencyInjectionTest {

    @Test
    public void testingUserManagerDependencyInjection()
    {
        System.out.println(UserManagerFactory.createInstance().isAdmin("2412"));
    }

}
