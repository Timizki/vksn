package net.vksn.bedrock.dao;

import java.util.Collection;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.model.Entity;
import net.vksn.bedrock.query.Query;

public interface GenericDAO<T extends Entity> {
	T get(int id) throws EntityNotFoundException;

	Collection<T> getByQuery(Query query);

	void store(T entity) throws EntityNotFoundException;

	void remove(int id) throws EntityNotFoundException;

	void delete(int id) throws EntityNotFoundException;

	void undelete(int id) throws EntityNotFoundException;

}
