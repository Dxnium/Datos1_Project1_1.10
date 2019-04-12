package JSON;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import game.logic.GameFlow;

// TODO: Auto-generated Javadoc
/**
 * The Class De.
 */
public class De {
	
	/** The json data. */
	JSONObject jsonData;
	
	/** The dato. */
	public String dato;
	
	/**
	 * Instantiates a new de.
	 *
	 * @param out the out
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public De(Writer out) throws IOException {
		jsonData =  new JSONObject();
		jsonData.put("Datos", out);

		JSONParser data_parser = new JSONParser();

		try {
			JSONObject objDatos = (JSONObject) data_parser.parse(jsonData.toJSONString());
			JSONArray arrayDatos = (JSONArray) objDatos.get("Datos");

			for (int i =0; i<arrayDatos.size(); i++) {
				
				JSONObject juego = (JSONObject) arrayDatos.get(i);
				if(juego.containsKey("Matriz")) {
					String matriz = juego.get("Matriz").toString();
					System.out.println(matriz + "\t");
					this.dato=matriz;
					
					}
					
				}
				

			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	
	
}
 

}
