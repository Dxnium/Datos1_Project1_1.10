package Sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {



	final int port = 5555;
	String address = "";

	Socket socket;
	String mensajeSalida;
	public String msg = null; //Mensaje resivido del servidor 
	String password = "";
	String nombre = "";
	
	//constructor que envia el password para verificar la conexcion con el server
	public Cliente(String address,String password) {
		this.address = address;
		try {
			socket = new Socket(address,port);
			System.out.println("Hola desde el cliente");
			//creamos el flujo de datos por el que se enviara un mensaje
			DataInputStream entradaDatos = new DataInputStream(socket.getInputStream());
			DataOutputStream mensaje = new DataOutputStream(socket.getOutputStream());
			
			//enviamos el mensaje
			mensaje.writeUTF(password);
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
		
		//constructor que solo envia el mensaje 
		public Cliente(String address) {
			this.address = address;
			try {
				socket = new Socket(address,port);
				System.out.println("Hola desde el cliente");
				//creamos el flujo de datos por el que se enviara un mensaje
				DataInputStream entradaDatos = new DataInputStream(socket.getInputStream());
				DataOutputStream mensaje = new DataOutputStream(socket.getOutputStream());
				
				//enviamos el mensaje
				mensaje.writeUTF(msjSalida());
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
		//metodo que genera el mensaje de salida para el server
private String msjSalida() {
			return "msjSalida";
		}


// GameUpdate hace un decode del msj del server y 
// llama a los metodos correspondientes para generar el update
// tanto en la matriz de juego como en pantalla 
	private void GameUpdate(String msg) {
		if(msg!=null) {
			System.out.println("Mensaje recibido, update juego");
		}else {
			System.out.println("Ningun mensaje recibido");
		}
		
		
		
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
