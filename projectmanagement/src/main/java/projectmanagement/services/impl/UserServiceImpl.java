package projectmanagement.services.impl;

import java.util.List;

import projectmanagement.dao.PersonDao;
import projectmanagement.dao.RoleDao;
import projectmanagement.model.Person;
import projectmanagement.model.Role;
import projectmanagement.queries.PersonQuery;
import projectmanagement.queries.RoleQuery;
import projectmanagement.services.UserService;


public class UserServiceImpl implements UserService{
	
	private RoleDao roleDao;
	private PersonDao personDao;
	
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public Person getPerson(int id) {
		return personDao.getPerson(id);
	}

	public List<Person> getPersons(PersonQuery query) {
		return personDao.getPersons(query);
	}

	public Role getRole(int id) {
		return roleDao.getRole(id);
	}

	public List<Role> getRoles(RoleQuery query) {
		return roleDao.getRoles(query);
	}

	public Person deletePerson(int id) {
		return personDao.deletePerson(id);
	}

	public Role deleteRole(int id) {
		return roleDao.deleteRole(id);
	}

	public Person storePerson(Person person) {
		return personDao.storePerson(person);
	}

	public Role storeRole(Role role) {
		return roleDao.storeRole(role);
	}
}
