package net.vksn.bedrock.dao.hibernate;

import java.util.Collection;

import net.vksn.bedrock.dao.UserDAO;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.model.User;
import net.vksn.bedrock.query.UserQuery;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class HibernateUserDAO extends AbstractHibernateDAO<User> implements UserDAO {

	public HibernateUserDAO() {
		super(User.class);
	}
	
	@Transactional(readOnly = true)
	public Collection<User> getByQuery(UserQuery query) {		
		return super.getByQuery(query);
	}

	@Override
	public void store(User entity) throws EntityNotFoundException {
		super.store(entity);
	}

	@Transactional(readOnly = true)
	public User getUser(int id) throws EntityNotFoundException {
		return super.get(id);
	}

	@Transactional(readOnly = true)
	public User getUserByUsername(String username) throws EntityNotFoundException {
		Criteria criteria = createCriteria();
		criteria.add(Restrictions.eq("username", username));
		if(criteria.list().size() == 0) {
			throw new EntityNotFoundException();
		}
		return (User)criteria.list().iterator().next();
	}
}
