package net.vksn.gathering.dao.hibernate;

import java.util.Collection;

import net.vksn.bedrock.dao.hibernate.AbstractHibernateDAO;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.query.Query;
import net.vksn.gathering.dao.InstanceDAO;
import net.vksn.gathering.model.Instance;

public class HibernateInstanceDAO extends AbstractHibernateDAO<Instance> implements InstanceDAO {

	public HibernateInstanceDAO() {
		super(Instance.class);
	}

	public Instance get(int id) throws EntityNotFoundException {
		return super.get(id);
	}

	public void store(Instance instance) throws EntityNotFoundException {
		super.store(instance);
	}

	@Override
	public Collection<Instance> getByQuery(Query query) {
		// TODO Auto-generated method stub
		return null;
	}
	public void remove(int id) throws EntityNotFoundException {
		super.remove(id);
	}
}
