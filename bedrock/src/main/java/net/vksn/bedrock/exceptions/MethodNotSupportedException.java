package net.vksn.bedrock.exceptions;

@SuppressWarnings("serial")
public class MethodNotSupportedException extends Exception {
	public MethodNotSupportedException() {
		super();
	}
	
	public MethodNotSupportedException(String message) {
		super(message);
	}
	
	@SuppressWarnings("rawtypes")
	public MethodNotSupportedException(String methodName, Class clazz) {
		super(clazz.getName() + "does not support "+methodName+"!!!");
	}
}
