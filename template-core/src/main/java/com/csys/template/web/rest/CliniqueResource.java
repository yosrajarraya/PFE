package com.csys.template.web.rest;

import com.csys.template.dto.CliniqueDTO;
import com.csys.template.service.CliniqueService;
import java.lang.Integer;
import java.lang.String;
import java.lang.Void;
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
 * REST controller for managing Clinique.
 */
@RestController
@RequestMapping("/api")
public class CliniqueResource {
  private static final String ENTITY_NAME = "clinique";

  private final CliniqueService cliniqueService;

  private final Logger log = LoggerFactory.getLogger(CliniqueService.class);

  public CliniqueResource(CliniqueService cliniqueService) {
    this.cliniqueService=cliniqueService;
  }

  @PostMapping("/cliniques")
  public ResponseEntity<String> createClinique(@Valid @RequestBody CliniqueDTO cliniqueDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save Clinique : {}", cliniqueDTO);
 
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    String result = cliniqueService.save(cliniqueDTO);
    return ResponseEntity.created( new URI("/api/cliniques/")).body(result);
  }

  @PutMapping("/cliniques")
  public ResponseEntity<String> updateClinique( @Valid @RequestBody CliniqueDTO cliniqueDTO) throws MethodArgumentNotValidException {
    String result =cliniqueService.update(cliniqueDTO);
    return ResponseEntity.ok().body(result);
  }

  @GetMapping("/cliniques")
  public ResponseEntity<CliniqueDTO> getClinique( Integer id) {
    log.debug("Request to get Clinique: {}",id);
    CliniqueDTO dto = cliniqueService.findClinique(id);
    return ResponseEntity.ok().body(dto);
  }

  @GetMapping("/cliniques/filtre")
  public List<CliniqueDTO> getAllCliniques(@RequestParam(required = false) String[] name) {
    log.debug("Request to get all  Cliniques : {}");
    return cliniqueService.findAll(name);
  }


 @DeleteMapping("/cliniques")
  public ResponseEntity<String> deleteClinique(@RequestParam Integer id) {
    log.debug("Request to delete Clinique: {}",id);
     String result =cliniqueService.delete(id);
    return ResponseEntity.ok().body(result);
  }
}

