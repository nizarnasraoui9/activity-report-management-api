package com.emainfo.cra.service;

import com.emainfo.cra.dto.ClientDto;
import com.emainfo.cra.generic.GenericService;
import com.emainfo.cra.mapper.ClientMapper;
import com.emainfo.cra.model.Client;
import com.emainfo.cra.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService  extends GenericService<Client, ClientDto, Long, ClientRepository, ClientMapper> {
    public ClientService(final ClientRepository repository, final ClientMapper mapper) {
        super(repository, mapper);
    }
}