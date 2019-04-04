package game.logic;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
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
		turnHandler(game.getPlayerList().getHead());
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
	public static void printPlayerDock(PlayerLinkedListNode node) {
		for(int x=0;x<7;x++) {
			out.print(node.getData().getDock()[x].getLetter()+" ");
		}
	}
	
	public static String[][] playTurn(PlayerLinkedListNode node) throws IOException {//class that the client will use to select tiles and positions on the matrix
		int index=0;
		String[][] tilesToUse= new String[7][3];
		while(index<7) {
			out.println("Cual ficha desea usar");
			String selection = in.readLine();
			out.println("En que posicion desea colocarla?");
			out.println("Fila:");
			String positionX=in.readLine();
			out.println("Columna:");
			String positionY=in.readLine();
			tilesToUse[index][0]=selection;
			tilesToUse[index][1]=positionX;
			tilesToUse[index][2]=positionY;
			out.println("Desea poner otra ficha?   (Y/N)");
			String decision=in.readLine();
			if(decision.equals("N")||decision.equals("n")) {
				break;
			}
			
			index++;
		}
		
		int count=0;
		while(tilesToUse[count][0]!=null) {
			count++;
		}
		String[][] finalTilesToUse= new String [count][3];
		int newElement=0;
		while(newElement<=count-1) {
			finalTilesToUse[newElement][0]=tilesToUse[newElement][0];
			finalTilesToUse[newElement][1]=tilesToUse[newElement][1];
			finalTilesToUse[newElement][2]=tilesToUse[newElement][2];
			newElement++;
		}
		out.println(Arrays.deepToString(finalTilesToUse));
		out.println(" ");
		return finalTilesToUse;
		
		
		
		}
	
		
	
	public static void turnHandler(PlayerLinkedListNode node) throws IOException{//class that server will use to manage the turns

		out.println("&&&&&&&&&&&&&&&&&&");
		out.print( "Turno de:"+ node.getData().getName());
		out.println("\n");
		out.println("Sus fichas son:");
		printPlayerDock(node);
		out.println("¿Que desea hacer?"+"\n"+"1.Jugar"+"\n"+"2.Pasar");

		String action = in.readLine();

		if (action.equals("Jugar")||action.equals("jugar")) {
			game.setTurn(game.getTurn()+1);
			String[][] selectedTiles = playTurn(node);
			if(verifyOrientation(selectedTiles).equals("invalid")) {
				out.println("Invalid tile placement, please select a new action");
				turnHandler(node);
			}
			String [][] sortedTiles=sortSelectedTiles(verifyOrientation(selectedTiles),selectedTiles);
			updateTableTop(sortedTiles);

				
			}else if(action.equals("Pasar")||action.equals("pasar")) {
				out.println("Espero que no te arrepientas");
				game.setTurn(game.getTurn()+1);
			}
		out.println("\n");
		out.println("&&&&&&&&&&&&&&&&&&");
		}
	
	

	private static String[][] sortSelectedTiles(String orientation,String[][] selectedTiles) {//sorts tiles to play accordingly to the word orientation, uses bubblesort.
		int element=0;																							  //if the word is vertical, sorts ascendantly using the row number 
		int length = selectedTiles.length; 																	 //if the word is horizontal, sorts ascendantly using the column number
		String[] temp = null;
		if (orientation.equals("vertical")) {
			element=1;
		}else {
			element=2;
		}
		if(length>1) {
			for(int subarray=0;subarray<length;subarray++) {
				for(int comparison=subarray+1;comparison<length;comparison++) {
					if(Integer.parseInt(selectedTiles[subarray][element])>Integer.parseInt(selectedTiles[comparison][element])) {
						temp=selectedTiles[subarray];
						selectedTiles[subarray]=selectedTiles[comparison];
						selectedTiles[comparison]=temp;		
					}
				}
			}
		}
		out.println(Arrays.deepToString(selectedTiles));
		return selectedTiles;
	}

	
	
	private static String verifyOrientation(String[][] selectedTiles) {//determines the orientation of a word on the matrix(vertical, horizontal), else, determines invalid position
		String orientation = null;
		int index=0;
		boolean vertical=false;
		boolean horizontal=false;
		boolean single=false;
		
		if(selectedTiles.length==1) {
			single=true;
			orientation= "single";
		}
		
		while(index<selectedTiles.length-1) {
			if(selectedTiles[index][1].equals(selectedTiles[index+1][1])) {
				horizontal=true;
			}else {
				horizontal=false;
			}
			index++;
		}
		if (horizontal==true) {
			orientation= "horizontal";
		}
		index=0;
		while(index<selectedTiles.length-1) {
			if(selectedTiles[index][2].equals(selectedTiles[index+1][2])) {
				vertical=true;
			}else {
				vertical=false;
			}
			index++;
		}
		
		if (vertical==true) {
			orientation= "vertical";
		}
		
		if(vertical==false&&horizontal==false&&single==false) {
			orientation="invalid";
		}
		return orientation;
	}
		
	private static void updateTableTop(String[][] sortedTiles) {
		printTableTop();
		
	}

	private static void printTableTop() {
		for (int i = 0; i < game.getTableTop().length; i++) {
		    for (int j = 0; j < game.getTableTop()[i].length; j++) {
		        if(game.getTableTop()[i][j].getLetterTile()!=null) {
		        	out.print(game.getTableTop()[i][j].getLetterTile().getLetter() + " ");	
		        }else {
		        	out.print("null"+" ");
		        }
		    	
		    }
		    System.out.println();
		}
		
	}
	
	
	
	
	}






























