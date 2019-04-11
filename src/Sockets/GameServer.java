package Sockets;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONArray;

import JSON.Encode;
import Msg.Message;
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
	private String msjDatos = "Vacio";
	
	
	
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
					BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream())); 
					DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
					
					//Confirmacion de conexion con mensaje 
					System.out.println(">>Enviando update al cliente");
					if(verPassword) {
						salida.writeUTF(GetJMensaje());
					}else {
//						salida.writeUTF(this.password+","+GetJMensaje());//toma el mensaje que se debe enviar al cliente 
						salida.writeUTF("true");
						}			
					
					//Recepcion de mensaje
					String mensajeRecibido = entrada.readLine();
					System.out.println(">>Mensaje recibido: "+ mensajeRecibido);
					if(!verPassword) {
						verPassword = verificarPassword(mensajeRecibido);
					}

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

	private String GetJMensaje() {
		if(verPassword) {
		Encode datos = new Encode();
		//Crea el arreglo con los datos de la Clase 
		JSONArray arr = datos.arrayData(new Message(this.msjDatos));
		Writer out = new StringWriter();//crear un variable de tipo Writer para almacenar el array y poder mostarlo en pantalla 
		try {
			arr.writeJSONString(out); //guardar el JSONArray en un string 
		} catch (IOException e) {
			e.printStackTrace();
			
		}String out1 = out.toString();
		return out1;
	 }
		return "Conexion invalida ";
	}
	
	public void setMensaje(String newMsj) {
		this.msjDatos = newMsj;
		
	}

}
