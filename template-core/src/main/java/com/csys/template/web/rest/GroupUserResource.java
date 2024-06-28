package com.csys.template.web.rest;

import com.csys.template.dto.GroupUserDTO;
import com.csys.template.service.GroupUserService;
import com.csys.template.util.RestPreconditions;
import java.lang.Integer;
import java.lang.String;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class GroupUserResource {
  private static final String ENTITY_NAME = "groupuser";

  private final GroupUserService groupuserService;

  private final Logger log = LoggerFactory.getLogger(GroupUserService.class);

  public GroupUserResource(GroupUserService groupuserService) {
    this.groupuserService=groupuserService;
  }


  @PostMapping("/groupusers")
  public ResponseEntity<String> createGroupUser(@Valid @RequestBody GroupUserDTO groupuserDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save GroupUser : {}", groupuserDTO);
  
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    String result = groupuserService.save(groupuserDTO);
    return ResponseEntity.created( new URI("/api/groupusers/")).body(result);
  }

  @PutMapping("/groupusers")
  public ResponseEntity<String> updateGroupUser( @Valid @RequestBody GroupUserDTO groupuserDTO) throws MethodArgumentNotValidException {
  
    String result =groupuserService.update(groupuserDTO);
    return ResponseEntity.ok().body(result);
  }

 
  @GetMapping("/groupusers")
  public ResponseEntity<GroupUserDTO> getGroupUser(@RequestParam Integer id) {
    log.debug("Request to get GroupUser: {}",id);
    GroupUserDTO dto = groupuserService.findGroupUser(id);
    RestPreconditions.checkFound(dto, "groupuser.NotFound");
    return ResponseEntity.ok().body(dto);
  }

 
  @GetMapping("/groupusers/all")
  public List<GroupUserDTO> getAllGroupUsers() {
    log.debug("Request to get all  GroupUsers : {}");
    return groupuserService.findAll();
  }

  
  @DeleteMapping("/groupusers")
  public ResponseEntity<String> deleteGroupUser(@RequestParam Integer id) {
    log.debug("Request to delete GroupUser: {}",id);
    String result=groupuserService.delete(id);
    return ResponseEntity.ok().body(result);
  }
}