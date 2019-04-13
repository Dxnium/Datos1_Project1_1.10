package GUI;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class LetterGUI.
 * LetterGUI is the class in charge of defining the main characteristics such as position on screen, letter assigned
 * and position on matrix of a tile
 */
public class LetterGUI extends JLabel implements MouseMotionListener,MouseListener{
	//Atributos
	/**  The alphabet. */
	private ArrayList<String> alphabet = new ArrayList<String>();
	
	/** The alphabet images. */
	private ArrayList<ImageIcon> alphabetIMG = new ArrayList<ImageIcon>();
	
	/**  The letter assigned to the tile. */
	private String letterAssigned;
	
	/**  Column where the tile was placed. */
	protected String posC = "null";
	
	/**  Row where the tile was placed. */
	protected String posF = "null";


	
	/**  Indicates if the tile can be repositioned. */

	protected Boolean canBeRepositioned = true;
	
	/** The initial position in X. */
	protected int posInicialX;
	
	/** The initial position in Y. */
	protected int posInicialY;
	

	
	/** Position in Deck */
	public int posDeck;

	//Metodos publicos
	
	/**
	 * Gets the initial position X.
	 *
	 * @return the initial position X
	 */
	public int getPosInicialX() {
		return posInicialX;
	}
	
	/**
	 * Sets the initial position X.
	 *
	 * @param posInicialX the new initial position X
	 */
	public void setPosInicialX(int posInicialX) {
		this.posInicialX = posInicialX;
	}
	
	/**
	 * Gets the initial position Y.
	 *
	 * @return the initial position Y
	 */
	public int getPosInicialY() {
		return posInicialY;
	}
	
	/**
	 * Sets the initial position Y.
	 *
	 * @param posInicialY the new initial position Y
	 */
	public void setPosInicialY(int posInicialY) {
		this.posInicialY = posInicialY;
	}
	
	/**
	 * Gets the position in Columns.
	 *
	 * @return the position in Columns
	 */
	public String getPosC() {
		return posC;
	}
	
	/**
	 * Sets the position in Columns.
	 *
	 * @param posC the new position in Columns
	 */
	public void setPosC(String posC) {
		this.posC = posC;
	}
	
	/**
	 * Gets the position in rows.
	 *
	 * @return the position in rows
	 */
	public String getPosF() {
		return posF;
	}
	
	/**
	 * Sets the position in rows.
	 *
	 * @param posF the new position in rows
	 */
	public void setPosF(String posF) {
		this.posF = posF;
	}
	
	/**
	 * Gets the letter assigned.
	 *
	 * @return the letter assigned
	 */
	public String getLetterAssigned() {
		return letterAssigned;
	}
	
	/**
	 * Sets the letter assigned.
	 *
	 * @param letterAsigned the new letter assigned
	 */
	public void setLetterAssigned(String letterAssigned) {
		this.letterAssigned = letterAssigned;
	}
	
	/**
	 * Instantiates a new letter GUI.
	 */
	public LetterGUI() {

	}


	String letter;
	/**
	 * Instantiates a new letter GUI.
	 *
	 * @param letter the letter
	 * @param x the x
	 */

	public LetterGUI(String letter, int x){
		this.letter = letter;
		this.posDeck = x;
		alphabetFill();
		alphabetIMGFill();
		this.setLetterAssigned(letter);
		for(int i= 0;i<alphabet.size();i++) {

			if(letter.equals(alphabet.get(i))) {
				setIcon(alphabetIMG.get(alphabet.indexOf(alphabet.get(i))));
				setBounds(700+((x*50)), 250, 50, 50);
				this.setPosInicialX(this.getX());
				this.setPosInicialY(this.getY());

			}
		}
		addMouseMotionListener(this);
		addMouseListener(this);

	}
	
	/**
	 * Instantiates a new letter GUI in a specific position and declares false in canBeReposition in order not to allow replacing the tile.
	 *
	 * @param letter the letter
	 * @param posX the pos X
	 * @param posY the pos Y
	 */
	public LetterGUI(String letter, Integer posX, Integer posY){
		this.canBeRepositioned = false;
		alphabetFill();
		alphabetIMGFill();
		this.setLetterAssigned(letter);
		for(int i= 0;i<alphabet.size();i++) {

			if(letter.equals(alphabet.get(i))) {
				setIcon(alphabetIMG.get(alphabet.indexOf(alphabet.get(i))));
				setBounds(posX, posY, 50, 50);
				this.setPosC(Integer.toString(posY/50));
				this.setPosF(Integer.toString(posX/50));
				

			}
		}
		addMouseMotionListener(this);
		addMouseListener(this);

	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		
		if(this.canBeRepositioned == true) {
			
			setLocation(this.getX()+e.getX()-this.getWidth()/2,this.getY()+e.getY()-this.getHeight()/2);
		}
		if(this.getX() <= 0 && this.canBeRepositioned == true){
			setLocation(0,this.getY()+e.getY()-this.getHeight()/2);
		}
		if(this.getY() <= 0 && this.canBeRepositioned == true) {
			setLocation(this.getX()+e.getX()-this.getWidth()/2,0);
		}
		if(this.getX() >= 1100 && this.canBeRepositioned == true) {
			setLocation(1100,this.getY()+e.getY()-this.getHeight()/2);
		}
		if( this.getY() >= 800 && this.canBeRepositioned == true) {
			setLocation(this.getX()+e.getX()-this.getWidth()/2,800);
		}
		

	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		


	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {


	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {




	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		
		
		System.out.println("Hola"+"La dejó en C:"+this.getPosC()+"y en F:"+this.getPosF());
		

	}
	
	/**
	 * AlphabetFill fills an array with the alphabet.
	 */

	public void alphabetFill() {
		alphabet.add("A");
		alphabet.add("B");
		alphabet.add("C");
		alphabet.add("CH");
		alphabet.add("D");
		alphabet.add("E");
		alphabet.add("F");
		alphabet.add("G");
		alphabet.add("H");
		alphabet.add("I");
		alphabet.add("J");
		alphabet.add("K");
		alphabet.add("L");
		alphabet.add("LL");
		alphabet.add("N");
		alphabet.add("M");
		alphabet.add("Ñ");
		alphabet.add("O");
		alphabet.add("P");
		alphabet.add("Q");
		alphabet.add("R");
		alphabet.add("RR");
		alphabet.add("S");
		alphabet.add("T");
		alphabet.add("U");
		alphabet.add("V");
		alphabet.add("W");
		alphabet.add("X");
		alphabet.add("Y");
		alphabet.add("Z");
		alphabet.add("BLANK");
	}
	
	/**
	 * AlphabetIMGFill fills an array with the alphabet images.
	 */
	public void alphabetIMGFill() {
		alphabetIMG.add(new ImageIcon("Images\\A.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\B.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\C.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\CH.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\D.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\E.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\F.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\G.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\H.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\I.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\J.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\K.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\L.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\LL.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\N.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\M.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\Ñ.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\O.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\P.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\Q.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\R.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\RR.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\S.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\T.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\U.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\V.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\W.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\X.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\Y.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\Z.PNG"));
		alphabetIMG.add(new ImageIcon("Images\\BLANK.PNG"));
	}


}
