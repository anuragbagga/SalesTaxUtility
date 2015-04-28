package com.mycart.salestax.itemList;

import com.mycart.salestax.constants.Constants;


public class FoodItem extends Product {

	private double salesTaxPercent = Constants.FOOD_ITEM_SALES_TAX;

	public FoodItem(String name, double cost, int quantity, boolean isImported) {
		super(name, cost, quantity, isImported);
		super.setSalesTaxPercent(this.salesTaxPercent);
	}

}
