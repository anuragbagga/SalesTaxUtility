package com.mycart.salestax.bean;

import java.util.ArrayList;
import java.util.Iterator;

import com.mycart.salestax.interfaces.ShoppingCartI;
import com.mycart.salestax.itemList.Product;


public class ShoppingCart implements ShoppingCartI {

	private String customerName;
	private String date;
	private ArrayList<Product> cart;
	private static Iterator<Product> iterator;

	public ShoppingCart(String customerName, String date) {
		this.customerName = customerName;
		this.date = date;
		cart = new ArrayList<Product>();
	}

	@Override
	public void addProduct(Product product) {
		cart.add(product);
	}

	@Override
	public int getItemCount() {
		return cart.size();
	}

	@Override
	public Iterator iterator() {
		return cart.iterator();
	}

	@Override
	public void getProduct(Product product) {
		cart.remove(product);
	}

	@Override
	public void emptyCart() {
		cart.clear();
	}

	@Override
	public void removeProduct(Product cancelledProduct) {
		cart.remove(cancelledProduct);
	}

}
