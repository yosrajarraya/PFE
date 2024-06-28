package com.csys.template.factory;

import com.csys.template.domain.GroupUser;
import com.csys.template.dto.GroupUserDTO;
import java.util.ArrayList;
import java.util.List;

public class GroupUserFactory {
  public static GroupUserDTO groupuserToGroupUserDTO(GroupUser groupuser) {
    GroupUserDTO groupuserDTO=new GroupUserDTO();
    groupuserDTO.setGroupUser(groupuser.getGroupUser());
    groupuserDTO.setGroupe(groupuser.getGroupe());
    groupuserDTO.setUsername(groupuser.getUsername());
    return groupuserDTO;
  }

  public static GroupUser groupuserDTOToGroupUser(GroupUserDTO groupuserDTO) {
    GroupUser groupuser=new GroupUser();
    groupuser.setGroupUser(groupuserDTO.getGroupUser());
    groupuser.setGroupe(groupuserDTO.getGroupe());
    groupuser.setUsername(groupuserDTO.getUsername());
    return groupuser;
  }

  public static List<GroupUserDTO> groupuserToGroupUserDTOs(List<GroupUser> groupusers) {
    List<GroupUserDTO> groupusersDTO=new ArrayList<>();
    groupusers.forEach(x -> {
      groupusersDTO.add(groupuserToGroupUserDTO(x));
    } );
    return groupusersDTO;
  }
}