package com.csys.template.factory;

import com.csys.template.domain.AccessButtonGrp;
import com.csys.template.domain.AccessMenuGrp;
import com.csys.template.domain.AccessModuleGrp;
import com.csys.template.domain.GroupUser;
import com.csys.template.domain.Groupe;
import com.csys.template.dto.GroupeDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupeFactory {

    private final static Logger log = LoggerFactory.getLogger(GroupeFactory.class);

    public static GroupeDTO groupToGroupDTO(Groupe group) {

        GroupeDTO groupDTO = new GroupeDTO();
        groupDTO.setDesignation(group.getDesignation());
        groupDTO.setActive(group.getActive());
        groupDTO.setUserCreation(group.getUserCreation());
        groupDTO.setDateCreation(group.getDateCreation());
        groupDTO.setGroupe(group.getGroupe());

        if (group.getGroupUsers() != null) {
            groupDTO.setGroupUsers(GroupUserFactory.groupuserToGroupUserDTOs(group.getGroupUsers()));
        }
        if (group.getAccessModuleGrps() != null) {
            groupDTO.setAccessModuleGrps(AccessModuleGrpFactory.accessmodulegrpToAccessModuleGrpDTOs(group.getAccessModuleGrps()));

        }
        if (group.getAccessButtonGrpList() != null) {
            groupDTO.setAccessButtonGrpList(AccessButtonGrpFactory.accessbuttongrpToAccessButtonGrpDTOs(group.getAccessButtonGrpList()));

        }
        if (group.getAccessMenuGrpList() != null) {
            groupDTO.setAccessMenuGrpList(AccessMenuGrpFactory.accessmenugrpToAccessMenuGrpDTOs(group.getAccessMenuGrpList()));
        }
        return groupDTO;
    }

    private static String localDateTimeToDateWithSlash(LocalDateTime localDateTime) {

        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(localDateTime);
    }

    public static Groupe groupDTOToGroup(GroupeDTO groupDTO, Groupe group, String user) {
        if (group == null) {
            group = new Groupe();

            group.setUserCreation(user);
            group.setDateCreation(LocalDateTime.now());
            group.setGroupe(groupDTO.getGroupe());
        }
        group.setDesignation(groupDTO.getDesignation());
        group.setActive(groupDTO.isActive());
        List<GroupUser> groupuser = new ArrayList<>();
        if (groupDTO.getGroupUsers() != null) {
            groupDTO.getGroupUsers().forEach(x -> {

                GroupUser groupusere = GroupUserFactory.groupuserDTOToGroupUser(x);
                groupuser.add(groupusere);
            });
        }
        if (group.getGroupUsers() != null) {
            group.getGroupUsers().clear();
            group.getGroupUsers().addAll(groupuser);
        } else {
            group.setGroupUsers(groupuser);
        }
        List<AccessModuleGrp> accessModuleGrps = new ArrayList<>();
        groupDTO.getAccessModuleGrps().forEach(x -> {
            AccessModuleGrp accessModuleGrp = AccessModuleGrpFactory.accessmodulegrpDTOToAccessModuleGrp(x);
            accessModuleGrps.add(accessModuleGrp);
        });
        if (group.getAccessModuleGrps() != null) {
            group.getAccessModuleGrps().clear();
            group.getAccessModuleGrps().addAll(accessModuleGrps);
        } else {
            group.setAccessModuleGrps(accessModuleGrps);
        }

        List<AccessMenuGrp> accessMenuGrp = new ArrayList<>();
        groupDTO.getAccessMenuGrpList().forEach(x -> {
            AccessMenuGrp accessMenuGrps = AccessMenuGrpFactory.accessmenugrpDTOToAccessMenuGrp(x);
            accessMenuGrp.add(accessMenuGrps);

        });
        if (group.getAccessMenuGrpList() != null) {
            group.getAccessMenuGrpList().clear();
            group.getAccessMenuGrpList().addAll(accessMenuGrp);

        } else {
            group.setAccessMenuGrpList(accessMenuGrp);
        }
        List<AccessButtonGrp> accessButtonGrp = new ArrayList<>();
        groupDTO.getAccessButtonGrpList().forEach(x -> {
            AccessButtonGrp accessButtonGrps = AccessButtonGrpFactory.accessbuttongrpDTOToAccessButtonGrp(x);
            accessButtonGrp.add(accessButtonGrps);
        });

        if (group.getAccessButtonGrpList() != null) {
            group.getAccessButtonGrpList().clear();
            group.getAccessButtonGrpList().addAll(accessButtonGrp);

        } else {
            group.setAccessButtonGrpList(accessButtonGrp);
        }
        return group;
    }

    public static List<GroupeDTO> groupToGroupDTOs(List<Groupe> groups) {
        List<GroupeDTO> groupsDTO = new ArrayList<>();
        groups.forEach(x -> {
            groupsDTO.add(groupToGroupDTO(x));

        });
        return groupsDTO;
    }
}
