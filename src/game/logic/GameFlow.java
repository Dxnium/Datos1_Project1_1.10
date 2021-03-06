package game.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
//import javax.swing.JFrame;
//import graphics.*;

import javax.swing.JFrame;

import com.sun.corba.se.impl.orbutil.graph.Node;
import com.twilio.SMS;




// TODO: Auto-generated Javadoc
/**
 * Class that manages the logical methods that allow the execution of the game, contains an attribute called game, which initializes an instance of
 * the Board Class, this class does all the logical verifications and is in charge of sending feedback to the clients.
 */
public class GameFlow {

	/** The in. */
	static BufferedReader in= new BufferedReader (new InputStreamReader(System.in));
	
	/** The out. */
	static PrintStream out=System.out;
	
	/** The game. */
	private static Board game = new Board();
	
	SMS ExpSMS = new SMS();
	


	/**
	 * Gets the game.
	 *
	 * @return the game
	 */
	public static Board getGame() {
		return game;
	}

	/**
	 * Sets the game.
	 *
	 * @param game the new game
	 */
	public static void setGame(Board game) {
		GameFlow.game = game;
	}


	public static void main(String[] args) throws IOException {
		game.InitializeDeck();
		game.initializeTableTop();
		game.getDictionary().generateDictionaryBook();
		
		
		
		
		

	}
	

	/**
	 * Player creation method, receives a string parameter that will serve as the name of the new player. The player will be created only if
	 * the amount of current connected clients is less than the maximum number of connections allowed.
	 *
	 * @param name the name
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void playerCreation(String name) throws IOException {
		if(game.getCurrentConection()<game.getMaxPlayers()) {
			game.getPlayerList().append(new Player(name));
			out.println(game.getPlayerList().getHead().getData().getName());
			System.out.println("Se ha agregado al jugador: "+name);
		}

		if(game.getCurrentConection()==game.getMaxPlayers()) {
			System.out.println("no puede agregar mas jugadores");
			
		}
	}

	
	/**
	 * the method Verify word travels through the dictionary, comparing string values until it finds the string in the given parameter or reaches the end of the list
	 *
	 * @param word the word
	 * @return true, if successful
	 */
	public static boolean verifyWord(String word)    {
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


	/**
	 * Random tile generator uses a random number generator to select a random tile from the game deck, taking into consideration the amount
	 * of tiles per letter, the tiles bigger in amount have a bigger chance of dropping
	 *
	 * @return the letter tile
	 */
	private static  LetterTile randomTileGenerator() {
		int positionCounter=0;																													
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
		
	}
	

	/**
	 * Deal tiles.
	 */
	public static void dealTiles() {
		int count=0;
		int index=0;
		String[][] listaLetras= new String[game.getMaxPlayers()][8];
		for (PlayerLinkedListNode node=game.getPlayerList().getHead();count!=game.getPlayerList().getLength()*7;node=node.getNext()) {
			node.getData().getDock()[index]=randomTileGenerator();
			node.getData().setMyTiles(node.getData().getMyTiles()+1);
			count++;
			if (count%game.getPlayerList().getLength()==0) {
				index++;
			}
		}

	}
		
	/**
	 * returns the tiles on a specific player dock, used for data transfer to client 
	 *
	 * @return the string[][]
	 */
	public static String[][] sendTiles(){
		int index=0;
		String[][] listaLetras= new String[game.getPlayerList().getLength()][8];
		for (PlayerLinkedListNode node=game.getPlayerList().getHead();index!=game.getPlayerList().getLength();node=node.getNext()) {
			listaLetras[index][0]=node.getData().getName();
			listaLetras[index][1]=node.getData().getDock()[0].getLetter();
			listaLetras[index][2]=node.getData().getDock()[1].getLetter();
			listaLetras[index][3]=node.getData().getDock()[2].getLetter();
			listaLetras[index][4]=node.getData().getDock()[3].getLetter();
			listaLetras[index][5]=node.getData().getDock()[4].getLetter();
			listaLetras[index][6]=node.getData().getDock()[5].getLetter();
			listaLetras[index][7]=node.getData().getDock()[6].getLetter();
			index++;
	}
		return listaLetras;
		
		
	}
	
	/**
	 * Refill tiles needed in the player dock until it fills completely
	 *
	 * @param node the node
	 */
	private static void refillTiles(PlayerLinkedListNode node) {
		for(int index=0;index!=node.getData().getDock().length;index++) {
			if(node.getData().getDock()[index]==null) {
				node.getData().getDock()[index]=randomTileGenerator();
			}
		}printPlayerDock(node);
	}


	/**
	 * Play order draws a random tile per player, then uses selection sort to sort the array from lesser to greater string value
	 */
	public static void playOrder() {
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

	/**
	 * Selection sort, classic selection sort algorithm applied to the player circular linked list, sorts the nodes from lesser string value to greater string value
	 */
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
	
	
	/**
	 * Play turn takes two dimensional matrix that has the tiles to be used and null sublists and creates a new array that contains only the tiles to be used
	 * @param tilesToUse the tiles to use
	 * @return 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static boolean playTurn(String [] allTiles) throws IOException {//class that the client will use to select tiles and positions on the matrix
			
		String[][] tilesToUse= new String[7][3];
		int count=0;
		int subindex=1;
		for(int index=0;index!=7;index++) {
			String[] subarray=new String[3];
			while(count<3) {
				subarray[count]=allTiles[subindex];
				subindex++;
				count++;
			}
			tilesToUse[index]=subarray;
			count=0;
		}
			
		
		
		int counter=0;
		for(int index1=0;index1!=tilesToUse.length;index1++) {
			if(!tilesToUse[index1][1].equals("null")) {
				counter++;
			}
			
		}
		
		out.println(Arrays.deepToString(tilesToUse));
		out.println(counter + "contador");
		String[][] finalTilesToUse= new String [counter][3];
		int x=0;
		for(int index2=0;index2!=tilesToUse.length;index2++) {
			out.println(index2);
			if(!tilesToUse[index2][1].equals("null")) {
				finalTilesToUse[x][0]=tilesToUse[index2][0];
				finalTilesToUse[x][1]=tilesToUse[index2][1];
				finalTilesToUse[x][2]=tilesToUse[index2][2];
				x++;
			}
		}
		out.println(Arrays.deepToString(finalTilesToUse));
		out.println(" ");
		String word="";
		for(int letter=0;letter!=finalTilesToUse.length;letter++) {
			word=word+finalTilesToUse[letter][0].toLowerCase();
		}	
		out.println(word);
		 return verifyWord(word);//return
		}
	
		
	


	/**
	 * Turn handler method that server uses to manage the turns and related game logic algorithms
	 * @param orientation 
	 * @param selectedTiles 
	 *
	 * @param node the node
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void turnHandler(String orientation, String[][] selectedTiles) throws IOException{

			if(orientation.equals("invalid")) {
				out.println("Posicion de ficha invalida, por favor intente de nuevo");
				//DEVOLVER A CLIENTE
				}
			String [][] sortedTiles=sortSelectedTiles(verifyOrientation(selectedTiles),selectedTiles);
			updateTableTop(sortedTiles,verifyOrientation(selectedTiles));
//
//				
//			}else if(action.equals("Pasar")||action.equals("pasar")) {
//				game.setTurn(game.getTurn()+1);
//				turnHandler(node.getNext());
//			}
//		out.println("\n");
//		out.println("&&&&&&&&&&&&&&&&&&");
		}
		
	
	

	/**
	 * Sort selected tiles sorts tiles to play accordingly to the word orientation, uses bubblesort
	 *if the word is vertical, sorts ascendantly using the row number
	 *if the word is horizontal, sorts ascendantly using the column number
	 *
	 * @param orientation the orientation
	 * @param selectedTiles the selected tiles
	 * @return the string[][]
	 */
	private static String[][] sortSelectedTiles(String orientation,String[][] selectedTiles) {
		int element=0;																						 
		int length = selectedTiles.length; 																	 
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

	
	
	/**
	 * Verify orientation determines the orientation of a word on the matrix(vertical, horizontal), else, determines invalid position
	 *
	 * @param selectedTiles the selected tiles
	 * @return the string
	 */
	private static String verifyOrientation(String[][] selectedTiles) {
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
		return sortSelectedTiles(orientation,selectedTiles);
	}
		
	private static void updateTableTop(String[][] sortedTiles, String orientation) throws IOException {
		printTableTop();
		if( game.getTurn()==0) {
			if(checkFirstWord(sortedTiles,orientation)==true) {
				placeFirstWord(sortedTiles);
			}else{
				turnHandler(node);
			}
		}else{
			placeNewWord(sortedTiles,node,orientation);
				
			
		}
	}

	/**
	 * Place new word places the tiles used by the player on the tabletop
	 *
	 * @param sortedTiles the sorted tiles
	 * @param node the node
	 * @param orientation the orientation
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
		if(checkNewPlacement(sortedTiles)==false) {
			return null;
		}
		data[0]=word;
		data[1]=Integer.toString(points);
		return data;
	}

	/**
	 * Process played words checks the words created or modified on a single turn, if all words are valid then the move is correct
	 *
	 * @param sortedTiles the sorted tiles
	 * @param node the node
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
			erasePlacedTiles(sortedTiles);
			turnHandler(node);
		}
		
	}

	/**
	 * Check new placement makes sure that no tile isn't connected to another tile played on the current turn or in a previous one
	 *
	 * @param sortedTiles the sorted tiles
	 * @return true, if successful
	 */
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

	/**
	 * Place first word places the first word on the matrix 
	 *
	 * @param node the node
	 * @param sortedTiles the sorted tiles
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
			eraseUsedTiles(sortedTiles,node);
			node.getData().setScore(node.getData().getScore()+points);
			out.println("Puntos obtenidos por la palabra "+word+": "+points);
			out.println("Puntos totales del jugador: "+node.getData().getScore());
			printTableTop();
			game.setTurn(game.getTurn()+1);
			refillTiles(node);
			turnHandler(node.getNext());
		}else{
			out.println("La palabra es invalida");
			erasePlacedTiles(sortedTiles);
			printTableTop();
			turnHandler(node);
		}
		
	}

	/**
	 * Erase placed tiles erases the placed tiles in the tabletop matrix
	 *
	 * @param sortedTiles the sorted tiles
	 */
	private static void erasePlacedTiles(String[][] sortedTiles) {
		for(int index=0;index!=sortedTiles.length;index++) {
			int x=Integer.parseInt(sortedTiles[index][1]);
			int y=Integer.parseInt(sortedTiles[index][2]);
			game.getTableTop()[x][y].setLetterTile(null);
		}		
	}

	/**
	 * Erase used tiles erase the tiles used by the player from the player dock
	 *
	 * @param sortedTiles the sorted tiles
	 * @param node the node
	 */
	private static void eraseUsedTiles(String[][] sortedTiles, PlayerLinkedListNode node) {
		for(int index=0;index!=sortedTiles.length;index++) {
			int tile=Integer.parseInt(sortedTiles[index][0]);
			node.getData().getDock()[tile]=null;
		}
		
	}

	/**
	 * Check first word takes in consideration the special validations of this specific case, used to verify if the actions of the player are valid
	 *
	 * @param sortedTiles the sorted tiles
	 * @param node the node
	 * @param orientation the orientation
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static boolean checkFirstWord(String[][] sortedTiles, String orientation) throws IOException {
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
		
	

	

	private static void printTableTop() {
		for (int i = 0; i < game.getTableTop().length; i++) {
		    for (int j = 0; j < game.getTableTop()[i].length; j++) {
		        if(game.getTableTop()[i][j].getLetterTile()!=null) {
		        	out.print(" ["+game.getTableTop()[i][j].getLetterTile().getLetter() + "] ");	
		        }else {
		        	
		        	out.print(" "+"[_]"+" ");
		        }
		    	
		    }
		    System.out.println();
		}
		out.println("=============================");
	}
	
	
	
	
	}