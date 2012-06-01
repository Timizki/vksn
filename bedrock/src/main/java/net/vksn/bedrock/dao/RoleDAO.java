package net.vksn.bedrock.dao;

import java.util.Collection;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.model.Role;

public interface RoleDAO {
	Collection<Role> getAllRoles();
	void store(Role role) throws EntityNotFoundException;
	Role getRoleByAuthority(String authority);
}
