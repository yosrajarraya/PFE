package com.csys.template.repository;

import com.csys.template.domain.GroupUser;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupUserRepository extends JpaRepository<GroupUser, Integer> , QuerydslPredicateExecutor<GroupUser> {
}

