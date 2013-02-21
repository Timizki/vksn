package net.vksn.bedrock.services.impl;

import java.util.Collection;

import net.vksn.bedrock.dao.UserDAO;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.model.User;
import net.vksn.bedrock.query.Query;
import net.vksn.bedrock.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;	
	
	@Transactional
	public User getUserByUsername(String username)
			throws EntityNotFoundException {
		
		return userDAO.getUserByUsername(username);
	}

	@Override
	public User get(int id) throws EntityNotFoundException {
		return userDAO.get(id);
	}

	@Override
	public Collection<User> getByQuery(Query query) {
		return userDAO.getByQuery(query);
	}

	@Override
	public void store(User entity) throws EntityNotFoundException {
		userDAO.store(entity);
	}

	@Override
	public void remove(int id) throws EntityNotFoundException {
		userDAO.remove(id);
		
	}

	@Override
	public void delete(int id) throws EntityNotFoundException {
		userDAO.delete(id);
	}

	@Override
	public void undelete(int id) throws EntityNotFoundException {
		userDAO.undelete(id);
	}
}
