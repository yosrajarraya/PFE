package com.csys.template.repository;

import com.csys.template.domain.AccessModuleUser;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AccessModuleUser entity.
 */
@Repository
public interface AccessModuleUserRepository extends JpaRepository<AccessModuleUser, Integer> ,QuerydslPredicateExecutor<AccessModuleUser>  {
}

