package com.csys.template.repository;

import com.csys.template.domain.DetailsDelegationAccessMenu;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DetailsDelegationAccessMenu entity.
 */
@Repository
public interface DetailsDelegationAccessMenuRepository extends JpaRepository<DetailsDelegationAccessMenu, Integer>, QuerydslPredicateExecutor<DetailsDelegationAccessMenu> {
}

