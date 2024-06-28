package com.csys.template.web.rest;

import com.csys.template.dto.DetailsDelegationAccessButtonDTO;
import com.csys.template.service.DetailsDelegationAccessButtonService;
import com.csys.template.util.RestPreconditions;
import java.lang.Integer;
import java.lang.String;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DetailsDelegationAccessButtonResource {
  private static final String ENTITY_NAME = "detailsdelegationaccessbutton";

  private final DetailsDelegationAccessButtonService detailsdelegationaccessbuttonService;

  private final Logger log = LoggerFactory.getLogger(DetailsDelegationAccessButtonService.class);

  public DetailsDelegationAccessButtonResource(DetailsDelegationAccessButtonService detailsdelegationaccessbuttonService) {
    this.detailsdelegationaccessbuttonService=detailsdelegationaccessbuttonService;
  }

  @PostMapping("/detailsdelegationaccessbuttons")
  public ResponseEntity<String> createDetailsDelegationAccessButton(@Valid @RequestBody DetailsDelegationAccessButtonDTO detailsdelegationaccessbuttonDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save DetailsDelegationAccessButton : {}", detailsdelegationaccessbuttonDTO);
 
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    String result = detailsdelegationaccessbuttonService.save(detailsdelegationaccessbuttonDTO);
    return ResponseEntity.created( new URI("/api/detailsdelegationaccessbuttons/")).body(result);
  }

  @PutMapping("/detailsdelegationaccessbuttons")
  public ResponseEntity<String> updateDetailsDelegationAccessButton( @Valid @RequestBody DetailsDelegationAccessButtonDTO detailsdelegationaccessbuttonDTO) throws MethodArgumentNotValidException {
    String result =detailsdelegationaccessbuttonService.update(detailsdelegationaccessbuttonDTO);
    return ResponseEntity.ok().body(result);
  }

 
  @GetMapping("/detailsdelegationaccessbuttons")
  public ResponseEntity<DetailsDelegationAccessButtonDTO> getDetailsDelegationAccessButton(@RequestParam Integer id) {
    log.debug("Request to get DetailsDelegationAccessButton: {}",id);
    DetailsDelegationAccessButtonDTO dto = detailsdelegationaccessbuttonService.findDetailsDelegationAccessButton(id);
    RestPreconditions.checkFound(dto, "detailsdelegationaccessbutton.NotFound");
    return ResponseEntity.ok().body(dto);
  }

  @GetMapping("/detailsdelegationaccessbuttons/all")
  public List<DetailsDelegationAccessButtonDTO> getAllDetailsDelegationAccessButtons() {
    log.debug("Request to get all  DetailsDelegationAccessButtons : {}");
    return detailsdelegationaccessbuttonService.findAll();
  }


  @DeleteMapping("/detailsdelegationaccessbuttons")
  public ResponseEntity<String> deleteDetailsDelegationAccessButton(@RequestParam Integer id) {
    log.debug("Request to delete DetailsDelegationAccessButton: {}",id);
    String result=detailsdelegationaccessbuttonService.delete(id);
    return ResponseEntity.ok().body(result);

  }
}

