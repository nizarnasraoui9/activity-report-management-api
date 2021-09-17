package com.emainfo.cra.service;

import javax.annotation.Resource;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.emainfo.cra.dto.AccountDto;
import com.emainfo.cra.exception.CraException;
import com.emainfo.cra.generic.GenericService;
import com.emainfo.cra.mapper.AccountMapper;
import com.emainfo.cra.model.Account;
import com.emainfo.cra.repository.AccountRepository;

import reactor.core.publisher.Mono;

@Service
public class AccountService extends GenericService<Account, AccountDto, Long, AccountRepository, AccountMapper> {
	@Resource
	private PasswordEncoder bcryptEncoder;
	
	public AccountService(final AccountRepository repository, final AccountMapper mapper) {
		super(repository, mapper);
	}

	public Mono<AccountDto> getAccountDtoByUserName(final String userName) {
		return Mono.justOrEmpty(repository.findByUsername(userName)).map(mapper::toDto);
	}
	public Account getAccountByUserName(final String userName) {
		return repository.findByUsername(userName);
	}
	public Mono<AccountDto> retrieveBoByUserEmail(final String email) {
		return Mono.justOrEmpty(repository.findByEmail(email)).map(mapper::toDto);
	}

	public boolean emailExists(final String email) {
		return retrieveBoByUserEmail(email).blockOptional().isPresent();
	}
	
	@Override
	public Mono<AccountDto> save(final AccountDto user) {
	if (emailExists(user.getEmail())) {
		throw new CraException("There is an account with that email address: " + user.getEmail());
	}
	user.setPassword(bcryptEncoder.encode(user.getPassword()));
	return createEntityFromDto(user);
}
}