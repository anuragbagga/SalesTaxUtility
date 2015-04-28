package com.mycart.salestax.itemList;

import com.mycart.salestax.constants.Constants;


public class BookItem extends Product {

	private double salesTaxPercent = Constants.BOOK_SALES_TAX;

	public BookItem(String title, double cost, int quantity, boolean isImported) {
		super(title, cost, quantity, isImported);
		super.setSalesTaxPercent(this.salesTaxPercent);
	}

}
