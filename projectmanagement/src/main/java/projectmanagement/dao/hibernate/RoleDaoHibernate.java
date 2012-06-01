package projectmanagement.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import projectmanagement.dao.RoleDao;
import projectmanagement.model.Role;
import projectmanagement.queries.RoleQuery;

public class RoleDaoHibernate extends HibernateDaoSupport implements RoleDao {
	
	public Role getRole(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Role> getRoles(RoleQuery query) {
		// TODO Auto-generated method stub
		return null;
	}

	public Role deleteRole(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Role storeRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

}
