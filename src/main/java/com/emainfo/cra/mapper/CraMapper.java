package com.emainfo.cra.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.emainfo.cra.dto.CraDto;
import com.emainfo.cra.generic.GenericMapper;
import com.emainfo.cra.model.Cra;

@Component
@Mapper(componentModel = "spring")
public interface CraMapper extends GenericMapper<Cra, CraDto> {
//Empty for now
}