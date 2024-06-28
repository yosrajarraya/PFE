package com.csys.template.web.rest;

import com.csys.template.dto.DelegationAccessDTO;
import com.csys.template.enumeration.EnumDTO;
import com.csys.template.enumeration.EnumMotif;
import com.csys.template.service.DelegationAccessService;
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

/**
 * REST controller for managing DelegationAccess.
 */
@RestController
@RequestMapping("/api")
public class DelegationAccessResource {

    private static final String ENTITY_NAME = "delegationaccess";

    private final DelegationAccessService delegationaccessService;

    private final Logger log = LoggerFactory.getLogger(DelegationAccessService.class);

    public DelegationAccessResource(DelegationAccessService delegationaccessService) {
        this.delegationaccessService = delegationaccessService;
    }

    @PostMapping("/delegationaccess")
    public ResponseEntity<String> createDelegationAccess(@Valid @RequestBody DelegationAccessDTO delegationaccessDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        log.debug("REST request to save DelegationAccess : {}", delegationaccessDTO);

        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        String result = delegationaccessService.save(delegationaccessDTO);
        return ResponseEntity.created(new URI("/api/delegationaccess/")).body(result);
    }

    @PutMapping("/delegationaccess")
    public ResponseEntity<String> updateDelegationAccess(@Valid @RequestBody DelegationAccessDTO delegationaccessDTO) throws MethodArgumentNotValidException {

        String result = delegationaccessService.update(delegationaccessDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/delegationaccess")
    public ResponseEntity<DelegationAccessDTO> getDelegationAccess(Integer delegationaccess) {
        log.debug("Request to get DelegationAccess: {}", delegationaccess);
        DelegationAccessDTO dto = delegationaccessService.findDelegationAccess(delegationaccess);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/delegationaccess/filter")
    public Collection<DelegationAccessDTO> getAllDelegationAccesss(@RequestParam(required = false) Boolean[] Termine) {
        log.debug("Request to get all  DelegationAccesss : {}");
        List<DelegationAccessDTO> dto = delegationaccessService.findAll(Termine);
        return dto;
    }

    @DeleteMapping("/delegationaccess")
    public ResponseEntity<String> deleteDelegationAccess(@RequestParam Integer delegationaccess) {
        log.debug("Request to delete DelegationAccess: {}", delegationaccess);
        String result = delegationaccessService.delete(delegationaccess);
        return ResponseEntity.ok().body(result);

    }

    @GetMapping("/delegationaccess/motif")
    public Collection<EnumDTO> motif() {
        return EnumMotif.MOTIF.values();
    }

}
