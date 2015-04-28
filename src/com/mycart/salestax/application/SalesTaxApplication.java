package com.mycart.salestax.application;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

import com.mycart.salestax.bean.GeoResponse;
import com.mycart.salestax.constants.Constants;
import com.mycart.salestax.constants.Result;
import com.mycart.salestax.exception.InvalidInputException;
import com.mycart.salestax.utils.AddressConverter;
import com.mycart.salestax.utils.Customer;
import com.mycart.salestax.utils.EmailConfiguration;
import com.mycart.salestax.utils.Printer;
import com.mycart.salestax.utils.ReceiptDetails;
import com.mycart.salestax.utils.TweetUtil;


public class SalesTaxApplication {

	public static void main(String[] args) {
		Customer customer = null;
		ReceiptDetails receipt = null;
		String inputFile = null;
		String customerName = EmailConfiguration.getProperty("customer.name");

		//Checking valid Input file 
		if(args.length == 0){
			System.err.println("No input file specified. Please specify input file path as command line argument");
			System.exit(0);
		} else {
			inputFile = args[0];
			String ext = FilenameUtils.getExtension(inputFile);	
			// Checking valid input file format
			if(!(ext.equals(Constants.FILE_EXTN))){
				System.out.println("Only txt file accepted. You provided "+ ext);
				System.exit(0);
			}
		}

		try {
			customer = new Customer(inputFile);
			receipt = customer.calculateSalesTax(customerName);
			new Printer(receipt).print();
			
			GeoResponse res = new AddressConverter().convertToLatLong(EmailConfiguration.getProperty("store.zip"));
			if(res.getStatus().equals("OK"))
			{
				for(Result result : res.getResults())
				{
					System.out.println("Lattitude of address is :"  +result.getGeometry().getLocation().getLat());
					System.out.println("Longitude of address is :" + result.getGeometry().getLocation().getLng());
					System.out.println("Location is " + result.getGeometry().getLocation_type());
	
					//Tweeting from location
					double latitude =  Double.parseDouble(result.getGeometry().getLocation().getLat());
					double longitude =  Double.parseDouble(result.getGeometry().getLocation().getLng());
					TweetUtil.tweetUserStatus(latitude, longitude,receipt.getTotal());
				}
			}
			else
			{
				System.out.println(res.getStatus());
			}
			
		} catch (InvalidInputException invalidInputException) {
			System.out.println("Please check your input file for correct format");
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println("The file you specified, doesn't exist!");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	}
}
