package JSON;

import java.io.Writer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Decode {
	JSONObject jsonData;
	public Decode(Writer out) {
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
				}
				

			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	
	
}
 

}



