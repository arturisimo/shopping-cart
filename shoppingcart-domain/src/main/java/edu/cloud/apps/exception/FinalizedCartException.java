package edu.cloud.apps.exception;

public class FinalizedCartException extends RuntimeException {
	
	private static final long serialVersionUID = 1904703295505963383L;

	public FinalizedCartException(Long id) {
		super("Shooping cart is finalized: " + id );
	}
	
}

