package com.csys.template.repository;

import com.csys.template.domain.DetailsDelegationAccess;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DetailsDelegationAccess entity.
 */
@Repository
public interface DetailsDelegationAccessRepository extends JpaRepository<DetailsDelegationAccess, Integer> {
}

