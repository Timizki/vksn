package net.vksn.bedrock.services.impl;

import java.util.Collection;

import net.vksn.bedrock.dao.GenericDAO;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.model.Entity;
import net.vksn.bedrock.query.Query;
import net.vksn.bedrock.services.GenericService;

import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractGenericService<T extends Entity> implements GenericService<T> {

	protected GenericDAO<T> dao;
	
	public void setDao(GenericDAO<T> dao) {
		this.dao = dao;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<T> getByQuery(Query query) {
		return dao.getByQuery(query);
	}

	@Transactional
	@Override
	public void store(T entity) throws EntityNotFoundException {
		dao.store(entity);
	}

	@Transactional(readOnly = true)
	@Override
	public T get(int id) throws EntityNotFoundException {
		return this.dao.get(id);
	}
	
	@Transactional
	@Override
	public void remove(int id) throws EntityNotFoundException {
		this.dao.remove(id);
	}

	@Transactional
	@Override
	public void delete(int id) throws EntityNotFoundException {
		this.dao.delete(id);
	}

	@Transactional
	@Override
	public void undelete(int id) throws EntityNotFoundException {
		this.dao.undelete(id);
	}
}
