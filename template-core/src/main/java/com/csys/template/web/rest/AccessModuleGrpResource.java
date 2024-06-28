package com.csys.template.web.rest;

import com.csys.template.dto.AccessModuleGrpDTO;
import com.csys.template.service.AccessModuleGrpService;
import java.lang.Integer;
import java.lang.String;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import liquibase.pro.packaged.s;
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
 * REST controller for managing AccessModuleGrp.
 */
@RestController
@RequestMapping("/api")
public class AccessModuleGrpResource {
  private static final String ENTITY_NAME = "accessmodulegrp";

  private final AccessModuleGrpService accessmodulegrpService;

  private final Logger log = LoggerFactory.getLogger(AccessModuleGrpService.class);

  public AccessModuleGrpResource(AccessModuleGrpService accessmodulegrpService) {
    this.accessmodulegrpService=accessmodulegrpService;
  }


  @PostMapping("/accessmodulegrps")
  public ResponseEntity<String> createAccessModuleGrp(@Valid @RequestBody AccessModuleGrpDTO accessmodulegrpDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save AccessModuleGrp : {}", accessmodulegrpDTO);
  
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    String result = accessmodulegrpService.save(accessmodulegrpDTO);
    return ResponseEntity.created( new URI("/api/accessmodulegrps/")).body(result);
  }

  
  @PutMapping("/accessmodulegrps")
  public ResponseEntity<String> updateAccessModuleGrp( @Valid @RequestBody AccessModuleGrpDTO accessmodulegrpDTO) throws MethodArgumentNotValidException {
    String result =accessmodulegrpService.update(accessmodulegrpDTO);
    return ResponseEntity.ok().body(result);
    
  }


  @GetMapping("/accessmodulegrps")
  public ResponseEntity<AccessModuleGrpDTO> getAccessModuleGrp(@RequestParam Integer id) {
    log.debug("Request to get AccessModuleGrp: {}",id);
    AccessModuleGrpDTO dto = accessmodulegrpService.findAccessModuleGrp(id);
    return ResponseEntity.ok().body(dto);
  }

  
  @GetMapping("/accessmodulegrps/all")
  public List<AccessModuleGrpDTO> getAllAccessModuleGrps() {
    log.debug("Request to get all  AccessModuleGrps : {}");
    return accessmodulegrpService.findAll();
  }


  @DeleteMapping("/accessmodulegrps")
  public ResponseEntity<String> deleteAccessModuleGrp(@PathVariable Integer id) {
    log.debug("Request to delete AccessModuleGrp: {}",id);
    accessmodulegrpService.delete(id);
    return ResponseEntity.ok().build();
  }
}

