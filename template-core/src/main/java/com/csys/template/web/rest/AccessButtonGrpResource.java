package com.csys.template.web.rest;

import com.csys.template.dto.AccessButtonGrpDTO;
import com.csys.template.service.AccessButtonGrpService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing AccessButtonGrp.
 */
@RestController
@RequestMapping("/api")
public class AccessButtonGrpResource {
  private static final String ENTITY_NAME = "accessbuttongrp";

  private final AccessButtonGrpService accessbuttongrpService;

  private final Logger log = LoggerFactory.getLogger(AccessButtonGrpService.class);

  public AccessButtonGrpResource(AccessButtonGrpService accessbuttongrpService) {
    this.accessbuttongrpService=accessbuttongrpService;
  }


  @PostMapping("/accessbuttongrps")
  public ResponseEntity<String> createAccessButtonGrp(@Valid @RequestBody AccessButtonGrpDTO accessbuttongrpDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save AccessButtonGrp : {}", accessbuttongrpDTO);
  
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    String result = accessbuttongrpService.save(accessbuttongrpDTO);
    return ResponseEntity.created( new URI("/api/accessbuttongrps/")).body(result);
  }


  @PutMapping("/accessbuttongrps")
  public ResponseEntity<String> updateAccessButtonGrp(@Valid @RequestBody AccessButtonGrpDTO accessbuttongrpDTO) throws MethodArgumentNotValidException {

    String result =accessbuttongrpService.update(accessbuttongrpDTO);
    return ResponseEntity.ok().body(result);
  }

 
  @GetMapping("/accessbuttongrps")
  public ResponseEntity<AccessButtonGrpDTO> getAccessButtonGrp(@RequestParam Integer id) {
    log.debug("Request to get AccessButtonGrp: {}",id);
    AccessButtonGrpDTO dto = accessbuttongrpService.findAccessButtonGrp(id);
      return ResponseEntity.ok().body(dto);
  }
 
  

  @GetMapping("/accessbuttongrps/all")
  public List<AccessButtonGrpDTO> getAllAccessButtonGrps() {
    log.debug("Request to get all  AccessButtonGrps : {}");
    return accessbuttongrpService.findAll();
  }


  @DeleteMapping("/accessbuttongrps")
  public ResponseEntity<String> deleteAccessButtonGrp(@RequestParam Integer id) {
    log.debug("Request to delete AccessButtonGrp: {}",id);
    String result =accessbuttongrpService.delete(id);
    return ResponseEntity.ok().body(result);
  }
}

