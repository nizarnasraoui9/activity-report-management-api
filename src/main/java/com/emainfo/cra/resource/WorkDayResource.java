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

import com.emainfo.cra.dto.WorkDayDto;
import com.emainfo.cra.model.WorkDay;
import com.emainfo.cra.service.WorkDayService;
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
@RequestMapping("/workdays")
@CrossOrigin()
public class WorkDayResource {

    private final WorkDayService workDayService;

    @GetMapping("/dynamic")
    public Flux<WorkDayDto> dynamicFindAll(@QuerydslPredicate(root = WorkDay.class) final Predicate predicate) {
        log.info("Dynamically find all workDay ");
        return workDayService.retrieveAll(predicate);
    }

    @GetMapping
    public Flux<WorkDayDto> retrieveAll() {
        log.info("find all workDay ");
        return workDayService.retrieveAll();
    }

    @GetMapping("/{id}")
    public Mono<WorkDayDto> retrieveById(
            @Schema(implementation = String.class) @PathVariable(value = "id") final Long id) {
        log.info("find workDay for id {}", id);
        return workDayService.retrieveById(id);
    }

    @PostMapping
    public Mono<WorkDayDto> createNew(
            @RequestBody @Valid final WorkDayDto workDayDto) {
        log.info("Receiving {} Data", workDayDto);
        return workDayService.createEntityFromDto(workDayDto);
    }

    @PutMapping("/{id}")
    public Mono<WorkDayDto> update(
            @Schema(implementation = String.class) @PathVariable(value = "id") final Long id,
            @RequestBody @Valid final WorkDayDto workDayDto) {
        log.info("Updating workDay  {} Data", id);
        return workDayService.update(id, workDayDto);
    }

    @DeleteMapping("/{id}")
    public Mono<WorkDayDto> delete(
            @Schema(implementation = String.class) @PathVariable(value = "id") final Long id) {
        log.info("Updating workDay  {} Data", id);
        return workDayService.delete(id);
    }
}