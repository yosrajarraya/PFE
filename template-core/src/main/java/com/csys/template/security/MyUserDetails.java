package com.csys.template.security;

import lombok.RequiredArgsConstructor;
import com.csys.template.domain.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.csys.template.repository.UtilisateurRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService {

  private final UtilisateurRepository utilisateurRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final Utilisateur utilisateur = utilisateurRepository.findByUsername(username);

    if (utilisateur == null) {
      throw new UsernameNotFoundException("User '" + username + "' not found");
    }

    // Create a list of granted authorities, here we add a default role "ROLE_USER"
    List<GrantedAuthority> grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

    return org.springframework.security.core.userdetails.User//
        .withUsername(username)//
        .password(utilisateur.getPassword())//
        .authorities(grantedAuthorities) // Add authorities
        .accountExpired(false)//
        .accountLocked(false)//
        .credentialsExpired(false)//
        .disabled(false)//
        .build();
  }

}
