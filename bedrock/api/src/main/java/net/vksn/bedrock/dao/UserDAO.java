package net.vksn.bedrock.dao;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.model.User;

public interface UserDAO extends GenericDAO<User> {
	User getUserByUsername(String username) throws EntityNotFoundException;
}
