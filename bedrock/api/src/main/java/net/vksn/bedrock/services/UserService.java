package net.vksn.bedrock.services;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.model.User;

public interface UserService extends GenericService<User> {
	User getUserByUsername(String username) throws EntityNotFoundException;
}
