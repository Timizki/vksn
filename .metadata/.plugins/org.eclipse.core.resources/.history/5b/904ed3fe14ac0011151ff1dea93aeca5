package net.vksn.bedrock.dao.hibernate;

import java.util.Collection;

import net.vksn.bedrock.dao.RoleDAO;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.model.Role;
import net.vksn.bedrock.query.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class HibernateRoleDAO extends AbstractHibernateDAO<Role> implements RoleDAO {

	public HibernateRoleDAO() {
		super(Role.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection getByQuery(Query query) {
		throw new RuntimeException("Method not supported");
	}

	@SuppressWarnings("unchecked")
	public Collection<Role> getAllRoles() {
		Criteria criteria  = createCriteria();
		return criteria.list();
	}

	@Override
	public void store(Role entity) throws EntityNotFoundException  {
		super.store(entity);
	}

	public Role getRoleByAuthority(String authority) {
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("authority", authority));
		return (Role)criteria.list().iterator().next();
	}
}
