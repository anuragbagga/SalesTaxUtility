package com.mycart.salestax.exception;

public class MyCartException extends Exception {
	
	/**
	 * 
	 */
	private ExceptionInfo exceptionInfo;
	private static final long serialVersionUID = 1L;

	public MyCartException() {
		super();
	}
	
	/**
	 * @param exceptionInfo
	 * 
	 * 
	 */
	public MyCartException(ExceptionInfo exceptionInfo) {
		super();
		this.exceptionInfo=exceptionInfo;
	}
	
	/**
	 * This method is used to get the Exception info
	 * 
	 */
	public ExceptionInfo getExceptionInfo() {
		return exceptionInfo;
	}

}
