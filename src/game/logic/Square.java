package game.logic;

// TODO: Auto-generated Javadoc
/**
 * The class square is an abstraction of a scrabble board placement space. 
 * square objects are used as fillers for the tabletop rows, these objects store a desired LetterTile object and have a set attribute of Integer type
 * called multiplier. The object then allows for an easy logical operation in which the value of the LetterTile's attribute "score" is increased times the
 * multiplier value. Additionally the class has a boolean attribute called "activeFlag", which indicates if the multiplier has been used (true for unused, false for used).
 */
public class Square {
	

	/** The multiplier. */
	private Integer multiplier;

	/** The letter tile. */
	private LetterTile letterTile;


	/** The active flag. */
	private boolean activeFlag = false;
	
	/**
	 * Instantiates a new square.
	 *
	 * @param multiplier the multiplier
	 */
	public Square(int multiplier){
		if ( multiplier != 0) {
			this.setActiveFlag(true);
		}
		this.multiplier = multiplier;
		
	}
	
	/**
	 * Gets the letter tile.
	 *
	 * @return the letter tile
	 */
	public LetterTile getLetterTile() {
		return letterTile;
	}
	
	/**
	 * Sets the letter tile.
	 *
	 * @param letter the new letter tile
	 */
	public void setLetterTile(LetterTile letter) {
		this.letterTile = letter;
	}
	
	/**
	 * Gets the multiplier.
	 *
	 * @return the multiplier
	 */
	public Integer getMultiplier() {
		return multiplier;
	}
	
	/**
	 * Sets the multiplier.
	 *
	 * @param multiplier the new multiplier
	 */
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	
	/**
	 * Checks if is active flag.
	 *
	 * @return true, if is active flag
	 */
	public boolean isActiveFlag() {
		return activeFlag;
	}
	
	/**
	 * Sets the active flag.
	 *
	 * @param activeFlag the new active flag
	 */
	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}
	

}
