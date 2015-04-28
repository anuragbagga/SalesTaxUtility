/**
 * This class returns appropriate product object based on the input from customer
 * 
 * 
 * */

package com.mycart.salestax.itemList;

import com.mycart.salestax.constants.Constants;


public class ProductFactory {

	private static ProductFactory instance;

	public static ProductFactory getInstance() {
		if (instance == null) {
			instance = new ProductFactory();
		}
		return instance;
	}

	public Product createProduct(String productType, String name, double cost, int quantity,
			boolean isImported) {

		Product product = null;

		if (productType.equals(Constants.BOOK_PRODUCT)) {
			product = new BookItem(name, cost, quantity, isImported);
		} else if (productType.equals(Constants.FOOD_PRODUCT)) {
			product = new FoodItem(name, cost, quantity, isImported);
		} else if (productType.equals(Constants.MEDICAL_PRODUCT)) {
			product = new MedicalItem(name, cost, quantity, isImported);
		} else {
			product = new OtherItem(name, cost, quantity, isImported);
		}

		return product;
	}

}
