package com.mycart.salestax.interfaces;

import java.util.Iterator;

import com.mycart.salestax.itemList.Product;


public interface ShoppingCartI {

	Iterator iterator();

	void addProduct(Product product);
	
	void getProduct(Product product);
	
	void removeProduct(Product cancelledProduct);

	int getItemCount();

	void emptyCart();

}
