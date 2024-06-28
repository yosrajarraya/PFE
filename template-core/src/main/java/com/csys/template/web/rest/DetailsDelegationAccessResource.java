package com.csys.template.web.rest;

import com.csys.template.dto.DetailsDelegationAccessDTO;
import com.csys.template.service.DetailsDelegationAccessService;
import com.csys.template.util.RestPreconditions;
import java.lang.Integer;
import java.lang.String;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
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
 * REST controller for managing DetailsDelegationAccess.
 */
@RestController
@RequestMapping("/api")
public class DetailsDelegationAccessResource {

    private static final String ENTITY_NAME = "detailsdelegationaccess";

    private final DetailsDelegationAccessService detailsdelegationaccessService;

    private final Logger log = LoggerFactory.getLogger(DetailsDelegationAccessService.class);

    public DetailsDelegationAccessResource(DetailsDelegationAccessService detailsdelegationaccessService) {
        this.detailsdelegationaccessService = detailsdelegationaccessService;
    }

    @PostMapping("/detailsdelegationaccesss")
    public ResponseEntity<String> createDetailsDelegationAccess(@Valid @RequestBody DetailsDelegationAccessDTO detailsdelegationaccessDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
        log.debug("REST request to save DetailsDelegationAccess : {}", detailsdelegationaccessDTO);

        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }
        String result = detailsdelegationaccessService.save(detailsdelegationaccessDTO);
        return ResponseEntity.created(new URI("/api/detailsdelegationaccesss/")).body(result);
    }

    @PutMapping("/detailsdelegationaccesss")
    public ResponseEntity<String> updateDetailsDelegationAccess(@Valid @RequestBody DetailsDelegationAccessDTO detailsdelegationaccessDTO) throws MethodArgumentNotValidException {
        String result = detailsdelegationaccessService.update(detailsdelegationaccessDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/detailsdelegationaccesss")
    public ResponseEntity<DetailsDelegationAccessDTO> getDetailsDelegationAccess(@PathVariable Integer id) {
        log.debug("Request to get DetailsDelegationAccess: {}", id);
        DetailsDelegationAccessDTO dto = detailsdelegationaccessService.findDetailsDelegationAccess(id);
        RestPreconditions.checkFound(dto, "detailsdelegationaccess.NotFound");
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/detailsdelegationaccesss/all")
    public Collection<DetailsDelegationAccessDTO> getAllDetailsDelegationAccesss() {
        log.debug("Request to get all  DetailsDelegationAccesss : {}");
        return detailsdelegationaccessService.findAll();
    }

    @DeleteMapping("/detailsdelegationaccesss")
    public ResponseEntity<String> deleteDetailsDelegationAccess(@RequestParam Integer id) {
        log.debug("Request to delete DetailsDelegationAccess: {}", id);
        String result = detailsdelegationaccessService.delete(id);
        return ResponseEntity.ok().body(result);
    }
}
