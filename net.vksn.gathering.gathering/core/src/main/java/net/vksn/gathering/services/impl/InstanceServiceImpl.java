package net.vksn.gathering.services.impl;


import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.gathering.dao.InstanceDAO;
import net.vksn.gathering.model.Instance;
import net.vksn.gathering.model.Participant;
import net.vksn.gathering.services.InstanceService;
import net.vksn.gathering.services.ParticipantService;

import org.springframework.transaction.annotation.Transactional;

public class InstanceServiceImpl implements InstanceService {
	private InstanceDAO instanceDAO;
	private ParticipantService participantService;
	
	public void setInstanceDAO(InstanceDAO instanceDAO) {
		this.instanceDAO = instanceDAO;
	}
	
	public void setParticipantService(ParticipantService participantService) {
		this.participantService = participantService;
	}

	@Transactional(readOnly = true)
	public Instance get(int id) throws EntityNotFoundException {
		return instanceDAO.get(id);
	}

	@Transactional
	public void store(Instance instance) throws EntityNotFoundException {
		instanceDAO.store(instance);
	}
	
	@Transactional
	public void addParticipant(Instance instance, Participant participant) throws EntityNotFoundException {
		this.participantService.store(participant);
		instance.getParticipants().add(participant);
		this.store(instance);
	}

	@Transactional
	public void remove(int id) throws EntityNotFoundException {
		this.instanceDAO.remove(id);
	}
}
