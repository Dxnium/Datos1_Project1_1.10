package com.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

// TODO: Auto-generated Javadoc
/**
 * The Class SMS.
 * Esta clase envia un SMS a un número telefonico
 * mediante el API de Twilio, para lo cual se debio instalar
 * un Jar con las librerias correspondientes.
 */
public class SMS {

	/** The Constant ACCOUNT_SID. 
	 * Esta variable almacena el ID para ingresar a Twilio
	 * */
	
	// Find your Account Sid and Token at twilio.com/user/account
	  public static final String ACCOUNT_SID = System.getenv("Twilio_SID");
	  
  	/** The Constant AUTH_TOKEN. 
  	 * Esta variable almacena el Token para la autenticación en Twilio.
  	 * */
  	public static final String AUTH_TOKEN = System.getenv("Twilio_Token");;
	  //public String MensajeSMS;
	  //public String NumeroDestino;

	  /**
  	 * Envio SMS.
  	 *
  	 * @param MensajeSMS the mensaje SMS
  	 * @param NumeroDestino the numero destino
  	 * 
  	 * Este método se encarga se hacer el envio del SMS. Recibi como parametros dos String,
  	 * uno con el Mensaje deseado y otro con el Numero de Telefono al que se desea enviar el SMS.
  	 * 
  	 */
  	public void EnvioSMS(String MensajeSMS, String NumeroDestino) {
	    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	    Message message = Message.creator(new PhoneNumber(NumeroDestino),
	        new PhoneNumber("+15615294003"), 
	        MensajeSMS).create();

	    System.out.println(message.getSid());
	  }
}