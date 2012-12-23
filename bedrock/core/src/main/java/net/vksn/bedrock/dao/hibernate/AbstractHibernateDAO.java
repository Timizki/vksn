package net.vksn.bedrock.dao.hibernate;

import java.util.Collection;
import java.util.Date;

import net.vksn.bedrock.dao.GenericDAO;
import net.vksn.bedrock.dao.util.impl.CriteriaPopulator;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.model.Entity;
import net.vksn.bedrock.query.Query;

import org.hibernate.Criteria;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component()
public abstract class AbstractHibernateDAO<T extends Entity> implements GenericDAO<T> {

	private Class<T> clazz;

	@Autowired
	private CriteriaPopulator populator;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public AbstractHibernateDAO(Class<T> entityClass) {
		this.clazz = entityClass;
	}
	
	protected CriteriaPopulator getPopulator() {
		return this.populator;
	}
	
	public void setPopulator(CriteriaPopulator populator) {
		this.populator = populator;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private SessionFactory getSessionFactory() {
		return sessionFactory;
	}
		
	@SuppressWarnings("unchecked")
	public T get(int id) throws EntityNotFoundException {
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.idEq(id));
		if (criteria.list().size() > 1) {
			throw new NonUniqueObjectException("1L", clazz.toString());
		}
		if (criteria.list().isEmpty()) {
			throw new EntityNotFoundException(clazz, id);
		}
		return (T) criteria.list().iterator().next();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Collection<T> getByQuery(Query query) {
		Criteria criteria = createCriteria();		
		populator.populateCriteria(criteria, query);		
		return criteria.list();
	}

	@Transactional
	public void store(T entity) throws EntityNotFoundException {
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
	}

	@Transactional
	public void remove(int id) throws EntityNotFoundException {
		T entity = get(id);
		getSessionFactory().getCurrentSession().delete(entity);
	}

	@Transactional
	public void delete(int id) throws EntityNotFoundException {
		T entity = get(id);
		entity.setDeleted(new Date());
		store(entity);
	}

	@Transactional
	public void undelete(int id) throws EntityNotFoundException {
		T entity = get(id);
		entity.setDeleted(null);
		store(entity);
	}

	protected Criteria createCriteria() {
		Session session = getSessionFactory().getCurrentSession();
		return session.createCriteria(clazz);
	}
}
