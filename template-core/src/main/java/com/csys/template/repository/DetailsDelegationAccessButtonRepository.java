package com.csys.template.repository;

import com.csys.template.domain.DetailsDelegationAccessButton;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DetailsDelegationAccessButton entity.
 */
@Repository
public interface DetailsDelegationAccessButtonRepository extends JpaRepository<DetailsDelegationAccessButton, Integer>, QuerydslPredicateExecutor<DetailsDelegationAccessButton> {
}

