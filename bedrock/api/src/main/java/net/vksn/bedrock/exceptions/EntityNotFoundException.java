package net.vksn.bedrock.exceptions;

import net.vksn.bedrock.model.Entity;

public class EntityNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object identifier;
	private Class<? extends Entity> entityClass;
	
	public Object getEntityIdentifier() {
		return this.identifier;
	}
	
	public Class<? extends Entity> getEntityClass() {
		return entityClass;
	}
	
	public EntityNotFoundException() {
		super();
	}
	
	public EntityNotFoundException(Class<? extends Entity> clazz, Object identifier) {
		super("Could not entity " + clazz.getCanonicalName() + " with identifier " + identifier);
		this.identifier = identifier;
		this.entityClass = clazz;
	}
	
	
}
