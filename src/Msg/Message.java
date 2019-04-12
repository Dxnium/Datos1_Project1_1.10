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


// TODO: Auto-generated Javadoc
/**
 * The Class Message.
 */
public class Message extends Observable implements JSONStreamAware {
	
	/** The matriz. */
	public String matriz;
	
	/** The matriz 1. */
	public String[][] matriz1;
	
	/** The matriz json. */
	JSONArray matrizJson;
	
	/**
	 * Gets the matriz 1.
	 *
	 * @return the matriz 1
	 */
	public String[][] getMatriz1() {
		
		return matriz1;
	}

	/**
	 * Sets the matriz 1.
	 *
	 * @param matriz1 the new matriz 1
	 */
	@SuppressWarnings("unchecked")
	public void setMatriz1(String[] matriz1) {
		JSONArray list = new JSONArray();
		for(String i : matriz1) {
				list.add(i);
			}
		this.matrizJson = list;
	}
	
	/**
	 * Sets the matrizdoble.
	 *
	 * @param matriz1 the new matrizdoble
	 */
	public void setMatrizdoble(String[][] matriz1) {
		JSONArray list = new JSONArray();
		for(String[] i : matriz1) {
			for(String j : i) {
				list.add(i);
			}
		this.matrizJson = list;
	}
	}

	/**
	 * Instantiates a new message.
	 *
	 * @param matriz the matriz
	 */
	public Message (String matriz) {
		this.matriz = matriz;
	}

	/**
	 * Gets the matriz.
	 *
	 * @return the matriz
	 */
	public String getMatriz() {
		return matriz;
	}

	/**
	 * Sets the matriz.
	 *
	 * @param message the new matriz
	 */
	public void setMatriz(String message) {
		this.matriz = message;
		//Indica que el mensaje ha cambiado 
		this.setChanged();
		//notifica a los observadores que el mesanje ha cambiado 
		this.notifyObservers(this.getMatriz());
	}

	/* (non-Javadoc)
	 * @see org.json.simple.JSONStreamAware#writeJSONString(java.io.Writer)
	 */
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
