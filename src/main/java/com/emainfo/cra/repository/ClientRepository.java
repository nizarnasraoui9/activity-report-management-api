package com.emainfo.cra.repository;
import com.emainfo.cra.generic.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emainfo.cra.model.Client;

@Repository
public interface ClientRepository extends GenericRepository<Client,Long> {
    Client findByName(String name);

    
}