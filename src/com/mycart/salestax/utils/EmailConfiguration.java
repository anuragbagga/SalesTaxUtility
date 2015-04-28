package com.mycart.salestax.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import com.mycart.salestax.constants.Constants;
import com.mycart.salestax.exception.ApplicationService;
import com.mycart.salestax.exception.ExceptionInfo;

public class EmailConfiguration extends ApplicationService {

	public EmailConfiguration() {
		super();
	}

	private static Properties emailProperty = new Properties();
	
	static {
		try {
				
			InputStream is =Thread.currentThread().getContextClassLoader() 
					.getResourceAsStream("Mycart.properties");
			emailProperty.load(is);
		} catch (IOException ioe) {
			raiseSytemException(Constants.ERRCODE_IO_EXCEPTION, ioe);
		} catch (Exception e) {
			raiseSytemException(Constants.ERRCODE_EXCEPTION, e);
		}
	}

	
	/**
	 * @param property
	 *            property to read from property value.
	 * @return value corresponding to the property
	 */
	public static String getProperty(String property) {
		return emailProperty.getProperty(property);
	}
}
