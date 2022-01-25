package edu.cloud.apps.exception;

public class NotFoundInCartException extends RuntimeException {

	private static final long serialVersionUID = -3680892547444807935L;

	public NotFoundInCartException(String message) {
		super(message);
	}
	
}