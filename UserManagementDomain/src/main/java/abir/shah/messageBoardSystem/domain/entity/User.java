package abir.shah.messageBoardSystem.domain.entity;


import abir.shah.messageBoardSystem.exception.NoSuchGroupWithName;
import abir.shah.messageBoardSystem.persistence.GroupRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {

    private String id;
    private String fullName;
    private String email;
    private String hashedPassword;
    private List<String> memberOfGroups;

    public User(String id, String fullName, String email, String hashedPassword) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.hashedPassword = hashedPassword;
        memberOfGroups = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void assignAsMemberOfGroup(String parent) {
        GroupRepository repository = new GroupRepository();

        if(!repository.isThereGroupByName(parent))
            throw new NoSuchGroupWithName();

        memberOfGroups.add(parent);
    }

    public boolean isMemberOfGroup(String groupName) {
        GroupRepository repository = new GroupRepository();

        for(String group:memberOfGroups)
        {
            if(group.equals(groupName))
                return true;

            if(repository.findAllParentsOfGroup(group).contains(groupName))
                return true;
        }


        return false;
    }

    public List<String> getAllGroupsIAmMemberOf() {

        GroupRepository repository = new GroupRepository();

        Set<String> setOfGroups = new HashSet<>();

        for(String group:memberOfGroups)
        {
            setOfGroups.add(group);
            setOfGroups.addAll(repository.findAllParentsOfGroup(group));
        }

        return new ArrayList<>(setOfGroups);
    }
}
