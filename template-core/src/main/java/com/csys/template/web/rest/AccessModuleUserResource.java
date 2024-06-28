package com.csys.template.web.rest;

import com.csys.template.dto.AccessModuleUserDTO;
import com.csys.template.dto.ModuleDTO;
import com.csys.template.service.AccessModuleUserService;
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
public class AccessModuleUserResource {

    private static final String ENTITY_NAME = "accessmoduleuser";

    private final AccessModuleUserService accessmoduleuserService;

    private final Logger log = LoggerFactory.getLogger(AccessModuleUserService.class);

    public AccessModuleUserResource(AccessModuleUserService accessmoduleuserService) {
        this.accessmoduleuserService = accessmoduleuserService;
    }

    @PostMapping("/accessmoduleusers")
    public ResponseEntity<String> createAccessModuleUser(@Valid @RequestBody AccessModuleUserDTO accessmoduleuserDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        log.debug("REST request to save AccessModuleUser : {}", accessmoduleuserDTO);

        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        String result = accessmoduleuserService.save(accessmoduleuserDTO);
        return ResponseEntity.created(new URI("/api/accessmoduleusers/")).body(result);
    }

    @PutMapping("/accessmoduleusers")
    public ResponseEntity<String> updateAccessModuleUser(@Valid @RequestBody AccessModuleUserDTO accessmoduleuserDTO) throws MethodArgumentNotValidException {
        String result = accessmoduleuserService.update(accessmoduleuserDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/accessmoduleusers")
    public ResponseEntity<AccessModuleUserDTO> getAccessModuleUser(@RequestParam Integer id) {
        log.debug("Request to get AccessModuleUser: {}", id);
        AccessModuleUserDTO dto = accessmoduleuserService.findAccessModuleUser(id);
        RestPreconditions.checkFound(dto, "accessmoduleuser.NotFound");
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/accessmoduleusers/all")
    public Collection<AccessModuleUserDTO> getAllAccessModuleUsers() {
        log.debug("Request to get all  AccessModuleUsers : {}");
        return accessmoduleuserService.findAll();
    }

    @GetMapping("/accessmoduleusers/access")
    public Collection<ModuleDTO> getAllAccessModuleUsers(@RequestParam(required = false) Integer idModule, @RequestParam(required = false) String username) {
        log.debug("Request to get all  AccessModuleUsers : {}");
        return accessmoduleuserService.findAll(idModule, username);
    }

    @DeleteMapping("/accessmoduleusers")
    public ResponseEntity<String> deleteAccessModuleUser(@RequestParam Integer id) {
        log.debug("Request to delete AccessModuleUser: {}", id);
        accessmoduleuserService.delete(id);
        return ResponseEntity.ok().build();
    }
}
