package net.scrappersite.ethryl.exceptions;

public class NotFoundException extends Exception {
	private Exception wrappedException = null;
	
	public NotFoundException() {
		super();
	}
	
	public NotFoundException(Exception e) {
		super();
		wrappedException = e;
	}
	
	public NotFoundException(String s) {
		super(s);
	}
}
