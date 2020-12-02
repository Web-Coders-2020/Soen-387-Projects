import abir.shah.messageBoardSystem.domain.entity.Group;
import abir.shah.messageBoardSystem.exception.CircularDependencyIsNotAllowed;
import abir.shah.messageBoardSystem.exception.GroupParentDidNotExist;
import abir.shah.messageBoardSystem.exception.RepetitveGroupNameIsNotAllowed;
import abir.shah.messageBoardSystem.persistence.GroupRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GroupUnitTests {


    @Before
    public void initialize()
    {
        new GroupRepository().deleteAll();
    }

    @Test
    public void creatingATopLevelGroup()
    {
        Group group = new Group("G1");

        Assert.assertTrue(group.isTopLevel());
    }

    @Test(expected = GroupParentDidNotExist.class)
    public void creatingAChildGroupWithUnknownParentWillThrowException()
    {
        Group group = new Group("G1","Parent");
    }

    @Test
    public void creatingChildGroupWithPreExistingParentWillCreateNewGroupWhichIsNotTopLevel()
    {
        Group parent = new Group("Parent");
        Group group = new Group("G1","Parent");

        Assert.assertFalse(group.isTopLevel());
        Assert.assertTrue(parent.isTopLevel());
    }

    @Test(expected = RepetitveGroupNameIsNotAllowed.class)
    public void creatingDuplicateGroupsWillThrowException()
    {
        Group parent = new Group("Parent");

        new Group("Parent");
    }


    @Test(expected = CircularDependencyIsNotAllowed.class)
    public void creatingCircularDependencyWillThrowException()
    {
        Group parent = new Group("Parent");
        Group child1 = new Group("child1" , "Parent");
        Group child2 = new Group("child2" , "child1");

        parent.changeParent("child2");
    }
}
