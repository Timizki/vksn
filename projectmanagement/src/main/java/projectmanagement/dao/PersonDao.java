package projectmanagement.dao;

import java.util.List;

import projectmanagement.model.Person;
import projectmanagement.queries.PersonQuery;

public interface PersonDao {
	
	Person getPerson(int id);
	
	List<Person> getPersons(PersonQuery query);
	
	Person storePerson(Person person);
	
	Person deletePerson(int id);

}
