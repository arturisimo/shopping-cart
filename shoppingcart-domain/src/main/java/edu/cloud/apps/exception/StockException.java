package edu.cloud.apps.exception;

public class StockException extends RuntimeException {
	
	private static final long serialVersionUID = -5175220772004337477L;
	
	public StockException(String product) {
		super("Not enought stock of "+ product);
	}
	
}
