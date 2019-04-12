package game.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

// TODO: Auto-generated Javadoc
/**
 * The Class Player.
 */
public class Player {//The player class has key attributes for game logic, such as the name of the player (graphical identifier), an array dock which will serve 
							//as storage for the tiles that belong to the player, and the player score.
	
	/** The name. */
private String name;
	
	/** The dock. */
	private  LetterTile[] dock = new LetterTile[7];
	
	/** The in. */
	static BufferedReader in= new BufferedReader (new InputStreamReader(System.in));
	
	/** The out. */
	static PrintStream out=System.out;
	
	/** The score. */
	int score;
	
	/** The my tiles. */
	int myTiles;
	
	
	/**
	 * Gets the my tiles.
	 *
	 * @return the my tiles
	 */
	public int getMyTiles() {
		return myTiles;
	}
	
	/**
	 * Sets the my tiles.
	 *
	 * @param myTiles the new my tiles
	 */
	public void setMyTiles(int myTiles) {
		this.myTiles = myTiles;
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the dock.
	 *
	 * @return the dock
	 */
	public LetterTile[] getDock() {
		return dock;
	}
	
	/**
	 * Instantiates a new player.
	 *
	 * @param name the name
	 */
	public Player(String name) {
		this.name = name;
	}
	
	
	}
