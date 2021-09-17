package com.emainfo.cra.service;

import com.emainfo.cra.generic.GenericService;
import com.emainfo.cra.repository.WorkDayRepository;
import org.springframework.stereotype.Service;

import com.emainfo.cra.dto.WorkDayDto;
import com.emainfo.cra.mapper.WorkDayMapper;
import com.emainfo.cra.model.WorkDay;

@Service
public class WorkDayService extends GenericService<WorkDay, WorkDayDto, Long, WorkDayRepository, WorkDayMapper> {
    public WorkDayService(final WorkDayRepository repository, final WorkDayMapper mapper) {
        super(repository, mapper);
    }
}
