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

import com.emainfo.cra.dto.ContractedDto;
import com.emainfo.cra.model.Contracted;
import com.emainfo.cra.service.ContractedService;
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
@RequestMapping("/contracteds")
@CrossOrigin()
public class ContractedResource {

    private final ContractedService contractedService;

    @GetMapping("/dynamic")
    public Flux<ContractedDto> dynamicFindAll(@QuerydslPredicate(root = Contracted.class) final Predicate predicate) {
        log.info("Dynamically find all contracted ");
        return contractedService.retrieveAll(predicate);
    }

    @GetMapping
    public Flux<ContractedDto> retrieveAll() {
        log.info("find all contracted ");
        return contractedService.retrieveAll();
    }

    @GetMapping("/{id}")
    public Mono<ContractedDto> retrieveById(
            @Schema(implementation = String.class) @PathVariable(value = "id") final Long id) {
        log.info("find contracted for id {}", id);
        return contractedService.retrieveById(id);
    }

    @PostMapping
    public Mono<ContractedDto> createNew(
            @RequestBody @Valid final ContractedDto contractedDto) {
        log.info("Receiving {} Data", contractedDto);
        return contractedService.createEntityFromDto(contractedDto);
    }

    @PutMapping("/{id}")
    public Mono<ContractedDto> update(
            @Schema(implementation = String.class) @PathVariable(value = "id") final Long id,
            @RequestBody @Valid final ContractedDto contractedDto) {
        log.info("Updating contracted  {} Data", id);
        return contractedService.update(id, contractedDto);
    }

    @DeleteMapping("/{id}")
    public Mono<ContractedDto> delete(
            @Schema(implementation = String.class) @PathVariable(value = "id") final Long id) {
        log.info("Updating contracted  {} Data", id);
        return contractedService.delete(id);
    }
}