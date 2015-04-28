package com.mycart.salestax.exception;

public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ExceptionInfo exceptionInfo;

	/**
	 * Parameterized constructor
	 * 
	 * @param exceptionInfo ExceptionInfo
	 * @param throwable Throwable
	 */
	
	public SystemException(ExceptionInfo exceptionInfo, Throwable throwable) {
		super();
		super.initCause(throwable);
		this.exceptionInfo = exceptionInfo;
	}

	/**
	 * Getter for exceptionInfo
	 * 
	 * @return ExceptionInfo
	 */
	public ExceptionInfo getExceptionInfo() {
		return exceptionInfo;
	}
}

