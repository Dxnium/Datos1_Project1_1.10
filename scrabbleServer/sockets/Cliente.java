package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	final int port = 9999;
	String address = "localhost";

	Socket socket;
	String msg = null; //Mensaje resivido del servidor 
	String password = "";
	
	
	public Cliente() {
		try {
			socket = new Socket(address,port);
			System.out.println("Hola desde el cliente");
			//creamos el flujo de datos por el que se enviara un mensaje
			DataInputStream entradaDatos = new DataInputStream(socket.getInputStream());
			DataOutputStream mensaje = new DataOutputStream(socket.getOutputStream());
			
			//enviamos el mensaje
			mensaje.writeUTF("hola que tal!! desde cliente ");
			System.out.println("Cerrando conexión...Cliente");
			
			//leer mensaje
			msg = entradaDatos.readUTF();
			System.out.println(msg);
			GameUpdate(msg);
		
			//cerramos la conexión
			entradaDatos.close();
			socket.close();
		} catch (UnknownHostException e) {
			//IP failed 
			System.out.println("Cannot find ip address");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cannot find server port");
		}
		
		
		
	}
// GameUpdate hace un decode del msj del server y 
// llama a los metodos correspondientes para generar el update
// tanto en la matriz de juego como en pantalla 
	private void GameUpdate(String msg) {
		if(msg!=null) {
			System.out.println("Mensaje resivido, update juego");
		}else {
			System.out.println("Ningun mensaje resivido");
		}
		
		
		
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	

}