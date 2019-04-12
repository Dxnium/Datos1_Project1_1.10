package Msg;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Observable;

import org.json.simple.JSONArray;
import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;


public class Message extends Observable implements JSONStreamAware {
	public String matriz;
	public String[][] matriz1;
	JSONArray matrizJson;
	
	public String[][] getMatriz1() {
		
		return matriz1;
	}

	@SuppressWarnings("unchecked")
	public void setMatriz1(String[] matriz1) {
		JSONArray list = new JSONArray();
		for(String i : matriz1) {
				list.add(i);
			}
		this.matrizJson = list;
	}
	public void setMatrizdoble(String[][] matriz1) {
		JSONArray list = new JSONArray();
		for(String[] i : matriz1) {
			for(String j : i) {
				list.add(i);
			}
		this.matrizJson = list;
	}
	}

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
		if(matriz!=null) {
			obj.put("mensaje", matriz);
			}
		if(matrizJson!=null) {
		obj.put("MatrizJson", matrizJson.toJSONString());
		}
		JSONValue.writeJSONString(obj, out);

		
	}
	
	
	

}
