package com.emainfo.cra.repository;


import com.emainfo.cra.generic.GenericRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emainfo.cra.model.Account;

@Repository
public interface AccountRepository extends GenericRepository<Account,Long> {
    Account findByUsername(String userName);

    Account findByEmail(String email);
}
