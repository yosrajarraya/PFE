package com.csys.template.repository;

import com.csys.template.domain.Menu;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Menu entity.
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer>,QuerydslPredicateExecutor<Menu>  {
}

