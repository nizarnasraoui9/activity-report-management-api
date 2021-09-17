package com.emainfo.cra.service;

import javax.annotation.Resource;

import com.emainfo.cra.model.Client;
import com.emainfo.cra.repository.ClientRepository;
import org.springframework.stereotype.Service;

import com.emainfo.cra.dto.CraDto;
import com.emainfo.cra.generic.GenericService;
import com.emainfo.cra.mapper.CraMapper;
import com.emainfo.cra.model.Account;
import com.emainfo.cra.model.Cra;
import com.emainfo.cra.repository.CraRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CraService extends GenericService<Cra, CraDto, Long, CraRepository, CraMapper> {
	@Resource
	AccountService accountService;
	@Resource
	CraRepository craRepository;
	@Resource
	CraMapper craMapper;
	@Resource
	ClientRepository clientRepository;

	public CraService(final CraRepository repo, final CraMapper mapper) {
		super(repo, mapper);
	}

	public Mono<CraDto> createCraByUserName(final CraDto craDto) {
		final Account account = accountService.getAccountByUserName(craDto.getUsername());
		final Cra cra = mapper.toBo(craDto);
		cra.setAccount(account);
		return createEntityFromBo(cra).map(mapper::toDto);
	}

	public Flux<CraDto> getListCraByUserName(final String username) {
		final Account account = accountService.getAccountByUserName(username);

		return Flux.fromIterable(repository.findByAccount(account)).map(mapper::toDto);
	}
	public Flux<CraDto> getCraByDateAndClient(Integer year,Integer month,String name){
		Client client=clientRepository.findByName(name);
		System.out.println(name);
		return Flux.fromIterable(craRepository.findByYearAndMonthAndClient(year,month,client)).map(craMapper::toDto);
	}



}
