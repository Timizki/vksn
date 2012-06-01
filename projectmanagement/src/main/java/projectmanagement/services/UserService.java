package projectmanagement.services;

import java.util.List;


import projectmanagement.model.Role;
import projectmanagement.model.Person;
import projectmanagement.queries.PersonQuery;
import projectmanagement.queries.RoleQuery;

public interface UserService {
	//Methods for Person
	Person getPerson(int id);
	
	List<Person> getPersons(PersonQuery query);
	
	Person storePerson(Person person);
	
	Person deletePerson(int id);
	
	//Methods for Role
	Role getRole(int id);
	
	List<Role> getRoles(RoleQuery query);
	
	Role storeRole(Role role);
	
	Role deleteRole(int id);
}
