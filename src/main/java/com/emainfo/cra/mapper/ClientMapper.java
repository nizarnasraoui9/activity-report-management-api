package com.emainfo.cra.mapper;

import com.emainfo.cra.generic.GenericMapper;
import org.mapstruct.Mapper;

import com.emainfo.cra.dto.ClientDto;
import com.emainfo.cra.model.Client;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ClientMapper extends GenericMapper<Client, ClientDto> {
//Empty for now
}