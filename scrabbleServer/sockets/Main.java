package sockets;

public class Main {

	public static void main(String[] args) {
		//New thread running the Server
		GameServer server = new GameServer();
		Thread hilo = new Thread(server);
		hilo.start();
		//

	}

}