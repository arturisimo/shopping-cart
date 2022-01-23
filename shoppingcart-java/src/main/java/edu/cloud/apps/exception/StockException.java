package edu.cloud.apps.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Not enought stock")
public class StockException extends ResponseStatusException {

	public StockException(String name) {
		super(HttpStatus.NOT_ACCEPTABLE, "Not enought stock of "+ name);
	}

	private static final long serialVersionUID = -6134645511146500349L;
	
}