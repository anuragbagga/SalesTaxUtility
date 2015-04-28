package com.mycart.salestax.exception;

import java.util.Arrays;

public class ApplicationService {

	/**
	 * This method handles exception at Application level and throws MyCartException
	 * 
	 * @param messageKey
	 *            String
	 * @param logLevel
	 *            String
	 * @throws 
	 */
	public static void raiseException(String msgCode, Exception exception) throws MyCartException {
		ExceptionInfo exceptionInfo = new ExceptionInfo();
		exceptionInfo.setCode(msgCode);
		exceptionInfo.setStackTrace(getExceptionStackTraceAsString(exception));
		throw new MyCartException(exceptionInfo);
	}

	/**
	 * This method handles exception at System level and throws SystemException
	 * 
	 * @param messageKey
	 *            String
	 * @param stackTrace
	 *            String
	 * @param logLevel
	 *            String
	 * @param throwable
	 *            Throwable
	 * @throws SystemException
	 */
	
	
	public static void raiseSytemException(String messageKey,
			Exception exception) throws SystemException {
		ExceptionInfo exceptionInfo = new ExceptionInfo();
		exceptionInfo.setCode(messageKey);
		exceptionInfo.setStackTrace(getExceptionStackTraceAsString(exception));
		throw new SystemException(exceptionInfo, exception);
	}

	
	/**
	 * This methods sends the stack trace in string format
	 * 
	 * @param exception
	 *            Exception
	 * @return String
	 */
	public static String getExceptionStackTraceAsString(Exception exception) {
		return exception.toString() + "\n"
				+ Arrays.asList(exception.getStackTrace()).toString();
	}

}
