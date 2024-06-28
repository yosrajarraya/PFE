package com.csys.template.repository;

import com.csys.template.domain.AccessButtonUser;

import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Data JPA repository for the AccessButtonUser entity.
 */
@Repository
public interface AccessButtonUserRepository extends JpaRepository<AccessButtonUser, Integer>  ,QuerydslPredicateExecutor<AccessButtonUser>{

   @Transactional
    @Modifying
    @Query("UPDATE AccessButtonUser SET numDelegate = '', visible = false WHERE numDelegate = ?1")
    void updateByNumDelegate(Integer numDelegate);
}

