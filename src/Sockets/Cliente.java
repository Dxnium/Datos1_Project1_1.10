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


// TODO: Auto-generated Javadoc
/**
 * The Class Cliente.
 */
public class Cliente {



	/** The port. */
	final int port = 5555;
	
	/** The address. */
	String address = "";

	/** The socket. */
	Socket socket;
	
	/** The mensaje salida. */
	String mensajeSalida;
	
	/** The msg. */
	public String msg = null; //Mensaje resivido del servidor 
	
	/** The password. */
	String password = "";
	
	/** The nombre. */
	String nombre = "";
	
	/** The msj datos. */
	public Message msjDatos = new Message("vacio");
	
	/**
	 * Instantiates a new cliente.
	 *
	 * @param address the address
	 * @param mensaje1 the mensaje 1
	 */
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
			System.out.println(GetJMensaje().toString());
			mensaje.writeUTF(GetJMensaje().toString());
			System.out.println("Cerrando conexión...Cliente");
			mensaje.flush();
			
			
			//leer mensaje
			msg = entradaDatos.readUTF();
			System.out.println(msg);
//			GameUpdate(msg);
		
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
		
		/**
		 * Instantiates a new cliente.
		 *
		 * @param address the address
		 */
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
//				System.out.println("*****************DECODE*****************");
//				GameUpdate(msg);
			
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
		
		/**
		 * Gets the J mensaje.
		 *
		 * @return the writer
		 */
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
/**
 * Game update.
 *
 * @param msg the msg
 * @throws IOException Signals that an I/O exception has occurred.
 */
// tanto en la matriz de juego como en pantalla 
	private void GameUpdate(String msg) throws IOException {
		StringWriter toJson = new StringWriter();
		toJson = toJson.append(msg, 0, msg.length());
		Decode decode = new Decode(toJson);
		
		
	}
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Sets the mensaje.
	 *
	 * @param newMsj the new mensaje
	 */
	public void setMensaje(String newMsj) {
		this.msjDatos.setMatriz(newMsj);
		
	}

	
}
