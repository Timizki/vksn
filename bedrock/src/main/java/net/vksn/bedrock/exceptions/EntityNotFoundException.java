package net.vksn.bedrock.exceptions;

public class EntityNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException() {
		super();
	}
	
	public EntityNotFoundException(Class clazz, Object id) {
		super("Could not entity " + clazz.getCanonicalName() + " with id " + id);
	}
	
	
}
