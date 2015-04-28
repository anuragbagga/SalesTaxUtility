package com.mycart.salestax.constants;

/*
 * This class contains Sales Tax percentage for shopping items
 * 
 */

public class Constants {

	// Non Zero Tax Items
	public final static double DEFAULT_FOOD_ITEMS = 10;
	public final static double IMPORTED_FOOD_ITEMS = 5;
	
	// All Other Tax Items
	public final static double BOOK_SALES_TAX = 0;
	public final static double MEDICAL_ITEMS_SALES_TAX = 0;
	public final static double FOOD_ITEM_SALES_TAX = 0;
	
	// Category Constants
	public static final String BOOK_PRODUCT = "Book";
	public static final String FOOD_PRODUCT = "Food";
	public static final String MEDICAL_PRODUCT = "Medical";
	public static final String OTHER_ITEMS = "Other";
	
	public static final int NEAREST_CHANGE = 5;
	public static final int REQUIRED_DECIMAL_PLACES = 2;

	//Error Constants
	public static final String FILE_INPUT_ERROR ="101:File name not provided";
	public static final String ERRCODE_IO_EXCEPTION="102:IO Exception occured";
	public static final String ERRCODE_EXCEPTION="103:System Exception occured";
	
	//
	public static final String FILE_EXTN="txt";

	public static final String GEO_LOOKUP_URL = "http://maps.googleapis.com/maps/api/geocode/json";
	
}
