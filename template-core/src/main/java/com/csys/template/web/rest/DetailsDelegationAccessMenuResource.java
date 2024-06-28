package com.csys.template.web.rest;

import com.csys.template.dto.DetailsDelegationAccessMenuDTO;
import com.csys.template.service.DetailsDelegationAccessMenuService;
import com.csys.template.util.Preconditions;
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
 * REST controller for managing DetailsDelegationAccessMenu.
 */
@RestController
@RequestMapping("/api")
public class DetailsDelegationAccessMenuResource {

    private static final String ENTITY_NAME = "detailsdelegationaccessmenu";

    private final DetailsDelegationAccessMenuService detailsdelegationaccessmenuService;

    private final Logger log = LoggerFactory.getLogger(DetailsDelegationAccessMenuService.class);

    public DetailsDelegationAccessMenuResource(DetailsDelegationAccessMenuService detailsdelegationaccessmenuService) {
        this.detailsdelegationaccessmenuService = detailsdelegationaccessmenuService;
    }

    @PostMapping("/detailsdelegationaccessmenus")
    public ResponseEntity<String> createDetailsDelegationAccessMenu(@Valid @RequestBody DetailsDelegationAccessMenuDTO detailsdelegationaccessmenuDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        log.debug("REST request to save DetailsDelegationAccessMenu : {}", detailsdelegationaccessmenuDTO);

        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        String result = detailsdelegationaccessmenuService.save(detailsdelegationaccessmenuDTO);
        return ResponseEntity.created(new URI("/api/detailsdelegationaccessmenus/")).body(result);
    }

    @PutMapping("/detailsdelegationaccessmenus")
    public ResponseEntity<String> updateDetailsDelegationAccessMenu( @Valid @RequestBody DetailsDelegationAccessMenuDTO detailsdelegationaccessmenuDTO) throws MethodArgumentNotValidException {
        String result = detailsdelegationaccessmenuService.update(detailsdelegationaccessmenuDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/detailsdelegationaccessmenus")
    public ResponseEntity<DetailsDelegationAccessMenuDTO> getDetailsDelegationAccessMenu(@RequestParam Integer id) {
        log.debug("Request to get DetailsDelegationAccessMenu: {}", id);
        DetailsDelegationAccessMenuDTO dto = detailsdelegationaccessmenuService.findDetailsDelegationAccessMenu(id);
        Preconditions.checkFound(dto, "detailsdelegationaccessmenu.NotFound");
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/detailsdelegationaccessmenus/all")
    public List<DetailsDelegationAccessMenuDTO> getAllDetailsDelegationAccessMenus() {
        log.debug("Request to get all  DetailsDelegationAccessMenus : {}");
        return detailsdelegationaccessmenuService.findAll();
    }

    @DeleteMapping("/detailsdelegationaccessmenus")
    public ResponseEntity<String> deleteDetailsDelegationAccessMenu(@RequestParam Integer id) {
        log.debug("Request to delete DetailsDelegationAccessMenu: {}", id);
        String result=detailsdelegationaccessmenuService.delete(id);
        return ResponseEntity.ok().body(result);
    }
}
