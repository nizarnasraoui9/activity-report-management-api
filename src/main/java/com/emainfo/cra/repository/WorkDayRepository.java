package com.emainfo.cra.repository;


import com.emainfo.cra.generic.GenericRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emainfo.cra.model.WorkDay;

@Repository
public interface WorkDayRepository extends GenericRepository<WorkDay, Long> {
}
