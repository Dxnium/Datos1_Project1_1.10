package com.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMS {

	// Find your Account Sid and Token at twilio.com/user/account
	  public static final String ACCOUNT_SID = System.getenv("Twilio_SID");
	  public static final String AUTH_TOKEN = System.getenv("Twilio_Token");;
	  //public String MensajeSMS;
	  //public String NumeroDestino;

	  public void EnvioSMS(String MensajeSMS, String NumeroDestino) {
	    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	    Message message = Message.creator(new PhoneNumber(NumeroDestino),
	        new PhoneNumber("+15615294003"), 
	        MensajeSMS).create();

	    System.out.println(message.getSid());
	  }
}