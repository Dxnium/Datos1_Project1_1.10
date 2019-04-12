package GUI;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LetterGUI extends JLabel implements MouseMotionListener,MouseListener{
	ArrayList<String> alphabet = new ArrayList<String>();
	ArrayList<ImageIcon> alphabetIMG = new ArrayList<ImageIcon>();
	String letterAsigned;
	String posC;
	String posF;
	Boolean canBeRepositioned = true;
	int posInicialX;
	int posInicialY;
	ArrayList<Shape> fichasPosList = new ArrayList<Shape>();
	Boolean alreadyPlaced = false;
	int cont=0;
	public int posDeck;

	public int getPosInicialX() {
		return posInicialX;
	}
	public void setPosInicialX(int posInicialX) {
		this.posInicialX = posInicialX;
	}
	public int getPosInicialY() {
		return posInicialY;
	}
	public void setPosInicialY(int posInicialY) {
		this.posInicialY = posInicialY;
	}
	public String getPosC() {
		return posC;
	}
	public void setPosC(String posC) {
		this.posC = posC;
	}
	public String getPosF() {
		return posF;
	}
	public void setPosF(String posF) {
		this.posF = posF;
	}
	public String getLetterAsigned() {
		return letterAsigned;
	}
	public void setLetterAsigned(String letterAsigned) {
		this.letterAsigned = letterAsigned;
	}
	public LetterGUI() {

	}
	public LetterGUI(String letter, int x){
		this.posDeck = x;
		alphabetFill();
		alphabetIMGFill();
		this.setLetterAsigned(letter);
		for(int i= 0;i<alphabet.size();i++) {

			if(letter.equals(alphabet.get(i))) {
				setIcon(alphabetIMG.get(alphabet.indexOf(alphabet.get(i))));
				setBounds(700+((x*50)), 100, 50, 50);
				this.setPosInicialX(this.getX());
				this.setPosInicialY(this.getY());

			}
		}
		addMouseMotionListener(this);
		addMouseListener(this);

	}
	public LetterGUI(String letter, Integer posX, Integer posY){
		this.canBeRepositioned = false;
		alphabetFill();
		alphabetIMGFill();
		this.setLetterAsigned(letter);
		for(int i= 0;i<alphabet.size();i++) {

			if(letter.equals(alphabet.get(i))) {
				setIcon(alphabetIMG.get(alphabet.indexOf(alphabet.get(i))));
				setBounds(posX, posY, 50, 50);
				this.setPosC(Integer.toString(posY/50));
				this.setPosF(Integer.toString(posX/50));
				Shape rect2 = new Rectangle(posX, posY,50,50);
				fichasPosList.add(rect2);

			}
		}
		addMouseMotionListener(this);
		addMouseListener(this);





	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
		
		if(this.canBeRepositioned == true) {
			setLocation(this.getX()+e.getX()-this.getWidth()/2,this.getY()+e.getY()-this.getHeight()/2);
		}
		if(this.getX() <= 0 ){
			setLocation(0,this.getY()+e.getY()-this.getHeight()/2);
		}
		if(this.getY() <= 0) {
			setLocation(this.getX()+e.getX()-this.getWidth()/2,0);
		}
		if(this.getX() >= 1100) {
			setLocation(1100,this.getY()+e.getY()-this.getHeight()/2);
		}
		if( this.getY() >= 800) {
			setLocation(this.getX()+e.getX()-this.getWidth()/2,800);
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Shape rect2 = new Rectangle(Integer.parseInt(this.getPosF())*50, Integer.parseInt(this.getPosC())*50,50,50);
		fichasPosList.add(rect2);
		System.out.println(fichasPosList.toString());
		for(int i = 0; i< fichasPosList.size();i++) {
			Shape valueToCompare= fichasPosList.get(i).getBounds();
			for(int i2 = 0; i2< fichasPosList.size();i2++){
				
				if(valueToCompare.equals(fichasPosList.get(i2).getBounds()) && i != i2) {
					
					cont++;
				}
				
				if(cont == 2){
					cont = 0;
					System.out.println("Holiwix");
					//System.out.println("C"+this.getPosC());
					//System.out.println("F"+this.getPosF());	
					if(this.canBeRepositioned == true) {
						setLocation(this.getPosInicialX(),this.getPosInicialY());
						fichasPosList.remove(i2);
					}
					
				}else {
					System.out.println("You are free to goo");
					
				}
			}

		}

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent e) {


	}
	@Override
	public void mousePressed(MouseEvent e) {

		if(this.canBeRepositioned == true) {
			setLocation(this.getX()+e.getX()-this.getWidth()/2,this.getY()+e.getY()-this.getHeight()/2);
		}
		if(this.getX() <= 0 ){
			setLocation(0,this.getY()+e.getY()-this.getHeight()/2);
		}
		if(this.getY() <= 0) {
			setLocation(this.getX()+e.getX()-this.getWidth()/2,0);
		}
		if(this.getX() >= 1100) {
			setLocation(1100,this.getY()+e.getY()-this.getHeight()/2);
		}
		if( this.getY() >= 800) {
			setLocation(this.getX()+e.getX()-this.getWidth()/2,800);
		}

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
		


	}
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
