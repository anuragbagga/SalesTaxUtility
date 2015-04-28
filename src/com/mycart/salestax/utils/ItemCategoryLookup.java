package com.mycart.salestax.utils;

import java.util.HashMap;

import com.mycart.salestax.constants.Constants;
 
public class ItemCategoryLookup {

	private static ItemCategoryLookup instance;
	private static HashMap<String, String> itemCategoriesMap;

	public static ItemCategoryLookup getInstance() {
		if (instance == null) {
			instance = new ItemCategoryLookup();
		}
		return instance;
	}
	
	/**
	 * 
	 * HashMap for item 
	 * 
	 */
	
	private ItemCategoryLookup() {
		itemCategoriesMap = new HashMap<String, String>();
		itemCategoriesMap.put("book", Constants.BOOK_PRODUCT);
		itemCategoriesMap.put("books", Constants.BOOK_PRODUCT);
		itemCategoriesMap.put("chocolate", Constants.FOOD_PRODUCT);
		itemCategoriesMap.put("chocolates", Constants.FOOD_PRODUCT);
		itemCategoriesMap.put("music", Constants.OTHER_ITEMS);
		itemCategoriesMap.put("perfume", Constants.OTHER_ITEMS);
		itemCategoriesMap.put("pills", Constants.MEDICAL_PRODUCT);
	}

	/**
	 * Add new product and it's category to current database
	 * 
	 * @param item
	 *            Item: keyword
	 * @param category
	 *            : Category keyword
	 * */
	public void addProduct(String item, String category) {
		itemCategoriesMap.put(item, category);
	}

	/**
	 * Remove product from the table, so that next time it is look up, it should
	 * default to "Other" category
	 * 
	 * @param productName
	 *            : Product to be removed from lookup table
	 * */
	public void removeProduct(String productName) {
		if (itemCategoriesMap.containsKey(productName))
			itemCategoriesMap.remove(productName);
	}

	/**
	 * Get the category of product with given keyword(s)
	 * 
	 * @param productDescription
	 *            keyword(s) with description of product
	 * @return category of the product if found in the lookup table, otherwise
	 *         the default value
	 * */
	
	public String getCategoryFor(String productDescription) {
		String[] productKeyWords = productDescription.split(" ");
		String category = Constants.OTHER_ITEMS;
		for (int keyWordIndex = 0; keyWordIndex < productKeyWords.length; keyWordIndex++) {
			if (itemCategoriesMap.containsKey(productKeyWords[keyWordIndex])) {
				category = itemCategoriesMap.get(productKeyWords[keyWordIndex]);
			}
		}
		return category;
	}

}
