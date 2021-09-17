package com.emainfo.cra.resource;

import com.emainfo.cra.dto.CraDto;
import com.emainfo.cra.dto.SignatureDto;
import com.emainfo.cra.model.Cra;
import com.emainfo.cra.service.SignatureService;
import com.querydsl.core.types.Predicate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping("/signatures")
@CrossOrigin()
public class SignatureResource {
    private final SignatureService signatureService ;


    @Autowired
    private ServletContext servletContext;


    @GetMapping("/dynamic")
    public Flux<SignatureDto> dynamicFindAll(final HttpServletRequest req,@QuerydslPredicate(root = Cra.class) final Predicate predicate) {
        log.info("Dynamically find all cra ");

        return signatureService.retrieveAll(predicate);
    }


    @GetMapping("/{id}")
    public Mono<SignatureDto> retrieveById(
            @Schema(implementation = String.class) @PathVariable(value = "id") final Long id) {
        log.info("find signature for id {}", id);

        return signatureService.retrieveById(id);
    }

    @GetMapping
    public Flux<SignatureDto> getSignatureByUserName(final HttpServletRequest req) {
        final String username= req.getHeader("username");
        log.info("find signature by user name {}", username);
        //if(Objects.nonNull(username) && !username.isEmpty()) {
        return signatureService.getSignatureByUserName(username);
        //}
    }

    @PutMapping("/{id}")
    public Mono<SignatureDto> update(
            @Schema(implementation = String.class) @PathVariable(value = "id") final Long id,
            @RequestBody @Valid final SignatureDto signatureDto) {
        log.info("Updating signature  {} Data", id);
        return signatureService.update(id, signatureDto);
    }


    @PostMapping
    public Mono<SignatureDto> createNew(
            @RequestBody @Valid final SignatureDto signatureDto) {
        String encodedString = Base64.getEncoder().encodeToString(signatureDto.getSignature().getBytes());
        return signatureService.createSignatureByUserName(signatureDto);
    }



    @DeleteMapping("/{id}")
    public Mono<SignatureDto> delete(
            @Schema(implementation = String.class) @PathVariable(value = "id") final Long id) {
        log.info("Updating signature  {} Data", id);
        return signatureService.delete(id);
    }
}