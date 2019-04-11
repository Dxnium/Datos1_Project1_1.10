package JSON;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import game.logic.GameFlow;

public class Decode {
	public int currentConnection;
	public int maxPlayers;
	public int command; 
	public int getCurrentConnection() {
		return currentConnection;
	}
	public void setCurrentConnection(int currentConnection) {
		this.currentConnection = currentConnection;
	}
	public int getMaxPlayers() {
		return maxPlayers;
	}
	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
	
	JSONObject jsonData;
	public Decode(Writer out) throws IOException {
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
					this.command=Integer.parseInt(datos[0]);
					switch(command) {
					case 1:
						GameFlow gameFlow = new GameFlow();
						GameFlow.getGame().setMaxPlayers(Integer.parseInt(datos[1]));	
						GameFlow.getGame().setCurrentConection();
						GameFlow.getGame().InitializeDeck();
						GameFlow.getGame().initializeTableTop();
						GameFlow.getGame().getDictionary().generateDictionaryBook();
						break;
					case 2:
						System.out.println(Arrays.deepToString(datos));
						GameFlow.playerCreation(datos[1]);
						int currentConnection = GameFlow.getGame().getCurrentConection();
						setCurrentConnection(currentConnection);
						int maxPlayers = GameFlow.getGame().getMaxPlayers();
						setMaxPlayers(maxPlayers);
						break;	
					case 3:
						break;
						
					}
					
					}
					
				}
				

			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	
	
}
	public int getCommand() {
		return command;
	}
	public void setCommand(int command) {
		this.command = command;
	}
 

}



