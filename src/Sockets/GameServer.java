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

import org.json.simple.JSONArray;

import JSON.Decode;
import JSON.Encode;
import queue.myQueue;

public class GameServer implements Runnable   {
	//atributos
	private myQueue<Socket> clientsQueue = new myQueue<Socket>();
	ServerSocket server;  
	Socket socket; 

	final int port = 5555;
	public String password;

	private boolean verPassword = false;
	public InetAddress ip; 
	private Msg.Message msjDatos = new Msg.Message("vacio");
	
	
	
	 public GameServer(){
		 
		 System.out.println(">>Se inicia el server");
		    Thread myThread = new Thread(this);
		    myThread.start();
		  }
	 
	 
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
					System.out.println(">>Mensaje recibido: "+ mensajeRecibido);
					
					
					//Confirmacion de conexion con mensaje 
					System.out.println(">>Enviando update al cliente");
					System.out.println("hola44");
					salida.writeUTF(GetJMensaje().toString());
					salida.flush();
					
				
					
					
//					GameUpdate(mensajeRecibido);
//					if(!verPassword) {
//						verPassword = verificarPassword(mensajeRecibido);
//					}

				}
				
			
			//Cerrando conexion 
//			System.out.println("Cerrando conexión...Server"); 
//			socket.close(); //se cierra la conexion 
			}
			
		} catch (IOException e) {
			System.out.println("Connetion failed");
		}
	}
//Verifica el password para establecer la conexion 
	private boolean verificarPassword(String mensajeRecibido) {
		if(mensajeRecibido.toLowerCase().contains((this.password))) {
			System.out.println(">>password correcto"+mensajeRecibido.toLowerCase());
			
			return true;
		}
		System.out.println(">>password incorrecto");
		return false;
		
	}

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
	
	public void setMensaje(String newMsj) {
		this.msjDatos.setMatriz(newMsj);
		
	}

	private void GameUpdate(String msg) throws IOException {
		System.out.println("Hola");
		StringWriter toJson = new StringWriter();
		toJson = toJson.append(msg, 2, msg.length());
		System.out.println(toJson.toString());
		Decode decode = new Decode(toJson);
		
		if(decode.command == 2) {
		System.out.println("comando2");
		String currentConnection = Integer.toString(decode.getCurrentConnection());
		String Maxplayers = Integer.toString(decode.getMaxPlayers());
		setMensaje(currentConnection+","+Maxplayers);
		}
		if (decode.command == 4) {
			System.out.println(">>comando4");
			setMensaje(this.password);
		}
	}

}

