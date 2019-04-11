package JSON;

import java.io.Writer;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import game.logic.GameFlow;

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
					if(matriz.contains("vacio")) {
						System.out.println("vacio");
					}else {
					String[] datos  = matriz.split(",");
					System.out.println(Arrays.deepToString(datos));
					int command=Integer.parseInt(datos[0]);
					switch(command) {
					case 1:
						GameFlow gameFlow = new GameFlow();
						GameFlow.getGame().setMaxPlayers(Integer.parseInt(datos[1]));	
						GameFlow.getGame().setCurrentConection();
						GameFlow.getGame().InitializeDeck();
						GameFlow.getGame().initializeTableTop();
						GameFlow.getGame().getDictionary().generateDictionaryBook();
					}
					}
					
				}
				

			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	
	
}
 

}



