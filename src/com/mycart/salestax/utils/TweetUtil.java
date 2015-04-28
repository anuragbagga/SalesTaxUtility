package com.mycart.salestax.utils;


import java.math.BigDecimal;

import twitter4j.GeoLocation;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TweetUtil {

	public static void tweetUserStatus(double latitude, double longitude, BigDecimal cost) throws Exception {
		try {
			Twitter twitter = new TwitterFactory().getInstance();
			GeoLocation loc = new GeoLocation(latitude,longitude);
			twitter.setOAuthConsumer(EmailConfiguration.getProperty("consumerKeyStr"), EmailConfiguration.getProperty("consumerSecretStr"));
			AccessToken accessToken = new AccessToken(EmailConfiguration.getProperty("accessTokenStr"),
					EmailConfiguration.getProperty("accessTokenSecretStr"));
			twitter.setOAuthAccessToken(accessToken);
			StatusUpdate statusUpdate = new StatusUpdate("Hi! i just purchased goodies from MyCart worth of $"+cost+".Love the shopping experience. #Awesome").location(loc);			
			Status status = twitter.updateStatus(statusUpdate);
			System.out.println(status.getGeoLocation());
			System.out.println("Successfully updated the status in Twitter.");
			System.out.println("Check https://twitter.com/anurag_sandbox");
		} catch (TwitterException te) {
			if(te.getErrorCode()==187){
				System.out.println("Tweet is duplicate. Cannot repost same status");
			}
			else {
				te.printStackTrace();
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}


