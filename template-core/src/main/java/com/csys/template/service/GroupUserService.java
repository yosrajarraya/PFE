package com.csys.template.service;

import com.csys.template.domain.GroupUser;
import com.csys.template.dto.GroupUserDTO;
import com.csys.template.factory.GroupUserFactory;
import com.csys.template.repository.GroupUserRepository;
import com.csys.template.util.Preconditions;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GroupUserService {

    private final Logger log = LoggerFactory.getLogger(GroupUserService.class);

    private final GroupUserRepository groupuserRepository;

    public GroupUserService(GroupUserRepository groupuserRepository) {
        this.groupuserRepository = groupuserRepository;
    }

    public String save(GroupUserDTO groupuserDTO) {
        log.debug("Request to save GroupUser: {}", groupuserDTO);
      GroupUser groupuser = GroupUserFactory.groupuserDTOToGroupUser(groupuserDTO);
        groupuserRepository.save(groupuser);
        return "True";
    }

    public String update(GroupUserDTO groupuserDTO) {
        
        log.debug("Request to update GroupUser: {}", groupuserDTO);
        GroupUser inBase = findOne(groupuserDTO.getGroupUser());
        Preconditions.checkBusinessLogique(inBase != null, "groupuser.NotFound");
        GroupUser groupuser = GroupUserFactory.groupuserDTOToGroupUser(groupuserDTO);
    if (groupuser.getGroupUser()== null) {
        throw new IllegalArgumentException("GroupUser ID cannot be null");
    }
        groupuserRepository.save(groupuser);
        return "True";
    }

    @Transactional(
            readOnly = true
    )
    public GroupUser findOne(Integer id) {
        log.debug("Request to get GroupUser: {}", id);
        GroupUser groupuser = groupuserRepository.findById(id).orElse(null);
        return groupuser;
    }

    @Transactional(
            readOnly = true
    )
    public GroupUserDTO findGroupUser(Integer id) {
        log.debug("Request to get GroupUser: {}", id);
        GroupUser groupuser = findOne(id);
        GroupUserDTO dto = GroupUserFactory.groupuserToGroupUserDTO(groupuser);
        return dto;
    }

   @Transactional(
            readOnly = true
    )
    public List<GroupUserDTO> findAll() {
        log.debug("Request to get All GroupUsers");
        List<GroupUser> result = groupuserRepository.findAll();
        return GroupUserFactory.groupuserToGroupUserDTOs(result);
    }

 
    
    public String delete(Integer id) {
        log.debug("Request to delete GroupUser: {}", id);
        GroupUser groupuser = findOne(id);
        Preconditions.checkBusinessLogique(groupuser != null, "GroupUser est inexistant");
        groupuserRepository.delete(groupuser);
        return "True";
    }

 
}