package net.vksn.gathering.services;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.gathering.model.Participant;

public interface ParticipantService {
	void store(Participant participant) throws EntityNotFoundException;
}
