package net.vksn.bedrock.dao.hibernate;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import net.vksn.bedrock.dao.FetchModeEnum;
import net.vksn.bedrock.dao.GenericDAO;
import net.vksn.bedrock.dao.util.impl.CriteriaPopulator;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.model.Entity;
import net.vksn.bedrock.query.Query;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component()
public abstract class AbstractHibernateDAO<T extends Entity> implements
		GenericDAO<T> {

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

	private void setFetchModes(Criteria criteria,
			Map<String, FetchModeEnum> fetchModes) {
		for (String key : fetchModes.keySet()) {
			FetchMode fetchMode = null;
			FetchModeEnum fetchModeAsEnum = fetchModes.get(key);
			switch (fetchModeAsEnum) {
			case JOIN:
				fetchMode = FetchMode.JOIN;
				break;
			case SELECT:
				fetchMode = FetchMode.SELECT;
				break;
			default:
				fetchMode = FetchMode.DEFAULT;
				break;
			}

			criteria.setFetchMode(key, fetchMode);
		}
	}

	@SuppressWarnings("unchecked")
	public T get(int id, Map<String, FetchModeEnum> fetchModes)
			throws EntityNotFoundException {
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.idEq(id));

		if (fetchModes != null) {
			setFetchModes(criteria, fetchModes);
		}
	
		T entity = (T) criteria.setCacheable(true).uniqueResult(); 
		if (entity == null) {
			throw new EntityNotFoundException(clazz, id);
		}
	
		return entity;
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
		Session session = getSessionFactory().getCurrentSession();
		session.saveOrUpdate(entity);
		session.flush();
	}

	@Transactional
	public void remove(int id) throws EntityNotFoundException {
		T entity = get(id, null);
		getSessionFactory().getCurrentSession().delete(entity);
	}

	@Transactional
	public void delete(int id) throws EntityNotFoundException {
		T entity = get(id, null);
		entity.setDeleted(new Date());
		store(entity);
	}

	@Transactional
	public void undelete(int id) throws EntityNotFoundException {
		T entity = get(id, null);
		entity.setDeleted(null);
		store(entity);
	}

	protected Criteria createCriteria() {
		Session session = getSessionFactory().getCurrentSession();
		return session.createCriteria(clazz);
	}
}
