package projectmanagement.dao;

import java.util.List;

import projectmanagement.model.Role;
import projectmanagement.queries.RoleQuery;

public interface RoleDao {
	
	Role getRole(int id);
	
	List<Role> getRoles(RoleQuery query);
	
	Role storeRole(Role role);
	
	Role deleteRole(int id);
}
