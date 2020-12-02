package abir.shah.messageBoardSystem.domain.entity;

import abir.shah.messageBoardSystem.exception.CircularDependencyIsNotAllowed;
import abir.shah.messageBoardSystem.exception.GroupParentDidNotExist;
import abir.shah.messageBoardSystem.persistence.GroupRepository;

public class Group {

    private String parent;
    private String name;
    private GroupRepository groupRepository = new GroupRepository();

    public Group(String name) {
        this.name = name;
        groupRepository.save(this);
    }

    public Group(String name, String parent) {

        if(!groupRepository.isThereGroupByName(parent))
            throw new GroupParentDidNotExist();

        this.parent = parent;
        this.name = name;

        groupRepository.save(this);
    }

    public boolean isTopLevel() {
        return parent==null || parent.isBlank();
    }

    public String getName() {
        return name;
    }

    public String getParentName() {
        return parent;
    }

    public void changeParent(String newParent) {
        if(groupRepository.findAllParentsOfGroup(newParent).contains(name))
            throw new CircularDependencyIsNotAllowed();

        parent = newParent;
    }
}
