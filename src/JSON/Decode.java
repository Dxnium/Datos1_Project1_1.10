package JSON;

import java.io.Writer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Decode {
	public String[] datos;
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
				if(juego.containsKey("mensaje")) {
					String matriz = juego.get("mensaje").toString();
					System.out.println(matriz + "\t");
					this.datos = matriz.split(",");
				}
				if(juego.containsKey("MatrizJson")) {
					String Jsonmatriz = juego.get("MatrizJson").toString();
					System.out.println(Jsonmatriz + "\t");
				}
				

			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	
	
}
 

}



