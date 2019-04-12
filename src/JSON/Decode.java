package JSON;

import java.io.Writer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// TODO: Auto-generated Javadoc
/**
 * The Class Decode read JSON code.
 */
public class Decode {
	
	/** The datos. */
	public String[] datos;
	
	/** The titles. */
	public String titles;
	
	/** The json data. */
	JSONObject jsonData;
	
	/**
	 * Instantiates a new decode to read a JSON message .
	 *
	 * @param msj in json to be decode
	 */
	public Decode(Writer out) {
		jsonData =  new JSONObject();
		jsonData.put("Datos", out);

		JSONParser data_parser = new JSONParser();

		try {
			JSONObject objDatos = (JSONObject) data_parser.parse(jsonData.toJSONString());
			JSONArray arrayDatos = (JSONArray) objDatos.get("Datos");

			for (int i =0; i<arrayDatos.size(); i++) {
				JSONObject juego = (JSONObject) arrayDatos.get(i);
				if(juego.containsKey("mensaje")) {
					String matriz = juego.get("mensaje").toString();
					System.out.println(matriz + "\t");
					this.datos = matriz.split(",");
				}
				if(juego.containsKey("MatrizJson")) {
					String Jsonmatriz = juego.get("MatrizJson").toString();
					System.out.println(">>MatrizJson: "+Jsonmatriz + "\t");
					this.titles = Jsonmatriz;
					System.out.println(Jsonmatriz.split(","));
				}
				

			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	
	
}
 

}



