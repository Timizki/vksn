package net.vksn.gathering.services.impl;

import org.springframework.transaction.annotation.Transactional;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.gathering.dao.ParticipantDAO;
import net.vksn.gathering.model.Participant;
import net.vksn.gathering.services.ParticipantService;

public class ParticipantServiceImpl implements ParticipantService {
	private ParticipantDAO participantDAO;
	
	public void setParticipantDAO(ParticipantDAO participantDAO) {
		this.participantDAO = participantDAO;
	}
	
	@Transactional
	public void store(Participant participant) throws EntityNotFoundException {
		this.participantDAO.store(participant);
	}

}
