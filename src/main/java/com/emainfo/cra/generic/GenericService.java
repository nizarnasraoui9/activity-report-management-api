package com.emainfo.cra.generic;

import static java.util.Objects.nonNull;
import static lombok.AccessLevel.PROTECTED;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import com.emainfo.cra.exception.EntityException;
import com.querydsl.core.types.Predicate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @param <B> Bo
 * @param <D> Dto
 * @param <I> id
 * @param <I> name
 * @param <R> Repository
 * @param <M> Mapper
 */
@Slf4j
@Transactional
@FieldDefaults(level = PROTECTED)
@Getter
@Setter
@AllArgsConstructor
@NoRepositoryBean
public abstract class GenericService<B, D, I, R extends GenericRepository<B, I>, M extends GenericMapper<B, D>> {

	R repository;

	M mapper;

	public Mono<D> retrieveById(final I id) {
		return Mono.justOrEmpty(repository
				.findById(id)
				.map(mapper::toDto));
	}


	public Mono<B> retrieveBoById(final I id) {
		return Mono.justOrEmpty(repository
				.findById(id))
				.switchIfEmpty(Mono.error(EntityException.toSupplier("id: " + id + " not found")));
	}

	public Flux<D> retrieveAll(final Predicate predicate) {
		return Flux.fromIterable(repository.findAll(predicate))
				.map(mapper::toDto);
	}

	public Flux<D> retrieveAll() {
		return Flux.fromIterable(repository.findAll())
				.map(mapper::toDto);
	}

	public Mono<D> save(final D dto) {
		return Mono.justOrEmpty(dto)
				.switchIfEmpty(Mono.error(EntityException.toSupplier("input: is null")))
				.flatMap(this::createEntityFromDto)
				.switchIfEmpty(Mono.error(EntityException.toSupplier("id: is null")));
	}

	public Mono<D> createEntityFromDto(final D dto) {
		return Mono.justOrEmpty(repository
				.save(mapper.toBo(dto)))
				.onErrorResume(DataAccessException.class, throwable -> Mono.error(EntityException.toSupplier("Failed to create entry", throwable)))
				.map(mapper::toDto);
	}

	public Mono<B> createEntityFromBo(final B bo) {
		return Mono.justOrEmpty(repository
				.save(bo))
				.onErrorResume(DataAccessException.class, throwable -> Mono.error(EntityException.toSupplier("Failed to create entry", throwable)));
	}

	public Mono<D> delete(final I id) {
		return Mono.justOrEmpty(repository
				.findById(id))
				.doOnNext(repository::delete)
				.switchIfEmpty(Mono.error(EntityException.toSupplier("id: " + id + " not found")))
				.map(mapper::toDto);
	}

	public Mono<D> deleteByUserName(final I id) {
		return Mono.justOrEmpty(repository
				.findById(id))
				.doOnNext(repository::delete)
				.switchIfEmpty(Mono.error(EntityException.toSupplier("id: " + id + " not found")))
				.map(mapper::toDto);
	}

	public Mono<D> saveFromBo(final B bo) {
		return Mono.justOrEmpty(bo)
				.switchIfEmpty(Mono.error(EntityException.toSupplier("input: is null")))
				.flatMap(this::createEntityFromBo)
				.switchIfEmpty(Mono.error(EntityException.toSupplier("id: is null")))
				.map(mapper::toDto);

	}

	public Mono<D> update(final I id, final D dto) {
		return Mono
				.justOrEmpty(dto)
				.switchIfEmpty(Mono.error(EntityException.toSupplier("dto: is null")))
				.filter(product -> nonNull(id))
				.switchIfEmpty(Mono.error(EntityException.toSupplier("id: is null")))

				.flatMap(entity -> updateProduct(id, entity));
	}

	private Mono<D> updateProduct(final I id, final D dto) {
		return Mono.justOrEmpty(repository
				.findById(id))
				.flatMap(entity -> Mono.justOrEmpty(repository.save(mapper.fillBo(dto, entity))))
				.map(mapper::toDto)
				.switchIfEmpty(Mono.error(EntityException.toSupplier("id: " + id + " not found")))
				.onErrorResume(
						DataAccessException.class, throwable -> Mono.error(EntityException.toSupplier("Failed to update entry", throwable)));
	}

}