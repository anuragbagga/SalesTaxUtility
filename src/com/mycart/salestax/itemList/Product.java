/**
 * Abstract class that defines default operations for products
 * */
package com.mycart.salestax.itemList;

import java.math.BigDecimal;

import com.mycart.salestax.constants.Constants;


public abstract class Product {
	private String name;
	private double cost;
	private double salesTaxPercent;
	private int quantity;
	private boolean isImported;

	public Product(String name, double cost, int quantity, boolean isImported) {
		this.name = name;
		this.cost = cost;
		this.quantity = quantity;
		this.salesTaxPercent = Constants.DEFAULT_FOOD_ITEMS;
		this.isImported = isImported;
	}

	public double getCost() {
		return this.cost * this.quantity;
	}

	public String getName() {
		return this.name;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public double getSalesTax() {
		double totalSalesTaxForThisProduct;
		if (isImported) {
			totalSalesTaxForThisProduct = ((this.salesTaxPercent + Constants.IMPORTED_FOOD_ITEMS)
					* this.cost * this.quantity) / 100;

		} else
			totalSalesTaxForThisProduct = (this.salesTaxPercent * this.cost * this.quantity) / 100;

		return roundUpSalesTax(totalSalesTaxForThisProduct, Constants.NEAREST_CHANGE);
	}

	public boolean isImported() {
		return this.isImported;
	}

	private double roundUpSalesTax(double totalSalesTaxForThisProduct, int nearestFiveCents) {
		BigDecimal bigDecimalRepresentation = new BigDecimal(
				Double.toString(totalSalesTaxForThisProduct));
		double scaledNumber = bigDecimalRepresentation.setScale(Constants.REQUIRED_DECIMAL_PLACES,
				BigDecimal.ROUND_HALF_UP).doubleValue() * 100;
		double resolution = scaledNumber % Constants.NEAREST_CHANGE;
		if (resolution != 0) {
			scaledNumber += Constants.NEAREST_CHANGE - resolution;
		}
		return scaledNumber / 100;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setSalesTaxPercent(double salesTaxPercent) {
		this.salesTaxPercent = salesTaxPercent;
	}

}
