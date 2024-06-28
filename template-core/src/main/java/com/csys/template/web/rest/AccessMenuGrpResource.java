package com.csys.template.web.rest;

import com.csys.template.dto.AccessMenuGrpDTO;
import com.csys.template.service.AccessMenuGrpService;
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

@RestController
@RequestMapping("/api")
public class AccessMenuGrpResource {
  private static final String ENTITY_NAME = "accessmenugrp";

  private final AccessMenuGrpService accessmenugrpService;

  private final Logger log = LoggerFactory.getLogger(AccessMenuGrpService.class);

  public AccessMenuGrpResource(AccessMenuGrpService accessmenugrpService) {
    this.accessmenugrpService=accessmenugrpService;
  }


  @PostMapping("/accessmenugrps")
  public ResponseEntity<String> createAccessMenuGrp(@Valid @RequestBody AccessMenuGrpDTO accessmenugrpDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save AccessMenuGrp : {}", accessmenugrpDTO);
  
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    String result = accessmenugrpService.save(accessmenugrpDTO);
    return ResponseEntity.created( new URI("/api/accessmenugrps/")).body(result);
  }


  @PutMapping("/accessmenugrps")
  public ResponseEntity<String> updateAccessMenuGrp(@PathVariable Integer id, @Valid @RequestBody AccessMenuGrpDTO accessmenugrpDTO) throws MethodArgumentNotValidException {
    log.debug("Request to update AccessMenuGrp: {}",id);
    String result =accessmenugrpService.update(accessmenugrpDTO);
    return ResponseEntity.ok().body(result);
  }

 
  @GetMapping("/accessmenugrps")
  public ResponseEntity<AccessMenuGrpDTO> getAccessMenuGrp(@PathVariable Integer id) {
    log.debug("Request to get AccessMenuGrp: {}",id);
    AccessMenuGrpDTO dto = accessmenugrpService.findAccessMenuGrp(id);
    RestPreconditions.checkFound(dto, "accessmenugrp.NotFound");
    return ResponseEntity.ok().body(dto);
  }


  @GetMapping("/accessmenugrps/all")
  public Collection<AccessMenuGrpDTO> getAllAccessMenuGrps() {
    log.debug("Request to get all  AccessMenuGrps : {}");
    return accessmenugrpService.findAll();
  }


  @DeleteMapping("/accessmenugrps")
  public ResponseEntity<String> deleteAccessMenuGrp(@PathVariable Integer id) {
    log.debug("Request to delete AccessMenuGrp: {}",id);
    accessmenugrpService.delete(id);
    return ResponseEntity.ok().build();
  }
}

