package net.vksn.gathering.services;


import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.gathering.model.Instance;
import net.vksn.gathering.model.Participant;

public interface InstanceService {

	Instance get(int id) throws EntityNotFoundException;
	void store(Instance instance) throws EntityNotFoundException;
	void addParticipant(Instance instance, Participant participant) throws EntityNotFoundException;
	void remove(int id) throws EntityNotFoundException;
}
