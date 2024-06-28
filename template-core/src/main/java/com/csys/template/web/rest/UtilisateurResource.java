package com.csys.template.web.rest;

import com.csys.template.domain.Credentials;
import com.csys.template.domain.Utilisateur;
import com.csys.template.util.RestPreconditions;
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
import org.springframework.web.bind.annotation.PutMapping;





import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import com.csys.template.dto.UtilisateurDTO;
import com.csys.template.dto.UserResponseDTO;
import com.csys.template.imprimer.Imprimer;

import com.csys.template.service.UtilisateurService;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;




/**
 * REST controller for managing Utilisateur.
 */
@RestController
@RequestMapping("/api")

@Api(tags = "utilisateurs")

public class UtilisateurResource {
  private static final String ENTITY_NAME = "utilisateur";

 

  private final Logger log = LoggerFactory.getLogger(UtilisateurService.class);

   private final UtilisateurService utilisateurService;
  private final ModelMapper modelMapper;

  
  
        
public UtilisateurResource(UtilisateurService utilisateurService, ModelMapper modelMapper) {
    this.utilisateurService = utilisateurService;
    this.modelMapper = modelMapper;
}

@PostMapping("/signin")
@ApiOperation(value = "${UtilisateurResource.signin}")
@ApiResponses(value = {
    @ApiResponse(code = 400, message = "Something went wrong"),
    @ApiResponse(code = 422, message = "Invalid username/password supplied")
})
public ResponseEntity<Map<String, String>> signin(@Valid @RequestBody Credentials credentials, HttpServletRequest request) {
    log.info("Signing in user: {}", credentials.getUsername());
    Map<String, String> token = utilisateurService.signin(credentials.getUsername(), credentials.getPassword());
    request.setAttribute("authenticatedUsername", credentials.getUsername()); // Stocker le nom d'utilisateur authentifié dans la requête
    return ResponseEntity.ok(token);
}

@PostMapping("/signup")
@ApiOperation(value = "${UtilisateurResource.signup}")
@ApiResponses(value = {
    @ApiResponse(code = 400, message = "Something went wrong"),
    @ApiResponse(code = 403, message = "Access denied"),
    @ApiResponse(code = 422, message = "Username is already in use")
})
public ResponseEntity<String> signup(
    @ApiParam("Signup User") @RequestBody UtilisateurDTO utilisateur,
    HttpServletRequest request) {
    String authenticatedUsername = (String) request.getAttribute("authenticatedUsername"); // Récupérer le nom d'utilisateur authentifié depuis la requête
    utilisateur.setUserCreation(authenticatedUsername); // Remplir le champ userCreation
    String result = utilisateurService.signup(modelMapper.map(utilisateur, Utilisateur.class));
    return ResponseEntity.ok(result);
}

  @DeleteMapping(value = "/{username}")
  @ApiOperation(value = "${UtilisateurResource.delete}", authorizations = { @Authorization(value="apiKey") })
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The user doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public String deletee(@ApiParam("Username") @PathVariable String username) {
    utilisateurService.delete(username);
    return username;
  }

  @GetMapping(value = "/{username}")

  @ApiOperation(value = "${UtilisateurResource.search}", response = UserResponseDTO.class, authorizations = { @Authorization(value="apiKey") })
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The user doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public UserResponseDTO search(@ApiParam("Username") @PathVariable String username) {
    return modelMapper.map(utilisateurService.search(username), UserResponseDTO.class);
  }

  @GetMapping(value = "/me")
 
  @ApiOperation(value = "${UtilisateurResource.me}", response = UserResponseDTO.class, authorizations = { @Authorization(value="apiKey") })
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  
  public UserResponseDTO whoami(HttpServletRequest req) {
    return modelMapper.map(utilisateurService.whoami(req), UserResponseDTO.class);
  }

  @GetMapping("/refresh")
 
  public String refresh(HttpServletRequest req) {
    return utilisateurService.refresh(req.getRemoteUser());
  }
  
 
  

  @PostMapping("/utilisateurs")
  public ResponseEntity<String> createUtilisateur(@Valid @RequestBody UtilisateurDTO utilisateurDTO, BindingResult bindingResult) throws URISyntaxException, MethodArgumentNotValidException {
    log.debug("REST request to save Utilisateur : {}", utilisateurDTO);
   
    if (bindingResult.hasErrors()) {
      throw new MethodArgumentNotValidException(null, bindingResult);
    }
    String result = utilisateurService.save(utilisateurDTO);
    return ResponseEntity.created( new URI("/api/utilisateurs/")).body(result);
  }


  @PutMapping("/utilisateurs")
  public ResponseEntity<String> updateUtilisateur( @Valid @RequestBody UtilisateurDTO utilisateurDTO) throws MethodArgumentNotValidException {
  
    String result =utilisateurService.update(utilisateurDTO);
    return ResponseEntity.ok().body(result);
  }

 
  @GetMapping("/utilisateurs")
  public ResponseEntity<UtilisateurDTO> getUtilisateur(@RequestParam String username) {
    log.debug("Request to get Utilisateur: {}",username);
    UtilisateurDTO dto = utilisateurService.findUtilisateur(username);
    RestPreconditions.checkFound(dto, "utilisateur.NotFound");
    return ResponseEntity.ok().body(dto);
  }

  
  @GetMapping("/utilisateurs/filtre")
  public Collection<UtilisateurDTO> getAllUtilisateurs(@RequestParam(required = false) Boolean[] actifs) {
    log.debug("Request to get all  Utilisateurs : {}");
    return utilisateurService.findAll(actifs);
  }

  @DeleteMapping("/utilisateurs")
  public ResponseEntity<String> deleteUtilisateur(@RequestParam String username) {
    log.debug("Request to delete Utilisateur: {}",username);
    utilisateurService.delete(username);
    return ResponseEntity.ok().build();
  }
    @GetMapping("/imprimerUtilisateur")
    @ApiOperation(value = "Export users to PDF")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=utilisateurs.pdf";
        response.setHeader(headerKey, headerValue);

        List<Utilisateur> listUsers = utilisateurService.listAll();

        Imprimer exporter = new Imprimer(listUsers);
        exporter.export(response);
    }

} 