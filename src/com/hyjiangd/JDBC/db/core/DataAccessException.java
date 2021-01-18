package com.hyjiangd.JDBC.db.core;

public class DataAccessException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataAccessException(String message) {
		super(message);
	}
	
	public DataAccessException(String message, Throwable ex) {
		super(message, ex);
	}
}
