package com.emainfo.cra.service;

import org.springframework.stereotype.Service;

import com.emainfo.cra.dto.ContractedDto;
import com.emainfo.cra.generic.GenericService;
import com.emainfo.cra.mapper.ContractedMapper;
import com.emainfo.cra.model.Contracted;
import com.emainfo.cra.repository.ContractedRepository;

@Service
public class ContractedService extends GenericService<Contracted, ContractedDto, Long, ContractedRepository, ContractedMapper> {
    public ContractedService(final ContractedRepository repository, final ContractedMapper mapper) {
        super(repository, mapper);
    }
}