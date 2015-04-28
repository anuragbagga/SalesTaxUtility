package com.mycart.salestax.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;

import com.mycart.salestax.utils.Customer;
import com.mycart.salestax.exception.InvalidInputException;
import com.mycart.salestax.utils.ReceiptDetails;



public class CustomerInputTest {

	/* 
	 * Junit to check Input1
	 * Input 1:
	 *	1 book at 12.49
	 *	1 music CD at 14.99
	 * 	1 chocolate bar at 0.85
	 * 
	 * Output 1:
	 * 1 book : 12.49
	 * 1 music CD: 16.49
	 * 1 chocolate bar: 0.85
	 * Sales Taxes: 1.50
	 * Total: 29.83
	 */

	@Test
	public void testInput1() throws InvalidInputException, NumberFormatException, IOException {
		Customer newCustomer = new Customer("InputFile/inputfile1.txt");
		ReceiptDetails receipt = newCustomer.calculateSalesTax("CustomerA");
		BigDecimal total = receipt.getTotal();
		BigDecimal myTotal = new BigDecimal("29.83"); // Matched
		assertTrue(total.equals(myTotal));
	}

	/* 
	 * Junit to check InputFile 2
	 *  1 imported box of chocolates at 10.00
	 *  2 imported bottles of perfume at 47.25
	 * 
	 * Output 2:
	 * 1 Imported box of chocolates: 10.50
	 * 2 Imported bottles of perfume: 108.70
	 * Sales Taxes: 14.70
	 * Total: 119.20
	 * 
	 */

	@Test
	public void testInput2() throws InvalidInputException, NumberFormatException, IOException {
		Customer newCustomer = new Customer("InputFile/inputfile2.txt");
		ReceiptDetails receipt = newCustomer.calculateSalesTax("CustomerB");
		BigDecimal total = receipt.getTotal();
		BigDecimal myTotal = new BigDecimal("119.20"); 
		assertTrue(total.equals(myTotal));
	}

	/* 
	 * Junit to check InputFile 3
	 *  Input 3:
	 * 1 imported bottle of perfume at 27.99
	 * 1 bottle of perfume at 18.99
	 * 1 packet of headache pills at 9.75
	 * 1 box of imported chocolates at 11.25
	 * 
	 * Output 3:
	 * 1 imported bottle of perfume: 32.19
	 * 1 bottle of perfume: 20.89
	 * 1 packet of headache pills: 9.75
	 * 1 imported box of chocolates: 11.85
	 * Sales Taxes: 6.70
	 * Total: 74.68
	 * 
	 */

	@Test
	public void testInput3() throws InvalidInputException, NumberFormatException, IOException {
		Customer newCustomer = new Customer("InputFile/inputfile3.txt");
		ReceiptDetails receipt = newCustomer.calculateSalesTax("CustomerC");
		BigDecimal total = receipt.getTotal();
		BigDecimal myTotal = new BigDecimal("74.68");
		assertTrue(total.equals(myTotal));
	}


	/* 
	 * Junit to check InputFile 4
	 *  
	 *  Invalid file name given throws FileNotFoundException
	 * 
	 **/

	@Test
	public void testInvalidFile() throws FileNotFoundException {
		String invalidFilePath = "InputFile/temp.txt";
		boolean thrown = false;
		Customer newCustomer = new Customer(invalidFilePath);
		try {
			newCustomer.calculateSalesTax("CustomerD");
		} catch (FileNotFoundException notFoundException) {
			// FileNotFoundException caught here !!
			thrown = true;
		} catch (Exception e){
			thrown = false;
		}
		assertTrue(thrown);
	}

	@Test
	public void testInvalidInputData() throws InvalidInputException {
		Customer newCustomer = new Customer("InputFile/inputfile4.txt");
		try {
			newCustomer.calculateSalesTax("CustomerE");
		} catch (Exception invalidInputException) {
			System.out.println(invalidInputException.getMessage());
			assertEquals(invalidInputException.getMessage(), "Invalid file format");

		}
	}

}