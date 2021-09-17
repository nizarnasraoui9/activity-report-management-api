package com.emainfo.cra.resource;

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

import com.emainfo.cra.dto.ClientDto;
import com.emainfo.cra.model.Client;
import com.emainfo.cra.service.ClientService;
import com.querydsl.core.types.Predicate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping("/clients")
@CrossOrigin()
public class ClientResource {

    private final ClientService clientService;

    @GetMapping("/dynamic")
    public Flux<ClientDto> dynamicFindAll(@QuerydslPredicate(root = Client.class) final Predicate predicate) {
        log.info("Dynamically find all client ");
        return clientService.retrieveAll(predicate);
    }

    @GetMapping
    public Flux<ClientDto> retrieveAll() {
        log.info("find all client ");
        return clientService.retrieveAll();
    }

    @GetMapping("/{id}")
    public Mono<ClientDto> retrieveById(
            @Schema(implementation = String.class) @PathVariable(value = "id") final Long id) {
        log.info("find client for id {}", id);
        return clientService.retrieveById(id);
    }

    @PostMapping
    public Mono<ClientDto> createNew(
            @RequestBody @Valid final ClientDto clientDto) {
        log.info("Receiving {} Data", clientDto);
        return clientService.createEntityFromDto(clientDto);
    }

    @PutMapping("/{id}")
    public Mono<ClientDto> update(
            @Schema(implementation = String.class) @PathVariable(value = "id") final Long id,
            @RequestBody @Valid final ClientDto clientDto) {
        log.info("Updating client  {} Data", id);
        return clientService.update(id, clientDto);
    }

    @DeleteMapping("/{id}")
    public Mono<ClientDto> delete(
            @Schema(implementation = String.class) @PathVariable(value = "id") final Long id) {
        log.info("Updating client  {} Data", id);
        return clientService.delete(id);
    }
}