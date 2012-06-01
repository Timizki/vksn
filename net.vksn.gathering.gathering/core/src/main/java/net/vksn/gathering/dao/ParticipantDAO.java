package net.vksn.gathering.dao;

import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.gathering.model.Participant;

public interface ParticipantDAO {
	void store(Participant participant) throws EntityNotFoundException;
}
