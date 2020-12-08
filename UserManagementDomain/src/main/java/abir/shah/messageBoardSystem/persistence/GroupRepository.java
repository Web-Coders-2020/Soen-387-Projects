package abir.shah.messageBoardSystem.persistence;


import abir.shah.messageBoardSystem.domain.entity.Group;
import abir.shah.messageBoardSystem.exception.NoSuchGroupWithName;
import abir.shah.messageBoardSystem.exception.RepetitveGroupNameIsNotAllowed;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository {

    private static List<Group> groups = new ArrayList<>();

    static {
        if(groups.isEmpty())
        {
            groups.add(new Group("admin"));
            groups.add(new Group("concordia"));
            groups.add(new Group("encs","concordia"));
            groups.add(new Group("comp","encs"));
            groups.add(new Group("soen","encs"));
        }
    }

    public List<Group> findAll()
    {
        return groups;
    }

    public void save(Group group) {

        if(isThereGroupByName(group.getName()))
            throw new RepetitveGroupNameIsNotAllowed();

        groups.add(group);
    }

    public Group findGroupByName(String parent) {
        for(Group g : groups)
            if(g.getName().equals(parent))
                return g;

        throw new NoSuchGroupWithName();
    }

    public boolean isThereGroupByName(String parent) {
        try{
            findGroupByName(parent);
            return true;
        }catch (NoSuchGroupWithName e)
        {
            return false;
        }
    }

    public void deleteAll()
    {
        groups.clear();
    }

    public List<String> findAllParentsOfGroup(String groupName) {

        String g = groupName;
        List<String> parents = new ArrayList<>();

        while(isThereGroupByName(g))
        {
            Group group = findGroupByName(g);
            if (group.isTopLevel()) break;

            g = group.getParentName();

            parents.add(g);

        }

        return parents;
    }
}
