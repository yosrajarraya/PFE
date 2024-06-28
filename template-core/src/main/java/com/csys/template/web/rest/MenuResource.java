package com.csys.template.web.rest;

import com.csys.template.dto.MenuDTO;
import com.csys.template.service.MenuService;
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


@RestController
@RequestMapping("/api")
public class MenuResource {
  private static final String ENTITY_NAME = "menu";

  private final MenuService menuService;

  private final Logger log = LoggerFactory.getLogger(MenuService.class);

  public MenuResource(MenuService menuService) {
    this.menuService=menuService;
  }


  
  @GetMapping("/menus")
  public ResponseEntity<MenuDTO> getMenu(@RequestParam Integer id) {
    log.debug("Request to get Menu: {}",id);
    MenuDTO dto = menuService.findMenu(id);
    return ResponseEntity.ok().body(dto);
  }


  @GetMapping("/menus/all")
  public List<MenuDTO> getAllMenus() {
    log.debug("Request to get all  Menus : {}");
    return menuService.findAll();
  }


}

