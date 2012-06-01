package net.vksn.bedrock.web.editors;

import net.vksn.bedrock.model.Role;
import net.vksn.bedrock.services.RoleService;

import org.springframework.beans.propertyeditors.ClassEditor;

public class RoleEditor extends ClassEditor {
	private RoleService roleService;
	
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@Override
	public void setAsText(String authority) throws IllegalArgumentException {
		Role role = this.roleService.getRoleByAuthority(authority);
		setValue(role);
	}
	
}
