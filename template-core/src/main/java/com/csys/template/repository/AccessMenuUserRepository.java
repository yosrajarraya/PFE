package com.csys.template.repository;

import com.csys.template.domain.AccessMenuUser;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AccessMenuUser entity.
 */
@Repository
public interface AccessMenuUserRepository extends JpaRepository<AccessMenuUser, Integer> ,QuerydslPredicateExecutor<AccessMenuUser>  {
}

