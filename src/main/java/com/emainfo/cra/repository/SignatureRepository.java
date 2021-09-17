package com.emainfo.cra.repository;
import com.emainfo.cra.generic.GenericRepository;
import com.emainfo.cra.model.Account;
import com.emainfo.cra.model.Cra;
import com.emainfo.cra.model.Signature;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SignatureRepository extends GenericRepository<Signature, Long> {
    List<Signature> findByAccount(final Account account);

}
