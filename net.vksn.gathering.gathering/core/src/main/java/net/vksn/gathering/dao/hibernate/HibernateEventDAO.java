package net.vksn.gathering.dao.hibernate;

import java.util.Collection;

import net.vksn.bedrock.dao.hibernate.AbstractHibernateDAO;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.query.Query;
import net.vksn.gathering.dao.EventDAO;
import net.vksn.gathering.model.Event;
import net.vksn.gathering.query.EventQuery;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class HibernateEventDAO extends AbstractHibernateDAO<Event> implements EventDAO {

	public HibernateEventDAO() {
		super(Event.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Event> getByQuery(Query query) {
		return super.getByQuery(query);
	}
	
	@Override
	public Event get(int id) throws EntityNotFoundException {		
		return super.get(id);
	}

	public void store(Event event) throws EntityNotFoundException {
		super.store(event);
	}
	
	//TODO: resolve meaning of this
	
//	protected void populateEventQuery(Criteria criteria, EventQuery query) {
//		if(query.getUser() != null) {
//			criteria.add(Restrictions.eq("owner", query.getUser()));
//		}
//	}
}
