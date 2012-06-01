package net.vksn.gathering.dao;


import net.vksn.bedrock.exceptions.EntityNotFoundException;
import net.vksn.gathering.model.Instance;

public interface InstanceDAO {
	Instance get(int id) throws EntityNotFoundException;
	void store(Instance instance) throws EntityNotFoundException;
	void remove(int id) throws EntityNotFoundException;
}
