package com.csys.template.repository;

import com.csys.template.domain.Button;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Button entity.
 */
@Repository
public interface ButtonRepository extends JpaRepository<Button, Integer> ,QuerydslPredicateExecutor<Button>  {
}

