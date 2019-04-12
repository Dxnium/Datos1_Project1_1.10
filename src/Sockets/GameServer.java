package Sockets;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

import org.json.simple.JSONArray;

import JSON.Decode;
import JSON.Encode;
import game.logic.GameFlow;
import queue.myQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class GameServer.
 */
public class GameServer implements Runnable   {
	
	/** The clients queue. */
	//atributos
	private myQueue<Socket> clientsQueue = new myQueue<Socket>();
	
	/** The server. */
	ServerSocket server;  
	
	/** The socket. */
	Socket socket; 
	
	/** The game flow. */
	private GameFlow gameFlow;
	
	/** The decode. */
	Decode decode;
	
	/** The port. */
	final int port = 5555;
	
	/** The password. */
	public String password = "none";

	/** The ver password. */
	private boolean verPassword = false;
	
	/** The ip. */
	public InetAddress ip; 
	
	/** The msj datos. */
	private Msg.Message msjDatos = new Msg.Message("vacio");
	
	/** The verfication. */
	private boolean verfication = true; 
	
	/** The matriz letras. */
	public String[][] matrizLetras; 
	
	/** The str. */
	public String str;
	
	
	 /**
 	 * Instantiates a new game server.
 	 */
 	public GameServer(){
		 
		 System.out.println(">>Se inicia el server");
		    Thread myThread = new Thread(this);
		    myThread.start();
		  }
	 
	 
	 /* (non-Javadoc)
 	 * @see java.lang.Runnable#run()
 	 */
 	@Override 
	public void run() {
		try {
			
			//server created in port 9999
			server = new ServerSocket( port);
			System.out.println("New server started");
			this.ip = InetAddress.getLocalHost();
			System.out.println(this.ip);
			
			while(true) {
			socket = server.accept();//Waiting for a client 
			clientsQueue.enqueue(socket);//agrega el cliente a la cola de clientes 
			System.out.println(">>Client connect");//Client connected 
				while(clientsQueue.hasItems()) {
					Socket cliente = clientsQueue.dequeue();
					//Canales de entrada y salida 
					DataInputStream entradaDatos = new DataInputStream(socket.getInputStream()); 
					DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
					
					
					//Recepcion de mensaje
					String mensajeRecibido = entradaDatos.readUTF();
					
					while(verfication) {
						reponseClient(mensajeRecibido);
						this.verfication = false;
						
					}
					System.out.println(">>Mensaje recibido: "+ mensajeRecibido);
					
					
					//Confirmacion de conexion con mensaje 
					System.out.println(">>Enviando update al cliente");
					System.out.println("hola44");
					salida.writeUTF(GetJMensaje().toString());
					salida.flush();
					this.verfication = true;
					
				
					
					

				}
				
			
			//Cerrando conexion 
//			System.out.println("Cerrando conexión...Server"); 
//			socket.close(); //se cierra la conexion 
			}
			
		} catch (IOException e) {
			System.out.println("Connetion failed");
		} 
	}

/**
 * Reponse client.
 *
 * @param mensajeRecibido the mensaje recibido
 * @throws IOException Signals that an I/O exception has occurred.
 */
private void reponseClient(String mensajeRecibido) throws IOException {
	if(mensajeRecibido.contains("password")) {
		System.out.println("password: "+ this.password);
		setMensaje(this.password);
	}
	//se crea el juego
		if(mensajeRecibido.contains("startgame")) {
			System.out.println(">>iniciando juego");
			gameFlow = new GameFlow();
			System.out.println(mensajeRecibido);
			GameUpdate(mensajeRecibido);
			System.out.println("numero de jugadores: "+ this.decode.datos[1]);
			this.gameFlow.getGame().setMaxPlayers(Integer.parseInt(this.decode.datos[1]));
			this.gameFlow.getGame().InitializeDeck();
			this.gameFlow.getGame().initializeTableTop();
			this.gameFlow.getGame().getDictionary().generateDictionaryBook();
		}
		if(mensajeRecibido.contains("agregarJugador")) {
			GameUpdate(mensajeRecibido);
			System.out.println(">>Agregando jugador: "+ decode.datos[1] );
			gameFlow.playerCreation(decode.datos[1]);
			gameFlow.getGame().setCurrentConection();
		}
		if(mensajeRecibido.contains("currentConection")) {
			GameUpdate(mensajeRecibido);
			int current = GameFlow.getGame().getCurrentConection();
			setMensaje(Integer.toString(current));
		}
		if(mensajeRecibido.contains("playOrder")) {
			if(this.gameFlow.getGame().getCurrentConection() == this.gameFlow.getGame().getMaxPlayers()) {
				this.gameFlow.playOrder();
				this.gameFlow.dealTiles();
				 this.matrizLetras = this.gameFlow.sendTiles();
//				 setMensaje(matrizLetras);
				 
			}
		}
			if(mensajeRecibido.contains("checkTurno")) {
				System.out.println("gameTurno");
				GameUpdate(mensajeRecibido);
				if(gameFlow.getGame().getPlayerList().getHead().getData().getName().toLowerCase().equals(decode.datos[1].toLowerCase())) {
					System.out.println("turno de jugador: "+decode.datos[1]);
					System.out.println(Arrays.deepToString(myMatriz(decode.datos[1])));
					if(myMatriz(decode.datos[1]) != null) {
						setMensaje1(myMatriz(decode.datos[1]));
					}else {
						setMensaje("");
					}
					}
				}if(mensajeRecibido.contains("posicionLetras")) {
					GameUpdate(mensajeRecibido);
					String data = "";
					for(int i = 1; i < 25 ;i++) {
						data += decode.datos[i]+",";
					}data = data.substring(0, data.length());
					System.out.println(data);
				}
		}
	
	/**
	 * My matriz.
	 *
	 * @param name the name
	 * @return the string[]
	 */
	public String[] myMatriz(String name) {
		for(String[]i:this.matrizLetras) {
			System.out.println(Arrays.deepToString(i));
			if(i[0].toLowerCase().equals(name)) {
				return i;
			}
		}
		return this.matrizLetras[0];
	}



/**
 * Verificar password.
 *
 * @param mensajeRecibido the mensaje recibido
 * @return true, if successful
 */
//Verifica el password para establecer la conexion 
	private boolean verificarPassword(String mensajeRecibido) {
		if(mensajeRecibido.toLowerCase().contains((this.password))) {
			System.out.println(">>password correcto"+mensajeRecibido.toLowerCase());
			
			return true;
		}
		System.out.println(">>password incorrecto");
		return false;
		
	}
	
	

	/**
	 * Gets the J mensaje.
	 *
	 * @return the writer
	 */
	private Writer GetJMensaje() {
		Encode datos = new Encode();
		//Crea el arreglo con los datos de la Clase 
		JSONArray arr = datos.arrayData(msjDatos);
		Writer out = new StringWriter();//crear un variable de tipo Writer para almacenar el array y poder mostarlo en pantalla 
		try {
			arr.writeJSONString(out); //guardar el JSONArray en un string 
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return out;
	}
	
	/**
	 * Sets the mensaje.
	 *
	 * @param newMsj the new mensaje
	 */
	public void setMensaje(String newMsj) {
		this.msjDatos.setMatriz(newMsj);
		
	}
	
	/**
	 * Sets the mensaje 1.
	 *
	 * @param matriz1 the new mensaje 1
	 */
	public void setMensaje1(String[] matriz1) {
		this.msjDatos.setMatriz("");
		this.msjDatos.setMatriz1(matriz1);
		
	}
	
	
	

	/**
	 * Game update.
	 *
	 * @param msg the msg
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void GameUpdate(String msg) throws IOException {
		StringWriter toJson = new StringWriter();
		toJson = toJson.append(msg, 0, msg.length());
		System.out.println(toJson.toString());
		 this.decode = new Decode(toJson);
		
		
	}

}

