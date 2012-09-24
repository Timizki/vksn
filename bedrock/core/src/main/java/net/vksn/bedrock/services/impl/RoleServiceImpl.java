package net.vksn.bedrock.services.impl;

import java.util.Collection;

import net.vksn.bedrock.dao.RoleDAO;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.model.Role;
import net.vksn.bedrock.services.RoleService;

public class RoleServiceImpl implements RoleService {

	private RoleDAO roleDAO;
	
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public Collection<Role> getAllRoles() {
		return this.roleDAO.getAllRoles();
	}

	public void store(Role role) throws EntityNotFoundException {
		roleDAO.store(role);
	}

	public Role getRoleByAuthority(String Authority) {
		return roleDAO.getRoleByAuthority(Authority);
	}
	
}
