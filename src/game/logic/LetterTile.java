package game.logic;

// TODO: Auto-generated Javadoc
/**
 * Class that will be used as a game tile, contains a string attribute with a letter, the score linked to that letter and the amount of 
 * tiles of the same kind available as arguments
 */
public class LetterTile {/** The letter. */
//
	private String letter;//
	
	/** The score. */
	private int score;
	
	/** The amount. */
	private int amount;
	
	/** The played on turn. */
	private int playedOnTurn;
	
	/** The is conected. */
	private boolean isConected;
	
	/**
	 * Checks if is conected.
	 *
	 * @return true, if is conected
	 */
	public boolean isConected() {
		return isConected;
	}

	/**
	 * Sets the conected.
	 *
	 * @param isConected the new conected
	 */
	public void setConected(boolean isConected) {
		this.isConected = isConected;
	}

	/**
	 * Gets the letter.
	 *
	 * @return the letter
	 */
	public String getLetter() {
		return letter;
	}
	
	/**
	 * Sets the letter.
	 *
	 * @param letter the new letter
	 */
	public void setLetter(String letter) {
		this.letter = letter;
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
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	/**
	 * Instantiates a new letter tile.
	 *
	 * @param letter the letter
	 * @param score the score
	 * @param amount the amount
	 */
	public LetterTile(String letter, int score,int amount) {
		this.letter = letter;
		this.score = score;
		this.setAmount(amount);
	}

	/**
	 * Gets the played on turn.
	 *
	 * @return the played on turn
	 */
	public int getPlayedOnTurn() {
		return playedOnTurn;
	}

	/**
	 * Sets the played on turn.
	 *
	 * @param playedOnTurn the new played on turn
	 */
	public void setPlayedOnTurn(int playedOnTurn) {
		this.playedOnTurn = playedOnTurn;
	}

	

	
}

