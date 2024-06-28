package com.csys.template.repository;

import com.csys.template.domain.Utilisateur;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Data JPA repository for the Utilisateur entity.
 */
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, String>, QuerydslPredicateExecutor<Utilisateur> {

 boolean existsByUsername(String username);

  Utilisateur findByUsername(String username);

  @Transactional
  void deleteByUsername(String username);
}
