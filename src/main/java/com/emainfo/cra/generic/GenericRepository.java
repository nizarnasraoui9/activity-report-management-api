package com.emainfo.cra.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<B, I> extends JpaRepository<B, I>, QuerydslPredicateExecutor<B> {
    //Empty
}
