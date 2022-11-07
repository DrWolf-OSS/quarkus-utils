package it.drwolf.base.resources;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import it.drwolf.base.model.dtos.PageDTO;
import it.drwolf.base.model.entities.BaseEntity;
import it.drwolf.base.utils.HasLogger;

import javax.enterprise.inject.spi.CDI;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class CrudResource<T extends PanacheRepositoryBase<E, I>, E extends BaseEntity<I>, I>
		implements HasLogger {

	public static final String DEFAULT_PAGE_SIZE = "1000";

	protected final T repository;

	public CrudResource() {
		this.repository = CDI.current()
				.select((Class<T>) ((ParameterizedType) ((Class<?>) this.getClass()
						.getGenericSuperclass()).getGenericSuperclass()).getActualTypeArguments()[0])
				.get();
	}

	public E add(E entity) {
		this.getRepository().persist(entity);
		return entity;
	}

	public boolean delete(I id) {
		return this.getRepository().deleteById(id);
	}

	public E get(I id) {
		return this.getRepository().findByIdOptional(id).orElseThrow(NotFoundException::new);
	}

	protected T getRepository() {
		return this.repository;
	}

	public PageDTO<E> list(int page, Integer size, String sort) {
		return  new PageDTO<E>(page,this.getRepository().count(), this.getRepository().findAll(Sort.by(sort)).page(Page.of(page, size)).list());
	}

	public E update(I id, E entity) {
		if (!entity.getId().equals(id)) {
			throw new BadRequestException();
		}
		return this.getRepository()
				.findByIdOptional(id)
				.map(old -> this.getRepository().getEntityManager().merge(entity))
				.orElseThrow(NotFoundException::new);
	}
}
