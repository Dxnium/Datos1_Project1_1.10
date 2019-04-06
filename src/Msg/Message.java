package Msg;

import java.util.Observable;


public class Message extends Observable {
	public String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		//Indica que el mensaje ha cambiado 
		this.setChanged();
		//notifica a los observadores que el mesanje ha cambiado 
		this.notifyObservers(this.getMessage());
	}
	
	
	

}
