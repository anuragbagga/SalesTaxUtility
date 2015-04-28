package com.mycart.salestax.exception;

import java.io.Serializable;

/**
 * This is a exception class which sets the exception message and provide
 * functionality to display error message
 * 
 * 
 */
public class ExceptionInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;

	private String stackTrace;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

}
