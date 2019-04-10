package Msg;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Observable;

import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;


public class Message extends Observable implements JSONStreamAware {
	public String matriz;
	
	public Message (String matriz) {
		this.matriz = matriz;
	}

	public String getMatriz() {
		return matriz;
	}

	public void setMatriz(String message) {
		this.matriz = message;
		//Indica que el mensaje ha cambiado 
		this.setChanged();
		//notifica a los observadores que el mesanje ha cambiado 
		this.notifyObservers(this.getMatriz());
	}

	@Override
	public void writeJSONString(Writer out) throws IOException {
		LinkedHashMap<String, String> obj = new LinkedHashMap<String, String>();
		obj.put("Matriz", matriz);
		JSONValue.writeJSONString(obj, out);
		
	}
	
	
	

}
