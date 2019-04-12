package game.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

// TODO: Auto-generated Javadoc
/**
 * The Class Board.
 */
public class Board {/** The game code. */
//Manages most of the board related methods
	private String gameCode;
	
	/** The table top. */
	private Square[][] tableTop = new Square[15][15];
	
	/** The player list. */
	private PlayerLinkedList playerList=new PlayerLinkedList();
	
	/** The deck. */
	private LetterTile[] deck = new LetterTile[29];
	
	/** The deck size. */
	int deckSize=100;
	
	/** The dictionary. */
	private Dictionary dictionary = new Dictionary();
	
	/** The turn. */
	private int turn;
	
	/** The max players. */
	private int maxPlayers;
	
	/** The current conection. */
	private int currentConection;
	
	/** The played words. */
	private PlayedWordList playedWords=new PlayedWordList();
	//hola
	
	
	
	/**
	 * Gets the current conection.
	 *
	 * @return the current conection
	 */
	public int getCurrentConection() {
		return currentConection;
	}
	
	/**
	 * Sets the current conection.
	 */
	public void setCurrentConection() {
		this.currentConection = currentConection+1;
	}
	
	/**
	 * Gets the max players.
	 *
	 * @return the max players
	 */
	public int getMaxPlayers() {
		return maxPlayers;
	}
	
	/**
	 * Sets the max players.
	 *
	 * @param maxPlayers the new max players
	 */
	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
	
	/**
	 * Gets the dictionary.
	 *
	 * @return the dictionary
	 */
	//GETTERS AND SETTERS
	public Dictionary getDictionary() {
		return dictionary;
	}
	
	/**
	 * Gets the deck.
	 *
	 * @return the deck
	 */
	public LetterTile[] getDeck() {
		return deck;
	}
	
	/**
	 * Gets the table top.
	 *
	 * @return the table top
	 */
	public Square[][] getTableTop() {
		return tableTop;
	}
	
	/**
	 * Sets the table top.
	 *
	 * @param tableTop the new table top
	 */
	public void setTableTop(Square[][] tableTop) {
		this.tableTop = tableTop;
	}

	
	/**
	 * Gets the player list.
	 *
	 * @return the player list
	 */
	public PlayerLinkedList getPlayerList() {
		return playerList;
	}
	
	/**
	 * Gets the deck size.
	 *
	 * @return the deck size
	 */
	public int getDeckSize() {
		return deckSize;
	}
	
	/**
	 * Sets the deck size.
	 *
	 * @param deckSize the new deck size
	 */
	public void setDeckSize(int deckSize) {
		this.deckSize = deckSize;
	}
	
	/**
	 * Gets the game code.
	 *
	 * @return the game code
	 */
	public String getGameCode() {
		return gameCode;
	}
	
	/**
	 * Sets the game code.
	 *
	 * @param gameCode the new game code
	 */
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}

	/**
	 * Gets the played words.
	 *
	 * @return the played words
	 */
	public PlayedWordList getPlayedWords() {
		//getter
		return playedWords;
	}
	
	/**
	 * Sets the played words.
	 *
	 * @param playedWords the new played words
	 */
	public void setPlayedWords(PlayedWordList playedWords) {
		//setter
		this.playedWords = playedWords;
	}
	
	//GAME LOGIC METHODS
	
	
	
	
	/**
	 * Gets the turn.
	 *
	 * @return the turn
	 */
	public int getTurn() {
		return turn;
	}
	
	/**
	 * Sets the turn.
	 *
	 * @param turn the new turn
	 */
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	/**
	 * Initialize table top.
	 */
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
	

	/**
	 * Initialize deck.
	 */
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
	
	/**
	 * Generate game code.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
