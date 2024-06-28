package com.csys.template.web.rest;

import com.csys.template.dto.ModuleDTO;
import com.csys.template.service.ModuleService;
import java.lang.Integer;
import java.lang.String;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ModuleResource {

    private static final String ENTITY_NAME = "module";

    private final ModuleService moduleService;

    private final Logger log = LoggerFactory.getLogger(ModuleService.class);

    public ModuleResource(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping("/modules")
    public ResponseEntity<ModuleDTO> getModule(@PathVariable Integer id_module) {
        log.debug("Request to get Module: {}", id_module);
        ModuleDTO dto = moduleService.findModule(id_module);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/modules/filtre")
    public Collection<ModuleDTO> getAllModules(@RequestParam(required = false) Boolean[] actifs) {
        log.debug("Request to get all  Modules : {}");
        return moduleService.findAll(actifs);
    }

//  @GetMapping("/modules/without-hirarchy")
//  public List<AccessDTO> getAllWithoutHirarchy(@RequestParam(required = false) Boolean[] actifs) {
//    log.debug("Request to get all  Modules : {}");
//    return moduleService.findAllWithoutHirarchy(actifs);
//  }
}
