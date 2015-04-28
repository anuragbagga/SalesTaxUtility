package com.mycart.salestax.itemList;

import com.mycart.salestax.constants.Constants;

public class MedicalItem extends Product {

	private double salesTaxPercent = Constants.MEDICAL_ITEMS_SALES_TAX;

	public MedicalItem(String name, double cost, int quantity, boolean isImported) {
		super(name, cost, quantity, isImported);
		super.setSalesTaxPercent(this.salesTaxPercent);
	}

}
