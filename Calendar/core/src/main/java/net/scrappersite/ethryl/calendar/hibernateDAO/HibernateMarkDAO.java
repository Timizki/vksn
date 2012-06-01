package net.scrappersite.ethryl.calendar.hibernateDAO;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import net.scrappersite.ethryl.calendar.DAO.MarkDAO;
import net.scrappersite.ethryl.calendar.model.Mark;
import net.scrappersite.ethryl.calendar.services.MarkQuery;
import net.scrappersite.ethryl.calendar.services.QueryResults;
import net.scrappersite.ethryl.exceptions.NotFoundException;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateMarkDAO extends HibernateDaoSupport implements MarkDAO {
	private static Logger log = Logger.getLogger(HibernateMarkDAO.class);
	
	public void populateMarkQuery(Criteria criteria, MarkQuery query) {
		if (query.getCreatedBy() != null) {
			criteria.add(Restrictions.like("createdBy", query.getCreatedBy()));
		}
		if (query.getUser() != null) {
			criteria.add(Restrictions.like("user", query.getUser()));
		}
	}
	
	public QueryResults getMarks(MarkQuery query) {
		Session session = getSession();
		QueryResults results = new QueryResults();
		Criteria count = session.createCriteria(Mark.class);
		populateMarkQuery(count, query);
		int total = ((Number)count.list().get(0)).intValue();
		if (total > 0) {
			results.setMaxPagesElements(total);
			
			Criteria elements = session.createCriteria(Mark.class);
			elements.addOrder(Order.desc("date"));
			populateMarkQuery(elements, query);
			
			Collection pageElements = elements.list();
			
			for(Iterator i = pageElements.iterator(); i.hasNext(); ) {
				Mark mark = (Mark)i.next();
				Hibernate.initialize(mark);
			}
			results.setElements(pageElements);
		}
		else {
			results.setMaxPagesElements(0);
		}
		
		return results;
	}
	
	public Mark getMark(int id) throws NotFoundException{
			HibernateTemplate t = getHibernateTemplate();
			Collection results = t.findByNamedParam("select m from Mark m " +
									"where id = :id", "id", id);
			if(results == null) {
				throw new NotFoundException();
			}		
		return (Mark)results;
	}
	
	public void storeMark(Mark mark) {
		getHibernateTemplate().saveOrUpdate(mark);
	}
	
	public void deleteMark(int id) throws NotFoundException {
		Mark mark = null;
		try {
			mark = getMark(id);
			mark.setDeleted(new Date());
		}
		catch (NotFoundException e) {
			log.error("Cannot delete mark: ", e);
		}
		getHibernateTemplate().update(mark);
	}
	
	public Mark undeleteMark(int id) throws NotFoundException {
		Mark mark = null;
		try {
			mark = getMark(id);
			mark.setDeleted(null);
		}
		catch (NotFoundException e) {
			log.error("Cannot undelete mark: ", e);
		} 
		getHibernateTemplate().update(mark);
		return mark;
	}
	
	public void removeMark(int id) throws NotFoundException {
		Mark mark = null;
		try {
			mark = getMark(id);
		}
		catch (NotFoundException e) {
			log.error("Cannot remove mark: ", e);
		}
		getHibernateTemplate().delete(mark);
	}
}
