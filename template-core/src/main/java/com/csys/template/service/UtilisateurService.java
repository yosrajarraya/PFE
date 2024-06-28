package com.csys.template.service;

import com.csys.template.domain.QUtilisateur;
import com.csys.template.dto.UtilisateurDTO;
import com.csys.template.factory.UtilisateurFactory;
import com.csys.template.util.WhereClauseBuilder;
import com.csys.template.util.Preconditions;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.csys.template.exception.CustomException;
import com.csys.template.domain.Utilisateur;
import com.csys.template.repository.UtilisateurRepository;
import com.csys.template.security.JwtTokenProvider;
import com.csys.template.util.Helper;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;

//, SecurityContextHolder.getContext().getAuthentication().getName()
@Service
@Transactional
@Slf4j
public class UtilisateurService {

    private final Logger log = LoggerFactory.getLogger(UtilisateurService.class);

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    /*  public String signin(String username, String password) {
        try {
            log.info("***********authentication***********");
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            log.info("create token----------------");
            return jwtTokenProvider.createToken(username);
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
     */
    public Map<String, String> signin(String username, String password) {
        try {
            Utilisateur utilisateur = utilisateurRepository.findByUsername(username);

            if (utilisateur == null) {
                throw new CustomException("Utilisateur non trouvé", HttpStatus.UNPROCESSABLE_ENTITY);
            }

            log.info("utilisateur.getPassword()------------------------{}", utilisateur.getPassword());
            log.info("password------------------------{}", password);
            if (!passwordEncoder.matches(password, passwordEncoder.encode(utilisateur.getPassword()))) {
                throw new CustomException("Mot de passe incorrect", HttpStatus.UNPROCESSABLE_ENTITY);
            }
            String authenticatedUsername = utilisateur.getUsername();

            utilisateur.setUserCreation(authenticatedUsername);
            //return jwtTokenProvider.createToken(username);
            String token = jwtTokenProvider.createToken(username);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return response;
        } catch (CustomException e) {
            throw e; // Re-lancer l'exception personnalisée
        } catch (Exception e) {
            throw new CustomException("Une erreur s'est produite lors de l'authentification", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean validateCredentials(String username, String password) {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);

        if (utilisateur != null && passwordEncoder.matches(password, utilisateur.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public String signup(Utilisateur utilisateur) {
        if (!utilisateurRepository.existsByUsername(utilisateur.getUsername())) {
            utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
            utilisateurRepository.save(utilisateur);
            if (utilisateur.getUsername().equals("admin")) {
                // Créez un exemple d'utilisateur admin avec un nom d'utilisateur et un mot de passe par défaut
                Utilisateur adminUser = new Utilisateur();
                adminUser.setUsername("admin");
                adminUser.setPassword(passwordEncoder.encode("admin")); // Mot de passe par défaut

                // Enregistrez l'utilisateur admin dans la base de données
                utilisateurRepository.save(adminUser);
            }
            return jwtTokenProvider.createToken(utilisateur.getUsername());
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void deletee(String username) {
        utilisateurRepository.deleteByUsername(username);
    }

    public Utilisateur search(String username) {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
        if (utilisateur == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return utilisateur;
    }

    public Utilisateur whoami(HttpServletRequest req) {
        return utilisateurRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public String refresh(String username) {
        return jwtTokenProvider.createToken(username);
    }

    public String save(UtilisateurDTO utilisateurDTO) {
        log.debug("Request to save Utilisateur: {}", utilisateurDTO);
        Preconditions.checkBusinessLogique(!exists(utilisateurDTO.getUsername()), "Le username est déjà utilisé");
        Preconditions.checkBusinessLogique((utilisateurDTO.getUsername().length() <= 20 && utilisateurDTO.getUsername().length() > 0), "Le username est invalide");
        Preconditions.checkBusinessLogique(utilisateurDTO.getPassword().equals(utilisateurDTO.getConfirmPassword()), "Les mots de passe ne correspondent pas");
//        Preconditions.checkBusinessLogique(!utilisateurDTO.isAccountExpired(), "Le compte de l'utilisateur a expiré");
        if (utilisateurDTO.getExpireCompte() != null && utilisateurDTO.getExpireCompte() && utilisateurDTO.getDateExpiration() != null && utilisateurDTO.getDateExpiration().isBefore(LocalDate.now())) {
            Preconditions.checkBusinessLogique(false, "Le compte de l'utilisateur a expiré");
        }

        Utilisateur utilisateur = UtilisateurFactory.utilisateurDTOToUtilisateur(utilisateurDTO, null, SecurityContextHolder.getContext().getAuthentication().getName());
        log.debug("Helper.getUserAuthenticated() {}", Helper.getUserAuthenticated());
        utilisateur.setUserCreation(Helper.getUserAuthenticated());
        utilisateurRepository.save(utilisateur);
        return "True";
    }

    public String update(UtilisateurDTO utilisateurDTO) {
        log.debug("Request to update Utilisateur: {}", utilisateurDTO);
        Utilisateur inBase = findOne(utilisateurDTO.getUsername());
        Preconditions.checkBusinessLogique(inBase != null, "utilisateur.NotFound");
        Utilisateur utilisateur = UtilisateurFactory.utilisateurDTOToUtilisateur(utilisateurDTO, inBase, SecurityContextHolder.getContext().getAuthentication().getName());
        utilisateur = utilisateurRepository.save(utilisateur);
        return "True";
    }

    @Transactional(
            readOnly = true
    )
    public Utilisateur findOne(String username) {
        log.debug("Request to get Utilisateur: {}", username);
        Utilisateur utilisateur = utilisateurRepository.findById(username).orElse(null);
        return utilisateur;
    }

    @Transactional(
            readOnly = true
    )
    public UtilisateurDTO findUtilisateur(String username) {
        log.debug("Request to get Utilisateur: {}", username);
        Utilisateur utilisateur = findOne(username);
        UtilisateurDTO dto = UtilisateurFactory.utilisateurToUtilisateurDTO(utilisateur);
        return dto;
    }

    @Transactional(
            readOnly = true
    )
    public List<UtilisateurDTO> findAll(Boolean[] actifs) {
        log.debug("Request to get All Utilisateurs");
        List<Utilisateur> result = findAllByActifs(actifs);
        return UtilisateurFactory.utilisateurToUtilisateurDTOs(result);
    }

    public List<Utilisateur> findAllByActifs(Boolean[] actifs) {
        log.debug("Request to get All users");
        QUtilisateur qUtilisateur = QUtilisateur.utilisateur;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .optionalAnd(actifs, () -> qUtilisateur.active.in(actifs));
        List<Utilisateur> result = (List<Utilisateur>) utilisateurRepository.findAll(builder);
        return result;

    }

    public String delete(String id) {
        log.debug("Request to delete Utilisateur: {}", id);
        Utilisateur utilisateur = findOne(id);
        Preconditions.checkBusinessLogique(utilisateur != null, "l'utilisateur est inexistant");
        utilisateurRepository.delete(utilisateur);
        return "True";
    }

    @Transactional(
            readOnly = true
    )
    public Boolean exists(String username) {
        QUtilisateur qUtilisateur = QUtilisateur.utilisateur;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .optionalAnd(username, () -> qUtilisateur.username.eq(username));
        Boolean result = utilisateurRepository.exists(builder);
        return result;
    }

    @Transactional(readOnly = true)
//methode pour l'imprission
    public List<Utilisateur> listAll() {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll(Sort.by("username").ascending());
        utilisateurs.forEach(u -> u.getGroupUsers().size()); 
        return utilisateurs;
    }

}
