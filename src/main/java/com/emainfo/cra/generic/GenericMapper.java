package com.emainfo.cra.generic;

import java.util.List;

import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;

/**
 * Basic generic interface to map Bo(s) to DTO(s) and vise versa
 *
 * @param <B>
 * @param <D>
 */
@MapperConfig
public interface GenericMapper<B, D> {

    List<D> toDtos(List<B> dtos);

    List<B> toBos(List<D> bos);

    D toDto(B bo);

    B toBo(D dto);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "createdDateTime", ignore = true)
//    @Mapping(target = "updatedDateTime", ignore = true)
    B fillBo(D dto, @MappingTarget B bo);
}