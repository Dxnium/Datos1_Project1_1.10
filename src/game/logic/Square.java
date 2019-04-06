package game.logic;

public class Square {
	

	private LetterTile letter;
	private Integer multiplier;

	private LetterTile letterTile;


	private boolean activeFlag = false;
	
	public Square(int multiplier){
		if ( multiplier != 0) {
			this.setActiveFlag(true);
		}
		this.multiplier = multiplier;
		
	}
	
	public LetterTile getLetterTile() {
		return letterTile;
	}
	public void setLetterTile(LetterTile letter) {
		this.letterTile = letter;
	}
	public Integer getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	public boolean isActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}
	

}
