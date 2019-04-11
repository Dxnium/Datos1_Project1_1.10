package Sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.simple.JSONArray;

import JSON.Encode;
import JSON.Decode;
import Msg.Message;


public class Cliente {



	final int port = 5555;
	String address = "";

	Socket socket;
	String mensajeSalida;
	public String msg = null; //Mensaje resivido del servidor 
	String password = "";
	String nombre = "";
	public Message msjDatos = new Message("vacio");
	
	//constructor que envia el password para verificar la conexcion con el server
	public Cliente(String address,String mensaje1) {
		setMensaje(mensaje1);
		this.address = address;
		try {
			socket = new Socket(address,port);
			System.out.println("Hola desde el cliente");
			//creamos el flujo de datos por el que se enviara un mensaje
			DataInputStream entradaDatos = new DataInputStream(socket.getInputStream());
			DataOutputStream mensaje = new DataOutputStream(socket.getOutputStream());
			
			//enviamos el mensaje
			mensaje.writeUTF(GetJMensaje().toString());
			System.out.println("Cerrando conexión...Cliente");
			mensaje.flush();
			
			
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
				mensaje.writeUTF(GetJMensaje().toString());
				System.out.println("Cerrando conexión...Cliente");
				
				//leer mensaje
				msg = entradaDatos.readUTF();
				
				System.out.println(msg);
				System.out.println("*****************DECODE*****************");
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


// GameUpdate hace un decode del msj del server y 
// llama a los metodos correspondientes para generar el update
// tanto en la matriz de juego como en pantalla 
	private void GameUpdate(String msg) throws IOException {
		StringWriter toJson = new StringWriter();
		toJson = toJson.append(msg, 0, msg.length());
		Decode decode = new Decode(toJson);
		
		
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
	public void setMensaje(String newMsj) {
		this.msjDatos.setMatriz(newMsj);
		
	}
}
