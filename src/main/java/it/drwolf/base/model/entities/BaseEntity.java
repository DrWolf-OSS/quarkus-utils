package it.drwolf.base.model.entities;

import java.util.Optional;

public abstract class BaseEntity<T> {

	@Override
	public boolean equals(Object obj) {
		try {
			return this.getId() != null && ((BaseEntity) obj).toString().equals(this.toString());
		} catch (Exception e) {
			return false;
		}
	}

	public abstract T getId();


	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	public String toString() {
		return String.format("%s #%s", this.getClass().getSimpleName(), this.getId());
	}
}