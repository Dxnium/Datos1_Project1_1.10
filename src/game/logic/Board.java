package game.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Board {//Manages most of the board related methods
	private String gameCode;
	private Square[][] tableTop = new Square[15][15];
	private PlayerLinkedList playerList=new PlayerLinkedList();
	private LetterTile[] deck = new LetterTile[29];
	int deckSize=100;
	private Dictionary dictionary = new Dictionary();
	private int turn;
	private PlayedWordList playedWords=new PlayedWordList();
	//hola
	
	
	

	//GETTERS AND SETTERS
	public Dictionary getDictionary() {
		return dictionary;
	}
	public LetterTile[] getDeck() {
		return deck;
	}
	public Square[][] getTableTop() {
		return tableTop;
	}
	public void setTableTop(Square[][] tableTop) {
		this.tableTop = tableTop;
	}

	
	public PlayerLinkedList getPlayerList() {
		return playerList;
	}
	
	public int getDeckSize() {
		return deckSize;
	}
	
	public void setDeckSize(int deckSize) {
		this.deckSize = deckSize;
	}
	
	public String getGameCode() {
		return gameCode;
	}
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}
	
	public PlayedWordList getPlayedWords() {
		return playedWords;
	}
	public void setPlayedWords(PlayedWordList playedWords) {
		this.playedWords = playedWords;
	}
	
	
	//GAME LOGIC METHODS
	
	
	
	
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public void initializeTableTop() {//reads from a .txt file on project root, parses a document that contains the board multiplier values and places them
		BufferedReader reader = null;//on a Square object thats positioned on the matrix contained in the tableTop attribute
		try {
			reader= new BufferedReader(new FileReader("TableTop.txt"));

			String line;
			int row = 0;
			while((line = reader.readLine()) != null) {
				String lineArray[] = line.split(",");
				for(int x=0; x<lineArray.length;x++) {
					this.tableTop[row][x]= new Square(Integer.parseInt(lineArray[x]));
				}
				row++;
					
				
			}
			System.out.println("Tabletop Initialized");
			
		} catch (IOException error){
			System.out.println("Error de lectura");
			error.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException closingError) {
				System.out.println("Error al crear diccionario");

			}
		}
	}
	

	public void InitializeDeck(){//this method parses the .txt file containing the valid words to use and appends one DictionaryLinkedListNode
		BufferedReader reader = null;//that contains a single word on its data attribute to the linked list on the attribute wordBook of this class.
		try {
			reader= new BufferedReader(new FileReader("LetterTiles.txt"));
			
			int index=0;
			String line;
			String lineArray[];
			while((line = reader.readLine()) != null) {
				lineArray = line.split(",");
				this.deck[index]=new LetterTile(lineArray[0],Integer.parseInt(lineArray[1]),Integer.parseInt(lineArray[2]));
				index++;
			}
			
			System.out.println("Deck Initialized");
		} catch (IOException error){
			System.out.println("Error de lectura");
		} finally {
			try {
				reader.close();
			} catch (IOException closingError) {
				System.out.println("Error al crear diccionario");

			}
		}
	}
	
	public void generateGameCode() throws IOException {
		BufferedReader reader = null;
		reader= new BufferedReader(new FileReader("characters.txt"));
		int count=0;
		String line;
		String letterArray[]= new String[26];
		String numberArray[]= new String[10];
		while((line = reader.readLine()) != null) {
			if(count==0) {
				letterArray = line.split(",");
			}else{
				numberArray=line.split(",");
			}
			count++;
		}
		reader.close();
		String GameCode="";
		int amount=0;
		while(amount<6) {
			if(amount<3) {
				int randomLetter = ThreadLocalRandom.current().nextInt(1, 27);
				GameCode=GameCode+letterArray[randomLetter];
			}else{
				int randomNum = ThreadLocalRandom.current().nextInt(1, 11);
				GameCode=GameCode+numberArray[randomNum];
			}
			amount++;
		}
		System.out.println(GameCode);
		this.setGameCode(GameCode);
	}
}
