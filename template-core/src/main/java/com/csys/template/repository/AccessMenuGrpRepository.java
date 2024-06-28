package com.csys.template.repository;

import com.csys.template.domain.AccessMenuGrp;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AccessMenuGrp entity.
 */
@Repository
public interface AccessMenuGrpRepository extends JpaRepository<AccessMenuGrp, Integer>, QuerydslPredicateExecutor<AccessMenuGrp> {
}

