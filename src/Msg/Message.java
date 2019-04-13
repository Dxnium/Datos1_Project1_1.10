package Msg;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Observable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;


// TODO: Auto-generated Javadoc
/**
 * The Class Message save all messages that are going to be send by the server and client.
 * .
 */
public class Message extends Observable implements JSONStreamAware {
	
	/** The matriz with the command and single messages. */
	public String matriz;
	
	/** The matriz1 matriz of array with the position of the titles. */
	public String[][] matriz1;
	
	/** matriz with positions of titles . */
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
	 * Sets the Matriz of Jsonarrays to message.
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
	 * Sets the matrizdoble matriz with jsonobject.
	 *
	 * @param matriz1 the new matrizdoble
	 */
	@SuppressWarnings("unchecked")
	public void setMatrizdoble(String[] matriz1) {
		JSONObject obj = new JSONObject();
		System.out.println("MatrizDOble");
		JSONArray list = new JSONArray();
		for(String i : matriz1) {
			obj.put("data", i);
			list.add(obj);
			}
		this.matrizJson = list;
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
	 * Creates a the JSONObject that takes 
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
