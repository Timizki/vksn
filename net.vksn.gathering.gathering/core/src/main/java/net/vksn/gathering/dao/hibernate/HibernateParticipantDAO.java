package net.vksn.gathering.dao.hibernate;

import java.util.Collection;

import net.vksn.bedrock.dao.hibernate.AbstractHibernateDAO;
import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.bedrock.query.Query;
import net.vksn.gathering.dao.ParticipantDAO;
import net.vksn.gathering.model.Participant;

public class HibernateParticipantDAO extends AbstractHibernateDAO<Participant> implements ParticipantDAO {

	public HibernateParticipantDAO() {
		super(Participant.class);
	}
	
	public void store(Participant participant) throws EntityNotFoundException {
			super.store(participant);
	}

	@Override
	public Collection<Participant> getByQuery(Query query) {
		return super.getByQuery(query);
	}

}
