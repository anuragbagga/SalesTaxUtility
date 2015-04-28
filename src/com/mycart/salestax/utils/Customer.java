/**
 * This program will take items from file, compute total bill 
 *  generates the receipt and email to customer.
 * 
 * */

package com.mycart.salestax.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.mycart.salestax.bean.ShoppingCart;
import com.mycart.salestax.bean.InputData;
import com.mycart.salestax.exception.InvalidInputException;
import com.mycart.salestax.interfaces.ShoppingCartI;
import com.mycart.salestax.itemList.Product;
import com.mycart.salestax.itemList.ProductFactory;

public class Customer {
	ItemCategoryLookup lookup;
	ProductFactory productFactory;
	ShoppingCartI shoppingCart;
	ArrayList<InputData> inputData;
	ReceiptDetails receipt;

	private String inputFilePath;

	public Customer(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}

	
	public ReceiptDetails calculateSalesTax(String customerName)
			throws InvalidInputException, NumberFormatException, IOException {
		// read and prepare input data
		lookup = ItemCategoryLookup.getInstance();

		inputData = new FileParser(inputFilePath).getInputData();
		productFactory = ProductFactory.getInstance();
		shoppingCart = new ShoppingCart(customerName, new Date().toString());

		for (InputData data : inputData) {
			int quantity = data.getQuantity();
			String productDescription = data.getProductDescription();
			double cost = data.getCost();
			boolean isImported = data.isImported();
			String category = lookup.getCategoryFor(productDescription);

			Product product = productFactory.createProduct(category, productDescription, cost,
					quantity, isImported);
			shoppingCart.addProduct(product);
		}

		receipt = new ReceiptDetails(shoppingCart);
		return receipt;
	}

}
