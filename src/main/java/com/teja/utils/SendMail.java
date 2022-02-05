package com.teja.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class SendMail {

	public Boolean sendMailMethod() {
		 // Recipient's email ID needs to be mentioned.
	      String to = "yerraguntlateja4@gmail.com";

	      // Sender's email ID needs to be mentioned
	      String from = "springproject8352@gmail.com";

	      // Assuming you are sending email from localhost
	     

	      // Get system properties
	      Properties properties = System.getProperties();

	      
	      String email="yerraguntlateja4@gmail.com";
	      String password="Teja@8352";
	      
	      properties.put("mail.smtp.host","smtp.gmail.com");
	      
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.port", "25");
	      properties.put("mail.smtp.ssl.enable", "true");
	      properties.put("mail.smtp.socketFactory.fallback","true");
	      properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties,new Authenticator() {
	    	  protected PasswordAuthentication getPasswordAuthentication() {
	    		  return new PasswordAuthentication(from,password);
	    	  }
	      });
	      session.setDebug(true);

	      try {
	    	  Transport transport=session.getTransport();
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(from);

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("This is the Subject Line!");

	         // Now set the actual message
	         message.setText("This is actual message");

	         // Send message
	         transport.connect();
	         transport.send(message);
	         System.out.println("Sent message successfully....");
	         return true;
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	         return false;
	      }

	}
	
}
