package sockets;

public class Main {

	public static void main(String[] args) {
		//New thread running the Server
		Server server = new Server();
		Thread hilo = new Thread(server);
		hilo.start();
		//

	}

}