package it.drwolf.base.resources;

import java.lang.reflect.ParameterizedType;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.narayana.jta.QuarkusTransaction;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import it.drwolf.base.model.dtos.PageDTO;
import it.drwolf.base.model.entities.BaseEntity;
import it.drwolf.base.utils.HasLogger;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

public abstract class CrudResource<T extends PanacheRepositoryBase<E, I>, E extends BaseEntity<I>, I>
		implements HasLogger {

	public static final String DEFAULT_PAGE_SIZE = "1000";

	protected final T repository;

	public CrudResource() {
		ParameterizedType genericSuperclass = null;
		try {
			genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		} catch (Exception e) {
			genericSuperclass = (ParameterizedType) ((Class<?>) this.getClass()
					.getGenericSuperclass()).getGenericSuperclass();
		}
		Class<T> actualTypeArgument = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
		this.repository = CDI.current().select(actualTypeArgument).get();
	}

	public E add(E entity) {
		QuarkusTransaction.requiringNew().run(() -> {
			this.getRepository().persist(entity);
		});
		this.repository.getEntityManager().clear();
		return get(entity.getId());
	}

	public boolean delete(I id) {
		return QuarkusTransaction.requiringNew().call(() -> this.getRepository().deleteById(id));
	}

	public E get(I id) {
		return this.getRepository().findByIdOptional(id).orElseThrow(NotFoundException::new);
	}

	protected T getRepository() {
		return this.repository;
	}

	public PageDTO<E> list(int page, Integer size, String sort) {
		return new PageDTO<E>(page, size, this.getRepository().count(),
				this.getRepository().findAll(Sort.by(sort)).page(Page.of(page, size)).list());
	}

	public E update(I id, E entity) {
		QuarkusTransaction.requiringNew().run(() -> {
			if (!entity.getId().equals(id)) {
				throw new BadRequestException();
			}
			this.getRepository()
					.findByIdOptional(id)
					.map(old -> this.getRepository().getEntityManager().merge(entity))
					.orElseThrow(NotFoundException::new);
		});
		this.repository.getEntityManager().clear();
		return get(id);
	}
}
