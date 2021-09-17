package com.emainfo.cra.mapper;

import com.emainfo.cra.generic.GenericMapper;
import com.emainfo.cra.model.Contracted;
import org.mapstruct.Mapper;

import com.emainfo.cra.dto.ContractedDto;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ContractedMapper extends GenericMapper<Contracted, ContractedDto> {
//Empty for now
}