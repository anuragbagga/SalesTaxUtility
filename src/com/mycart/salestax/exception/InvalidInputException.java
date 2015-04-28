package com.mycart.salestax.exception;

public class InvalidInputException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		// Can log these messages to a log file
		return "Invalid file format";
	}

	@Override
	public void printStackTrace() {
		super.printStackTrace();
	}
}

