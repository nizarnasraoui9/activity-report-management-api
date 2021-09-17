package com.emainfo.cra.mapper;

import com.emainfo.cra.dto.SignatureDto;
import com.emainfo.cra.generic.GenericMapper;
import com.emainfo.cra.model.Signature;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface SignatureMapper extends GenericMapper<Signature, SignatureDto> {
//Empty for now
}