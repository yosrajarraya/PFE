package com.csys.template.repository;

import com.csys.template.domain.AccessButtonGrp;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AccessButtonGrp entity.
 */
@Repository
public interface AccessButtonGrpRepository extends JpaRepository<AccessButtonGrp, Integer> ,QuerydslPredicateExecutor<AccessButtonGrp> {
}

