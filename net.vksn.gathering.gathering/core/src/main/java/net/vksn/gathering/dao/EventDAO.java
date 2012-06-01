package net.vksn.gathering.dao;

import java.util.Collection;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.query.Query;
import net.vksn.gathering.model.Event;

public interface EventDAO {

	Event get(int id) throws EntityNotFoundException;
	
	Collection<Event> getByQuery(Query query);
	
	void store(Event event) throws EntityNotFoundException;
}
