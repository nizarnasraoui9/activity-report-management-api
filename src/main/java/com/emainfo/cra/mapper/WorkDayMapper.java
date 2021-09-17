package com.emainfo.cra.mapper;

import com.emainfo.cra.generic.GenericMapper;
import com.emainfo.cra.model.WorkDay;
import org.mapstruct.Mapper;

import com.emainfo.cra.dto.WorkDayDto;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel="spring")
public interface WorkDayMapper extends GenericMapper<WorkDay, WorkDayDto> {
}
