package com.csys.template.web.rest;

import com.csys.template.dto.ButtonDTO;
import com.csys.template.service.ButtonService;
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
 * REST controller for managing Button.
 */
@RestController
@RequestMapping("/api")
public class ButtonResource {
  private static final String ENTITY_NAME = "button";

  private final ButtonService buttonService;

  private final Logger log = LoggerFactory.getLogger(ButtonService.class);

  public ButtonResource(ButtonService buttonService) {
    this.buttonService=buttonService;
  }




  @GetMapping("/buttons")
  public ResponseEntity<ButtonDTO> getButton(@RequestParam Integer id) {
    log.debug("Request to get Button: {}",id);
    ButtonDTO dto = buttonService.findButton(id);
    return ResponseEntity.ok().body(dto);
  }

 
  @GetMapping("/buttons/all")
  public List<ButtonDTO> getAllButtons() {
    log.debug("Request to get all  Buttons : {}");
    return buttonService.findAll();
  }

}

