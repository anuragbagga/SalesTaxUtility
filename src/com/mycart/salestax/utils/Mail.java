package com.mycart.salestax.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	public static void messenger(String str){
		try {
			String host = "";
			final String sender = EmailConfiguration.getProperty("FROM_MAIL_ID");
			String recipient = EmailConfiguration.getProperty("TO_MAIL_ID");
			
			// Password current stored as plaintext. Should be in encrypted format
			final String password = EmailConfiguration.getProperty("mailpassword");

			String subject = "******* Your Email Receipt *******";
			String msgText = str;
			boolean sessionDebug = false;
			// Create some properties and get the default Session.
			 Properties props = new Properties();
			 
		        props.put("mail.smtp.host", EmailConfiguration.getProperty("host")); //SMTP Host
		        props.put("mail.smtp.socketFactory.port", EmailConfiguration.getProperty("port")); //SMTP Host
		           props.put("mail.smtp.socketFactory.class",EmailConfiguration.getProperty("socketFactory.class")); //SSL Factory Class
		        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		        props.put("mail.smtp.port",  EmailConfiguration.getProperty("port")); //SMTP Port
		         
		        Authenticator auth = new Authenticator() {
		            //override the getPasswordAuthentication method
		            protected PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication(sender, password);
		            }
		        };
		        
			Session session = Session.getDefaultInstance(props, auth);
			
			// Set debug on the Session so we can see what is going on
			// Passing false will not echo debug info, and passing true
			// will.
			session.setDebug(sessionDebug);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(sender));
			InternetAddress[] address = {new InternetAddress(recipient)};
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			//msg.setFileName(file1);
			msg.setText(msgText);
			Transport.send(msg);
			System.out.println("Email sent to "+ recipient);
			System.out.println(" Please check your inbox..");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
}
