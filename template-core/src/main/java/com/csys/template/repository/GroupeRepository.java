package com.csys.template.repository;

import com.csys.template.domain.Groupe;
import java.lang.String;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Groupe entity.
 */
@Repository
public interface GroupeRepository extends JpaRepository<Groupe, String>, QuerydslPredicateExecutor<Groupe> {
}

