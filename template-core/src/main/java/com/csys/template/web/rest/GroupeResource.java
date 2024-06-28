package com.csys.template.web.rest;

import com.csys.template.domain.Groupe;
import com.csys.template.dto.GroupeDTO;
import com.csys.template.imprimer.Imprimer_group;
import com.csys.template.service.GroupeService;
import com.itextpdf.text.DocumentException;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.lang.String;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
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

/**
 * REST controller for managing GroupUser.
 */
@RestController
@RequestMapping("/api")
public class GroupeResource {
  private static final String ENTITY_NAME = "groupe";

    private final GroupeService groupeService;

    private final Logger log = LoggerFactory.getLogger(GroupeService.class);

    public GroupeResource(GroupeService groupeService) {
        this.groupeService = groupeService;
    }

    @PostMapping("/groupes")
    public ResponseEntity<String> createGroupUser(@Valid @RequestBody GroupeDTO groupeDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        log.debug("REST request to save GroupUser : {}", groupeDTO);

        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        String result = groupeService.save(groupeDTO);
        return ResponseEntity.created(new URI("/api/groupes/")).body(result);
    }

    @PutMapping("/groupes")
    public ResponseEntity<String> updateGroupUser(@Valid @RequestBody GroupeDTO groupeDTO) throws MethodArgumentNotValidException {
        String result = groupeService.update(groupeDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/groupes")
    public ResponseEntity<GroupeDTO> getGroupUser(String groupe) {
        log.debug("Request to get GroupUser: {}", groupe);
        GroupeDTO dto = groupeService.findGroupUser(groupe);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/groupes/filtre")
    public List<GroupeDTO> getAllGroupUsers(@RequestParam(required = false) Boolean[] actifs) {
        log.debug("Request to get all  GroupUsers : {}");
      List<GroupeDTO> groupeDTOs=  groupeService.findAll(actifs);
        return groupeDTOs;
    }

    @DeleteMapping("/groupes")
    public ResponseEntity<String> deleteGroupUser(@RequestParam String groupe) {
        log.debug("Request to delete GroupUser: {}", groupe);
        String result = groupeService.delete(groupe);
        return ResponseEntity.ok().body(result);
    }
    @GetMapping("/imprimerGroupe")
    @ApiOperation(value = "Export users to PDF")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=groupes.pdf";
        response.setHeader(headerKey, headerValue);

        List<Groupe> listGroup = groupeService.listAll();

        Imprimer_group exporter = new Imprimer_group(listGroup);
        exporter.export(response);
    }

}

