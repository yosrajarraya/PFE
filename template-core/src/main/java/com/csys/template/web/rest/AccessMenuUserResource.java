package com.csys.template.web.rest;

import com.csys.template.dto.AccessMenuUserDTO;
import com.csys.template.dto.MenuDTO;
import com.csys.template.service.AccessMenuUserService;
import com.csys.template.util.RestPreconditions;
import java.lang.Integer;
import java.lang.String;
import java.lang.Void;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing AccessMenuUser.
 */
@RestController
@RequestMapping("/api")
public class AccessMenuUserResource {
  private static final String ENTITY_NAME = "accessmenuuser";

  private final AccessMenuUserService accessmenuuserService;

  private final Logger log = LoggerFactory.getLogger(AccessMenuUserService.class);

  public AccessMenuUserResource(AccessMenuUserService accessmenuuserService) {
    this.accessmenuuserService=accessmenuuserService;
  }


  @PostMapping("/accessmenuusers")
  public ResponseEntity<String> createAccessMenuUser(@Valid @RequestBody AccessMenuUserDTO accessmenuuserDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save AccessMenuUser : {}", accessmenuuserDTO);

    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    String result = accessmenuuserService.save(accessmenuuserDTO);
    return ResponseEntity.created( new URI("/api/accessmenuusers/")).body(result);
  }


  @PutMapping("/accessmenuusers")
  public ResponseEntity<String> updateAccessMenuUser(@PathVariable Integer id, @Valid @RequestBody AccessMenuUserDTO accessmenuuserDTO) throws MethodArgumentNotValidException {
    log.debug("Request to update AccessMenuUser: {}",id);
    accessmenuuserDTO.setIdAccessMenuUser(id);
    String result =accessmenuuserService.update(accessmenuuserDTO);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/accessmenuusers")
  public ResponseEntity<AccessMenuUserDTO> getAccessMenuUser(@PathVariable Integer id) {
    log.debug("Request to get AccessMenuUser: {}",id);
    AccessMenuUserDTO dto = accessmenuuserService.findAccessMenuUser(id);
    RestPreconditions.checkFound(dto, "accessmenuuser.NotFound");
    return ResponseEntity.ok().body(dto);
  }

 
  @GetMapping("/accessmenuusers/all")
  public Collection<AccessMenuUserDTO> getAllAccessMenuUsers() {
    log.debug("Request to get all  AccessMenuUsers : {}");
    return accessmenuuserService.findAll();
  }

  @GetMapping("/accessmenuusers/access")
  public Collection<MenuDTO> getAllAccessMenuUsers(@RequestParam(required = false) Integer idModule, @RequestParam(required = false) String username) {
    log.debug("Request to get all  AccessMenuUsers : {}");
    return accessmenuuserService.findAll(idModule,username);
  }
  
  @DeleteMapping("/accessmenuusers")
  public ResponseEntity<String> deleteAccessMenuUser(@PathVariable Integer id) {
    log.debug("Request to delete AccessMenuUser: {}",id);
    accessmenuuserService.delete(id);
    return ResponseEntity.ok().build();
  }
}

