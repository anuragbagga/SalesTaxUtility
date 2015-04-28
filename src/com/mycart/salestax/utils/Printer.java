package com.mycart.salestax.utils;

public class Printer {

	ReceiptDetails receipt;

	public Printer(ReceiptDetails receipt) {
		this.receipt = receipt;
	}

	public void print() {
		this.receipt.printReceipt();
	}
}
