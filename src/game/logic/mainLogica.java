package game.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class mainLogica {
	
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
	
	private static void refillTiles(PlayerLinkedListNode node) {
		for(int index=0;index!=node.getData().getDock().length;index++) {
			if(node.getData().getDock()[index]==null) {
				node.getData().getDock()[index]=randomTileGenerator();
			}
		}printPlayerDock(node);
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
			String[][] selectedTiles = playTurn(node);
			if(verifyOrientation(selectedTiles).equals("invalid")) {
				out.println("Posicion de ficha invalida, por favor intente de nuevo");
				turnHandler(node);
			}
			String [][] sortedTiles=sortSelectedTiles(verifyOrientation(selectedTiles),selectedTiles);
			updateTableTop(sortedTiles,node,verifyOrientation(selectedTiles));

				
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
		
	private static void updateTableTop(String[][] sortedTiles, PlayerLinkedListNode node, String orientation) throws IOException {
		printTableTop();
		if( game.getTurn()==0) {
			if(checkFirstWord(sortedTiles,node, orientation)==true) {
				placeFirstWord(node, sortedTiles);
			}else{
				turnHandler(node);
			}
		}else{
			placeNewWord(sortedTiles,node,orientation);
				
			
		}
	}
	
	
	private static void placeNewWord(String[][] sortedTiles, PlayerLinkedListNode node, String orientation) throws IOException {
		for(int index=0;index!=sortedTiles.length;index++) {
			int tile=Integer.parseInt(sortedTiles[index][0]);
			int x=Integer.parseInt(sortedTiles[index][1]);
			int y=Integer.parseInt(sortedTiles[index][2]);
			if(game.getTableTop()[x][y].getLetterTile()==null) {
				game.getTableTop()[x][y].setLetterTile(node.getData().getDock()[tile]);
				game.getTableTop()[x][y].getLetterTile().setPlayedOnTurn(game.getTurn());
			}else{
				out.println("Error");
			}
			
		}
		printTableTop();
		for(int element=0;element!=sortedTiles.length;element++) {
			String[] horizontal=checkNewWord(sortedTiles,Integer.parseInt(sortedTiles[element][1]),Integer.parseInt(sortedTiles[element][2]),node,"horizontal");
			String[] vertical=checkNewWord(sortedTiles,Integer.parseInt(sortedTiles[element][1]),Integer.parseInt(sortedTiles[element][2]),node,"vertical");
			game.getPlayedWords().append(horizontal);
			game.getPlayedWords().append(vertical);
		}		
		processPlayedWords(sortedTiles,node);
	}

	/**
	 * Check new word concatenates the strings of the placed tiles and forms words, it obtains the scores with applied multipliers, returns an array with both of these data
	 *
	 * @param sortedTiles the sorted tiles
	 * @param x the x
	 * @param y the y
	 * @param node the node
	 * @param orientation the orientation
	 * @return the string[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static String[] checkNewWord(String[][] sortedTiles,int x,int y, PlayerLinkedListNode node, String orientation) throws IOException {
		String[] data=new String[2];
		String word="";
		int column=y;
		int row=x;
		int points=0;
		LetterTile horizontalTile=game.getTableTop()[x][column].getLetterTile();
		LetterTile verticalTile=game.getTableTop()[row][y].getLetterTile();
			if(orientation.equals("horizontal")) {
				while(horizontalTile!=null) {
					word=horizontalTile.getLetter().toLowerCase()+word;
					points=points+horizontalTile.getScore()*game.getTableTop()[x][column].getMultiplier();
					column=column-1;
					horizontalTile=game.getTableTop()[x][column].getLetterTile();
				}
				column=y;
				horizontalTile=game.getTableTop()[x][column+1].getLetterTile();
				while(horizontalTile!=null) {
					word=word+horizontalTile.getLetter().toLowerCase();
					points=points+horizontalTile.getScore()*game.getTableTop()[x][column].getMultiplier();
					column=column+1;
					horizontalTile=game.getTableTop()[x][column+1].getLetterTile();
				}
				
			}
			if(orientation.equals("vertical")) {
				while(verticalTile!=null) {
					word=verticalTile.getLetter().toLowerCase()+word;
					points=points+verticalTile.getScore()*game.getTableTop()[row][y].getMultiplier();
					row=row-1;
					verticalTile=game.getTableTop()[row][y].getLetterTile();
				}
				row=x;
				verticalTile=game.getTableTop()[row+1][y].getLetterTile();
				while(verticalTile!=null) {
					word=word+verticalTile.getLetter().toLowerCase();
					points=points+verticalTile.getScore()*game.getTableTop()[row][y].getMultiplier();
					row=row+1;
					verticalTile=game.getTableTop()[row+1][y].getLetterTile();
				}
				
			}
			
			
		data[0]=word;
		data[1]=Integer.toString(points);
		return data;
	}
	
	private static boolean checkNewPlacement(String[][] sortedTiles) {
		boolean isAdjacent=false;
		for(int index=0;index!=sortedTiles.length;index++) {
			int x=Integer.parseInt(sortedTiles[index][1]);
			int y=Integer.parseInt(sortedTiles[index][2]);
			if(game.getTableTop()[x+1][y].getLetterTile()!=null) {
				if(game.getTableTop()[x+1][y].getLetterTile().getPlayedOnTurn()<game.getTurn()) {
					isAdjacent=true;
					game.getTableTop()[x][y].getLetterTile().setConected(true);
					continue;
				}else if(game.getTableTop()[x+1][y].getLetterTile().isConected()) {
					isAdjacent=true;
					game.getTableTop()[x][y].getLetterTile().setConected(true);
					continue;
				}else{
					isAdjacent=false;
				}
				
			}else{
				isAdjacent=false;
			}
			if(game.getTableTop()[x][y-1].getLetterTile()!=null) {
				if(game.getTableTop()[x][y-1].getLetterTile().getPlayedOnTurn()<game.getTurn()) {
					isAdjacent=true;
					game.getTableTop()[x][y].getLetterTile().setConected(true);
					continue;
				}else if(game.getTableTop()[x][y-1].getLetterTile().isConected()) {
					isAdjacent=true;
					game.getTableTop()[x][y].getLetterTile().setConected(true);
					continue;
				}else{
					isAdjacent=false;
				}
				
			}else{
				isAdjacent=false;
			}
			if(game.getTableTop()[x-1][y].getLetterTile()!=null) {
				if(game.getTableTop()[x-1][y].getLetterTile().getPlayedOnTurn()<game.getTurn()) {
					isAdjacent=true;
					game.getTableTop()[x][y].getLetterTile().setConected(true);
					continue;
				}else if(game.getTableTop()[x-1][y].getLetterTile().isConected()) {
					isAdjacent=true;
					game.getTableTop()[x][y].getLetterTile().setConected(true);
					continue;
				}else{
					isAdjacent=false;
				}
				
			}else{
				isAdjacent=false;
			}
			if(game.getTableTop()[x][y+1].getLetterTile()!=null) {
				if(game.getTableTop()[x][y+1].getLetterTile().getPlayedOnTurn()<game.getTurn()) {
					isAdjacent=true;
					game.getTableTop()[x][y].getLetterTile().setConected(true);
					continue;
				}else if(game.getTableTop()[x][y+1].getLetterTile().isConected()) {
					isAdjacent=true;
					game.getTableTop()[x][y].getLetterTile().setConected(true);
					continue;
				}else{
					isAdjacent=false;
				}
				
			}else{
				isAdjacent=false;
			}
		}
		return isAdjacent;
	}

	private static void placeFirstWord(PlayerLinkedListNode node, String[][] sortedTiles) throws IOException {
		String word="";
		int points=0;

		for(int index=0;index!=sortedTiles.length;index++) {
			int tile=Integer.parseInt(sortedTiles[index][0]);
			int x=Integer.parseInt(sortedTiles[index][1]);
			int y=Integer.parseInt(sortedTiles[index][2]);
			game.getTableTop()[x][y].setLetterTile(node.getData().getDock()[tile]);
			game.getTableTop()[x][y].getLetterTile().setPlayedOnTurn(game.getTurn());
			out.println("Punto de ficha: "+node.getData().getDock()[tile].getScore());
			out.println("Multiplicador: "+game.getTableTop()[x][y].getMultiplier());
			points+=node.getData().getDock()[tile].getScore()*game.getTableTop()[x][y].getMultiplier();
			word=word+node.getData().getDock()[tile].getLetter().toLowerCase();
			
		}
		if(verifyWord(word)==true) {
			for(int index=0;index!=sortedTiles.length;index++) {
				int tile=Integer.parseInt(sortedTiles[index][0]);
				node.getData().getDock()[tile]=null;
			}
			node.getData().setScore(node.getData().getScore()+points);
			out.println("Puntos obtenidos por la palabra "+word+": "+points);
			out.println("Puntos totales del jugador: "+node.getData().getScore());
			printTableTop();
			game.setTurn(game.getTurn()+1);
			refillTiles(node);
			turnHandler(node.getNext());
		}else{
			out.println("La palabra es invalida");
			for(int index=0;index!=sortedTiles.length;index++) {
				int x=Integer.parseInt(sortedTiles[index][1]);
				int y=Integer.parseInt(sortedTiles[index][2]);
				game.getTableTop()[x][y].setLetterTile(null);
			}
			printTableTop();
			turnHandler(node);
		}
		
	}

	private static boolean checkFirstWord(String[][] sortedTiles, PlayerLinkedListNode node, String orientation) throws IOException {
		boolean check=false;
		if(sortedTiles.length>=2) {
			if((sortedTiles[0][1].equals("7")&&sortedTiles[0][2].equals("7"))||(sortedTiles[1][1].equals("7")&&sortedTiles[sortedTiles.length-1][2].equals("7"))) {
				if (orientation.equals("vertical")) {
					for(int index=0;index!=sortedTiles.length-1;index++) {
						if(Integer.parseInt(sortedTiles[index+1][1])==Integer.parseInt(sortedTiles[index][1])+1) {
							check=true;
						}else{
							check=false;
							out.println("ERROR: debe de ingresar su primera palabra en una hilera");
							break;
						}
					}
				}else{
					for(int index=0;index!=sortedTiles.length-1;index++) {
						if(Integer.parseInt(sortedTiles[index+1][2])==Integer.parseInt(sortedTiles[index][2])+1) {
							check=true;
						}else{
							out.println("ERROR: debe de ingresar su primera palabra en una hilera continua");
							check=false;
							break;
						}
					}
					

				}

			}else {
				out.println("Debe colocar su ficha inicial o final en el centro del tablero");
				check=false;
			}

		}else {
			out.println("Debe usar al menos dos fichas para la primera palabra");
			check=false;
		}
		return check;
	}
		
	private static void processPlayedWords(String[][] sortedTiles,PlayerLinkedListNode node) throws IOException {
		
		game.getPlayedWords().removeDuplicates();
		boolean valid=true;
		int index=0;
		int points=0;
		WordListNode current= game.getPlayedWords().getHead();
		while(index<game.getPlayedWords().getLength()) {
			if (verifyWord(current.getData()[0])) {
				valid=true;
				points=points+Integer.parseInt(current.getData()[1]);
			}else {
				valid=false;
				break;
			}
			current=current.getNext();
			index++;
		}
		if(valid==true) {
			node.getData().setScore(node.getData().getScore()+points);
			eraseUsedTiles(sortedTiles,node);
			refillTiles(node);
			game.setTurn(game.getTurn()+1);
			game.getPlayedWords().setHead(null);
			turnHandler(node.getNext());
		}else {
			game.getPlayedWords().setHead(null);
			erasePlacedTiles(sortedTiles);
			turnHandler(node.getNext());
		}
		
	}
	
	private static void erasePlacedTiles(String[][] sortedTiles) {
		for(int index=0;index!=sortedTiles.length;index++) {
			int x=Integer.parseInt(sortedTiles[index][1]);
			int y=Integer.parseInt(sortedTiles[index][2]);
			game.getTableTop()[x][y].setLetterTile(null);
		}		
	}


	private static void eraseUsedTiles(String[][] sortedTiles, PlayerLinkedListNode node) {
		for(int index=0;index!=sortedTiles.length;index++) {
			int tile=Integer.parseInt(sortedTiles[index][0]);
			node.getData().getDock()[tile]=null;
		}
		
	}

	
	
	private static void printTableTop() {
		for (int i = 0; i < game.getTableTop().length; i++) {
		    for (int j = 0; j < game.getTableTop()[i].length; j++) {
		        if(game.getTableTop()[i][j].getLetterTile()!=null) {
		        	out.print("["+game.getTableTop()[i][j].getLetterTile().getLetter() + "] ");	
		        }else {
		        	
		        	out.print("[  ]"+" ");
		        }
		    	
		    }
		    System.out.println();
		}
		out.println("=============================");
	}
	
	
	
	
	}

