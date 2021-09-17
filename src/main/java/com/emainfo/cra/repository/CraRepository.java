package com.emainfo.cra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emainfo.cra.generic.GenericRepository;
import com.emainfo.cra.model.Account;
import com.emainfo.cra.model.Cra;

@Repository
public interface CraRepository extends GenericRepository<Cra,Long> {
	
	
	List<Cra> findByAccount(final Account account);
	List<Cra> findByYearAndMonth(Integer year,Integer month);
}