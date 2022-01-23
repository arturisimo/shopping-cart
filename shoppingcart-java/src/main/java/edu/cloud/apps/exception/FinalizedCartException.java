package edu.cloud.apps.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Shooping cart is finalized")
public class FinalizedCartException extends ResponseStatusException {

	public FinalizedCartException(Long id) {
		super(HttpStatus.NOT_ACCEPTABLE, "Shooping cart is finalized: " + id );
	}

	private static final long serialVersionUID = -6134645511146500349L;
	
}