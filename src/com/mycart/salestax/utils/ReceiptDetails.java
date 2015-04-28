package com.mycart.salestax.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.mycart.salestax.constants.Constants;
import com.mycart.salestax.interfaces.ShoppingCartI;
import com.mycart.salestax.itemList.Product;



public class ReceiptDetails {

	private static class ReceiptItems {
		private int quantity;
		private String name;
		private BigDecimal totalCost;
		private BigDecimal salesTax;
		private boolean isImported;

		public ReceiptItems(int quantity, String name, double salesTax, double totalCost,
				boolean isImported) {
			this.quantity = quantity;
			this.name = name;
			this.salesTax = new BigDecimal(Double.toString(salesTax)).setScale(2,
					BigDecimal.ROUND_HALF_EVEN);
			this.totalCost = new BigDecimal(Double.toString(totalCost)).setScale(2,
					BigDecimal.ROUND_HALF_EVEN);
			this.isImported = isImported;
		}

	}

	private ArrayList<ReceiptItems> receiptItemsList;
	private double grandSalesTaxesTotal = 0;
	private double grandBaseCostTotal = 0;
	private double grandOverallTotal = 0;

	private ShoppingCartI cart;

	public ReceiptDetails(ShoppingCartI cart) {
		this.cart = cart;
		this.receiptItemsList = new ArrayList<ReceiptItems>();
		generateReceipt();
	}

	/** Get the grand total for this purchase */
	public BigDecimal getTotal() {
		BigDecimal total = new BigDecimal(Double.toString(grandOverallTotal)).setScale(
				Constants.REQUIRED_DECIMAL_PLACES, BigDecimal.ROUND_HALF_EVEN);
		return total;
	}

	/** Get the total sales tax for this purchase */
	public BigDecimal getSalesTax() {

		BigDecimal salesTaxes = new BigDecimal(Double.toString(grandSalesTaxesTotal)).setScale(
				Constants.REQUIRED_DECIMAL_PLACES, BigDecimal.ROUND_HALF_EVEN);
		return salesTaxes;

	}

	/** Helper method that does all the calculations */
	private void generateReceipt() {
		Iterator<Product> cartIterator = cart.iterator();

		while (cartIterator.hasNext()) {
			Product product = cartIterator.next();
			int quantity = product.getQuantity();
			String name = product.getName();
			boolean isImported = product.isImported();
			double totalBaseCostForThisProduct = product.getCost();
			double totalSalesTaxForThisProduct = product.getSalesTax();
			double totalCostForThisProduct = totalBaseCostForThisProduct
					+ totalSalesTaxForThisProduct;

			grandSalesTaxesTotal += totalSalesTaxForThisProduct;
			grandBaseCostTotal += totalBaseCostForThisProduct;

			receiptItemsList.add(new ReceiptItems(quantity, name, totalSalesTaxForThisProduct,
					totalCostForThisProduct, isImported));
		}

		grandOverallTotal = grandBaseCostTotal + grandSalesTaxesTotal;
	}

	/**
	 * Method used by any printer to print the receipt and email to customer.
	 * 
	 * Putting sysout instead of logging.
	 * 
	 * Email id can be updated in Email.properties
	 * 
	 * */
	
	public void printReceipt() {
		StringBuffer emailContent = new StringBuffer();
		String todaysDate = new Date().toString();
		
		System.out.println("---------------------------- MyShopping Cart Receipt -----------------------------");
		emailContent.append("---------------------------- MyShopping Cart Receipt -----------------------------");
		emailContent.append('\n');
		emailContent.append('\n');

		for (ReceiptItems item : receiptItemsList) {
			System.out.print(item.quantity);
			System.out.println(item.name + " : " + item.totalCost);
			emailContent.append(item.name + " : " + item.totalCost);
			emailContent.append('\n');
		}

		System.out.println("\nSales Taxes:\t"
				+ new BigDecimal(Double.toString(grandSalesTaxesTotal)).setScale(2,
						BigDecimal.ROUND_HALF_EVEN));
		emailContent.append("\nSales Taxes:\t"
				+ new BigDecimal(Double.toString(grandSalesTaxesTotal)).setScale(2,
						BigDecimal.ROUND_HALF_EVEN));
		emailContent.append('\n');
	
		emailContent.append("---------------------------- Total Payment Due -----------------------------");
		emailContent.append('\n');
		System.out.println("Total:\t"
				+ new BigDecimal(Double.toString(grandOverallTotal)).setScale(2,
						BigDecimal.ROUND_HALF_EVEN));
		emailContent.append("Total:\t"
				+ new BigDecimal(Double.toString(grandOverallTotal)).setScale(2,
						BigDecimal.ROUND_HALF_EVEN));
		
		System.out.println("\nPrinted on: " + todaysDate);
		emailContent.append("\nPrinted on: " + todaysDate);
		emailContent.append('\n');
		emailContent.append('\n');
		System.out.println("--------------------------Thank You-----------------------------");
		emailContent.append("--------------------------Thank You-----------------------------");
		Mail.messenger(emailContent.toString());
		cart.emptyCart();
	}
}
