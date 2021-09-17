package com.emainfo.cra.service;
import javax.annotation.Resource;

import com.emainfo.cra.dto.CraDto;
import com.emainfo.cra.dto.SignatureDto;
import com.emainfo.cra.mapper.SignatureMapper;
import com.emainfo.cra.model.Account;
import com.emainfo.cra.model.Cra;
import com.emainfo.cra.model.Signature;
import com.emainfo.cra.repository.SignatureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.emainfo.cra.generic.GenericService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SignatureService extends GenericService<Signature, SignatureDto, Long, SignatureRepository, SignatureMapper> {
    @Resource
    AccountService accountService;
    public SignatureService(SignatureRepository repository, SignatureMapper mapper) {
        super(repository, mapper);
    }

    public Mono<SignatureDto> createSignatureByUserName(final SignatureDto signatureDto) {
        final Account account = accountService.getAccountByUserName(signatureDto.getUsername());
        final Signature signature = mapper.toBo(signatureDto);
        signature.setAccount(account);
        return createEntityFromBo(signature).map(mapper::toDto);
    }

    public Flux<SignatureDto> getSignatureByUserName(final String username) {
        final Account account = accountService.getAccountByUserName(username);

        return Flux.fromIterable(repository.findByAccount(account)).map(mapper::toDto);
    }

}