package net.vksn.bedrock.exceptions;

public class NonUniqueEntityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NonUniqueEntityException() {
		super();
	}
	
	@SuppressWarnings("rawtypes")
	public NonUniqueEntityException(Class clazz) {
		super(clazz.toString());
	}	
	
	@SuppressWarnings("rawtypes")
	public NonUniqueEntityException(Class clazz, String identifier) {
		super("Multiple " + clazz.getName() + "where found with given identifier "+identifier);
	}
}
