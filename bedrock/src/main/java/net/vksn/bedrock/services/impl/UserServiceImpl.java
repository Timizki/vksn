package net.vksn.bedrock.services.impl;

import net.vksn.bedrock.dao.UserDAO;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.model.User;
import net.vksn.bedrock.services.UserService;

import org.springframework.transaction.annotation.Transactional;

public class UserServiceImpl extends AbstractGenericService<User> implements UserService {
	
	@Transactional
	public User getUserByUsername(String username)
			throws EntityNotFoundException {
		return ((UserDAO)this.dao).getUserByUsername(username);
	}
}
