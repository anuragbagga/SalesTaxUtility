package com.mycart.salestax.utils;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.codehaus.jackson.map.ObjectMapper;

import com.mycart.salestax.bean.GeoResponse;
import com.mycart.salestax.constants.Constants;
import com.mycart.salestax.constants.Result;


public class AddressConverter {

	private static final String URL = Constants.GEO_LOOKUP_URL;

	public GeoResponse convertToLatLong(String fullAddress) throws IOException {
		URL url = new URL(URL + "?address="
				+ URLEncoder.encode(fullAddress, "UTF-8") + "&sensor=false");
		// Open the Connection
		URLConnection conn = url.openConnection();

		InputStream in = conn.getInputStream() ;
		ObjectMapper mapper = new ObjectMapper();
		GeoResponse response = (GeoResponse)mapper.readValue(in,GeoResponse.class);
		in.close();
		return response;
	}

//	public GeoResponse convertFromLatLong(String latlongString) throws IOException {
//		URL url = new URL(URL + "?latlng="
//				+ URLEncoder.encode(latlongString, "UTF-8") + "&sensor=false");
//		// Open the Connection
//		URLConnection conn = url.openConnection();
//
//		InputStream in = conn.getInputStream() ;
//		ObjectMapper mapper = new ObjectMapper();
//		GeoResponse response = (GeoResponse)mapper.readValue(in,GeoResponse.class);
//		in.close();
//		return response;
//
//	}


	public static void main(String[] args) throws IOException {

		GeoResponse res = new AddressConverter().convertToLatLong("");
		if(res.getStatus().equals("OK"))
		{
			for(Result result : res.getResults())
			{
				System.out.println("Lattitude of address is :"  +result.getGeometry().getLocation().getLat());
				System.out.println("Longitude of address is :" + result.getGeometry().getLocation().getLng());
				System.out.println("Location is " + result.getGeometry().getLocation_type());
			}
		}
		else
		{
			System.out.println(res.getStatus());
		}

		System.out.println("\n");
//		GeoResponse res1 = new AddressConverter().convertFromLatLong("18.92038860,72.83013059999999");
//		if(res1.getStatus().equals("OK"))
//		{
//			for(Result result : res1.getResults())
//			{
//				System.out.println("address is :"  +result.getFormatted_address());
//			}
//		}
//		else
//		{
//			System.out.println(res1.getStatus());
//		}
	}


}
