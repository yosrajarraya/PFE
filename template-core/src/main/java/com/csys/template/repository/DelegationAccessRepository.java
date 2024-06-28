package com.csys.template.repository;

import com.csys.template.domain.DelegationAccess;
import java.lang.Integer;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DelegationAccess entity.
 */
@Repository
public interface DelegationAccessRepository extends JpaRepository<DelegationAccess, Integer>, QuerydslPredicateExecutor<DelegationAccess> {

   
}
