package com.emainfo.cra.resource;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emainfo.cra.dto.AccountDto;
import com.emainfo.cra.model.Account;
import com.emainfo.cra.service.AccountService;
import com.querydsl.core.types.Predicate;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping("/accounts")
@Tag(name="User account")
@CrossOrigin()
public class AccountResource {

    private final AccountService accountService;

    @GetMapping("/dynamic")
    public Flux<AccountDto> dynamicFindAll(@QuerydslPredicate(root = Account.class) final Predicate predicate) {
        log.info("Dynamically find all account ");
        return accountService.retrieveAll(predicate);
    }


    @GetMapping("/{id}")
    public Mono<AccountDto> retrieveById(
            @Schema(implementation = String.class) @PathVariable(value = "id") final Long id) {
        log.info("find account for id {}", id);
        return accountService.retrieveById(id);
    }

    @GetMapping("/{userName}")
    public Mono<AccountDto> getAccountByUserName(
            @Schema(implementation = String.class) @PathVariable(value = "userName") final String userName) {
        log.info("find account for userName {}", userName);
        return accountService.getAccountDtoByUserName(userName);
    }

    @GetMapping()
    public Mono<AccountDto> retrieveAllByUser(final HttpServletRequest req) {
    	final String username= req.getHeader("username");
    	log.info("find account for username {}", username);
    	
    	return accountService.getAccountDtoByUserName(username);
    }

    @PostMapping
    public Mono<AccountDto> createNew(
            @RequestBody @Valid final AccountDto accountDto) {
        log.info("Receiving {} Data", accountDto);
        return accountService.createEntityFromDto(accountDto);
    }

    @PutMapping("/{id}")
    public Mono<AccountDto> update(
            @Schema(implementation = String.class) @PathVariable(value = "id") final Long id,
            @RequestBody @Valid final AccountDto accountDto) {
        log.info("Updating account  {} Data", accountDto);
        return accountService.update(id, accountDto);
    }

    @DeleteMapping("/{id}")
    public Mono<AccountDto> delete(
            @Schema(implementation = String.class) @PathVariable(value = "id") final Long id) {
        log.info("Updating account  {} Data", id);
        return accountService.delete(id);
    }



}