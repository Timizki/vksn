package net.vksn.bedrock.services;

import java.util.Collection;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.model.Role;

public interface RoleService {
	Collection<Role> getAllRoles();
	void store(Role role) throws EntityNotFoundException;
	Role getRoleByAuthority(String Authority);
}
