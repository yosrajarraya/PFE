package com.csys.template.repository;

import com.csys.template.domain.AccessModuleGrp;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AccessModuleGrp entity.
 */
@Repository
public interface AccessModuleGrpRepository extends JpaRepository<AccessModuleGrp, Integer> ,QuerydslPredicateExecutor<AccessModuleGrp>  {
}

