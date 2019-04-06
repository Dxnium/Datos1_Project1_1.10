package sockets;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable  {
	ServerSocket server;  
	Socket socket; 
	final int port = 9999;

	@Override
	public void run() {
		try {
			//server created in port 9999
			server = new ServerSocket(port);
			System.out.println("New server started");	
			socket = server.accept();//Waiting for a client 
			
			//Client connected 
			System.out.println("Client connect");
			
			//Canales de entrada y salida 
			BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
			
			//Confirmacion de conexion con mensaje 
			System.out.println("Confirmando conexion al cliente....");
			salida.writeUTF(GetJMensaje());//toma el mensaje que se debe enviar al cliente 
	
			//Recepcion de mensaje
			String mensajeRecibido = entrada.readLine();
			System.out.println("Mensaje desde el cliente:"+mensajeRecibido);
			
			//Cerrando conexion 
			System.out.println("Cerrando conexión...Server");
			socket.close();//Aqui se cierra la conexión con el cliente
			
		} catch (IOException e) {
			System.out.println("Connetion failed");
		}
	}

	private String GetJMensaje() {
		return "No hay mensaje";
	}
}