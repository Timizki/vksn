package projectmanagement.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import projectmanagement.dao.PersonDao;
import projectmanagement.model.Person;
import projectmanagement.queries.PersonQuery;
//Tämä on DAO rajapinnan hibernate toteutus
public class PersonDaoHibernate extends HibernateDaoSupport implements PersonDao{
	private static final Logger log = Logger.getLogger(PersonDaoHibernate.class);
	
	/**
	 * Person olioiden poisto
	 * Merkkaa poisto päivämäärän personin deleted propertyyn.
	 */
	public Person deletePerson(int id) {
		Person person = getPerson(id);
		person.setDeleted(new Date());
		storePerson(person);
		return person;
	}
	/**
	 * Person olioiden tallennus
	 * Tallentaa person olion kantaan.
	 */
	public Person storePerson(Person person) {
		getHibernateTemplate().saveOrUpdate(person);
		return person;
	}

	/**
	 * Person olion haku
	 * Palauttaa person olion kannasta id:n perusteella
	 */
	public Person getPerson(int id) {		
		List results = getHibernateTemplate().find("select p from Person p where p.id = ?", id);
	
		Person person = (Person)results.get(0);

		if(log.isDebugEnabled()) {
			log.info("person found with id: " + id +" Persons name is "+person.getFullName());
		}
		return person;
	}
	/**
	 * Person olioiden haku
	 * Palauttaa <code>List</code>:n personeista
	 * Person rajaus tehdään <code>Criteria</code>:lla
	 */
	public List<Person> getPersons(PersonQuery query) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Person.class);
		populatePersonQuery(criteria, query);
		log.debug("Query returned "+criteria.list().size() +" persons");
		return criteria.list();
	}
	
	/**
	 * Asettaa rajaukset criteriaan queryn perusteella.
	 */
	private void populatePersonQuery(Criteria criteria, PersonQuery query) {
		if(query.isDeleted() != null) {
			if(query.isDeleted()){
				criteria.add(Restrictions.isNotNull("deleted"));
			}
			else {
				criteria.add(Restrictions.isNull("deleted"));
			}
		}
		
		if(query.getFirstname() != null) {
			criteria.add(Restrictions.ilike("firstname", query.getFirstname()));
		}
		if(query.getLastname() != null) {
			criteria.add(Restrictions.ilike("lastname", query.getLastname()));
		}
		if(query.getLoginName() != null) {
			criteria.add(Restrictions.ilike("username", query.getLoginName()));
		}
	}
}
