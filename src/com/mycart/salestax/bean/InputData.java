package com.mycart.salestax.bean;

public class InputData {
	private int quantity;
	private String productDesc;
	private boolean isImported;
	private double cost;

	public InputData() {
	}

	public InputData(int quantity, String productDescription, boolean isImported, double cost) {
		super();
		this.quantity = quantity;
		this.productDesc = productDescription;
		this.isImported = isImported;
		this.cost = cost;
	}

	public double getCost() {
		return cost;
	}

	public String getProductDescription() {
		return productDesc;
	}

	public int getQuantity() {
		return quantity;
	}

	public boolean isImported() {
		return isImported;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setImported(boolean isImported) {
		this.isImported = isImported;
	}

	public void setProduct(String product) {
		this.productDesc = product;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}