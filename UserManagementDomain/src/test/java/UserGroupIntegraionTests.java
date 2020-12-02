import abir.shah.messageBoardSystem.domain.entity.Group;
import abir.shah.messageBoardSystem.domain.entity.User;
import abir.shah.messageBoardSystem.exception.NoSuchGroupWithName;
import abir.shah.messageBoardSystem.persistence.GroupRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserGroupIntegraionTests {

    @Before
    public void initialize()
    {
        new GroupRepository().deleteAll();
    }

    @Test(expected = NoSuchGroupWithName.class)
    public void assigningAUserToUndefinedGroupWillThrowException()
    {
        User user = new User("id","name","","pass");
        user.assignAsMemberOfGroup("parent");
    }

    @Test
    public void ifUserIsMemberOfAGroup_ItIsAlsoMemberOfItsParents()
    {
        Group p1 = new Group("p1");
        Group p2 = new Group("p2","p1");

        User userAssignedToP2Group = new User("id1","name","","pass");
        userAssignedToP2Group.assignAsMemberOfGroup("p2");

        User userAssignedToP1Group = new User("id2","name","","pass");
        userAssignedToP1Group.assignAsMemberOfGroup("p1");

        Assert.assertTrue(userAssignedToP1Group.isMemberOfGroup("p1"));
        Assert.assertFalse(userAssignedToP1Group.isMemberOfGroup("p2"));

        Assert.assertTrue(userAssignedToP2Group.isMemberOfGroup("p2"));
        Assert.assertTrue(userAssignedToP2Group.isMemberOfGroup("p1"));
    }

}
