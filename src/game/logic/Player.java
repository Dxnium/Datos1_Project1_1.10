package game.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Player {//The player class has key attributes for game logic, such as the name of the player (graphical identifier), an array dock which will serve 
							//as storage for the tiles that belong to the player, and the player score.
	
	private String name;
	private  LetterTile[] dock = new LetterTile[7];
	static BufferedReader in= new BufferedReader (new InputStreamReader(System.in));
	static PrintStream out=System.out;
	int score;
	int myTiles;
	
	
	public int getMyTiles() {
		return myTiles;
	}
	public void setMyTiles(int myTiles) {
		this.myTiles = myTiles;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LetterTile[] getDock() {
		return dock;
	}
	
	public Player(String name) {
		this.name = name;
	}
	
	
	}


