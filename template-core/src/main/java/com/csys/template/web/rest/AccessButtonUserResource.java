package com.csys.template.web.rest;

import com.csys.template.dto.AccessButtonUserDTO;
import com.csys.template.dto.ButtonDTO;
import com.csys.template.dto.MenuDTO;
import com.csys.template.service.AccessButtonUserService;
import com.csys.template.util.RestPreconditions;
import java.lang.Integer;
import java.lang.String;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
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
public class AccessButtonUserResource {

    private static final String ENTITY_NAME = "accessbuttonuser";

    private final AccessButtonUserService accessbuttonuserService;

    private final Logger log = LoggerFactory.getLogger(AccessButtonUserService.class);

    public AccessButtonUserResource(AccessButtonUserService accessbuttonuserService) {
        this.accessbuttonuserService = accessbuttonuserService;
    }

    @PostMapping("/accessbuttonusers")
    public ResponseEntity<String> createAccessButtonUser(@Valid @RequestBody AccessButtonUserDTO accessbuttonuserDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        log.debug("REST request to save AccessButtonUser : {}", accessbuttonuserDTO);
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        String result = accessbuttonuserService.save(accessbuttonuserDTO);
        return ResponseEntity.created(new URI("/api/accessbuttonusers/")).body(result);
    }

    @PutMapping("/accessbuttonusers")
    public ResponseEntity<String> updateAccessButtonUser(@Valid @RequestBody AccessButtonUserDTO accessbuttonuserDTO) throws MethodArgumentNotValidException {
        String result = accessbuttonuserService.update(accessbuttonuserDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/accessbuttonusers")
    public ResponseEntity<AccessButtonUserDTO> getAccessButtonUser(@RequestParam Integer id) {
        log.debug("Request to get AccessButtonUser: {}", id);
        AccessButtonUserDTO dto = accessbuttonuserService.findAccessButtonUser(id);
        RestPreconditions.checkFound(dto, "accessbuttonuser.NotFound");
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/accessbuttonusers/access")
    public Collection<ButtonDTO> getAllAccessButtonUsers(@RequestParam(required = false) Integer idModule, @RequestParam(required = false) String username) {
        log.debug("Request to get all  AccessButtonUsers : {}");
        return accessbuttonuserService.findAll(idModule, username);
    }

    @GetMapping("/accessbuttonusers/all")
    public List<AccessButtonUserDTO> getAllAccessButtonUsers() {
        log.debug("Request to get all  AccessButtonUsers : {}");
        return accessbuttonuserService.findAll();
    }

    @DeleteMapping("/accessbuttonusers")
    public ResponseEntity<String> deleteAccessButtonUser(@RequestParam Integer id) {
        log.debug("Request to delete AccessButtonUser: {}", id);
        accessbuttonuserService.delete(id);
        return ResponseEntity.ok().build();
    }
}
