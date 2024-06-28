package com.csys.template.repository;

import com.csys.template.domain.Clinique;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Clinique entity.
 */
@Repository
public interface CliniqueRepository extends JpaRepository<Clinique, Integer>, QuerydslPredicateExecutor<Clinique>  {
}

