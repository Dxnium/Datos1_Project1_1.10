package game.logic;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.concurrent.ThreadLocalRandom;

public class GameFlow {

	static BufferedReader in= new BufferedReader (new InputStreamReader(System.in));
	static PrintStream out=System.out;
	private static Board game = new Board();
	

	public static void main(String[] args) throws IOException {
		game.initializeTableTop();
		System.out.println("----------------------------------------");
		game.getDictionary().generateDictionaryBook();
		System.out.println("----------------------------------------");
		game.InitializeDeck();
		System.out.println("----------------------------------------");
		promptGameStart();

		


	}

	public static void promptGameStart() throws IOException{
		System.out.println("----------------------------------------");
		System.out.println("----------------------------------------");
		out.println("Bienvenido a Scrabble");
		out.println("desea iniciar un nuevo juego?");
		out.println("Y/N?");
		String input = in.readLine();
		if (input.equals("Y")||                    input.equals("y")) {
			out.println("La partida puede tener un maximo de cuatro jugadores y un minimo de dos para poder empezar");
			playerCreation();
		}else if(input.equals("N")||input.equals("n")) {
			out.println("hasta luego!");

		}


	}

	private static void playerCreation() throws IOException {
		if(game.getPlayerList().getLength()<4) {
			out.println("Ingrese el nombre del nuevo jugador");
			String name = in.readLine();
			game.getPlayerList().append(new Player(name));
		}
		
		if(game.getPlayerList().getLength()==4) {
			System.out.println("no puede agregar mas jugadores");
			matchStarter();
			}
		
		out.println("desea agregar otro jugador?");
		String decision= in.readLine();
		if (decision.equals("Y")||decision.equals("y")) {
			playerCreation();	
				
		}else if(decision.equals("N")||decision.equals("n")) {
			if(game.getPlayerList().getLength()>=2) {
				matchStarter();
			} else {
				out.println("La partida puede empezar solo si hay 2 jugadores o mas");
				playerCreation();
			}
			
		}
		
		
	}
	
	private static boolean verifyWord(String word)    {
		DictionaryNode current;
		
			for(current =game.getDictionary().getWordBook().getHead(); current != null; current = current.getNext()) {
				if(current.getData().equals(word)) { 
					out.println("FOUND");
					return true;
				}
			}
			out.println("NOT FOUND");
			return false;
	}

	
	private static  LetterTile randomTileGenerator() {//Method that generates a random letter tile, taking into consideration the amount
		int positionCounter=0;									// of tiles per letter, the tiles bigger in amount have a bigger chance of dropping																					
		int index=0;																			
		int randomNum = ThreadLocalRandom.current().nextInt(1, game.getDeckSize()+1);
		while(positionCounter<randomNum) {
			positionCounter+=game.getDeck()[index].getAmount();
			index++;
		}
		index-=1;
		
		out.println("La letra es: "+ game.getDeck()[index].getLetter());
		game.setDeckSize(game.getDeckSize()-1);
		game.getDeck()[index].setAmount(game.getDeck()[index].getAmount()-1);
		return game.getDeck()[index];
		
		
		
	}
	
	 private static void matchStarter() throws IOException {
		 playOrder();
		 dealTiles();
		 turnHandler();
		 
	 }
	
	 private static void dealTiles() {
		 int count=0;
		 int index=0;
		 for (PlayerLinkedListNode node=game.getPlayerList().getHead();count!=game.getPlayerList().getLength()*7;node=node.getNext()) {
			 node.getData().getDock()[index]=randomTileGenerator();
			 node.getData().setMyTiles(node.getData().getMyTiles()+1);
			 count++;
			 if (count%game.getPlayerList().getLength()==0) {
				 index++;
			 }
		 }
	 }
		

	private static void playOrder() {
		int count=0;
		PlayerLinkedListNode currentNode=game.getPlayerList().getHead();
		Player currentPlayer= currentNode.getData();
		while (count!=game.getPlayerList().getLength()){
			currentPlayer.getDock()[0]=randomTileGenerator();
			currentPlayer.getDock()[0].setAmount(currentPlayer.getDock()[0].getAmount()+1);
			game.setDeckSize(game.getDeckSize()+1);
			out.println(currentPlayer.getName());
			out.println(currentPlayer.getDock()[0].getLetter());
			currentNode=currentNode.getNext();
			currentPlayer=currentNode.getData();
			count++;	
		}
		selectionSort();
		
	}
	
	private static void selectionSort() {
		int x=0;
		for (PlayerLinkedListNode node=game.getPlayerList().getHead();x!=game.getPlayerList().getLength();node=node.getNext()) {
			out.print(node.getData().getDock()[0].getLetter()+" ");
			x++;
		}
		for (PlayerLinkedListNode selected=game.getPlayerList().getHead();selected!=game.getPlayerList().getTail();selected=selected.getNext()) {
			for(PlayerLinkedListNode selected2=selected.getNext();selected2!=game.getPlayerList().getHead();selected2=selected2.getNext()) {
				if(selected2.getData().getDock()[0].getLetter().compareTo(selected.getData().getDock()[0].getLetter())<0) {
					Player temp=selected.getData();
					selected.setData(selected2.getData());
					selected2.setData(temp);
	
				}
		
			}
			
	}
		x=0;
		out.println(" ");
		for (PlayerLinkedListNode node=game.getPlayerList().getHead();x!=game.getPlayerList().getLength();node=node.getNext()) {
			out.print( node.getData().getDock()[0].getLetter()+" ");
			x++;
		}
		x=0;
		out.println(" ");
		for (PlayerLinkedListNode node=game.getPlayerList().getHead();x!=game.getPlayerList().getLength();node=node.getNext()) {
			node.getData().getDock()[0]=null;
			x++;
		}
		
  }
	public static void turnHandler() throws IOException{
		 
		System.out.println("&&&&&&&&&&&&&&&&&&");
		
		for (PlayerLinkedListNode node=game.getPlayerList().getHead();game.getDeckSize() != 0;node=node.getNext()) {
			out.print( "Turno de:"+ node.getData().getName());
			System.out.println("\n");
			System.out.println("¿Que desea hacer?"+"\n"+"1.Jugar"+"\n"+"2.Pasar");
			
			String action = in.readLine();
			
			if (action.equals("Jugar")||action.equals("jugar")) {
				System.out.println("Aunque no lo creas, estás jugando...");

			}else if(action.equals("Pasar")||action.equals("pasar")) {
				System.out.println("Espero que no te arrepientas");
			}

			
			
		}
		System.out.println("\n");
		System.out.println("&&&&&&&&&&&&&&&&&&");
		
	}
	
}




















		
		
	

