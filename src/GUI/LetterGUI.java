package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LetterGUI extends JLabel implements MouseMotionListener{
		ArrayList<String> alphabet = new ArrayList<String>();
		ArrayList<ImageIcon> alphabetIMG = new ArrayList<ImageIcon>();
		public LetterGUI() {
		
		}
		public LetterGUI(String letter, int x){
			alphabetFill();
			alphabetIMGFill();
			for(int i= 0;i<alphabet.size();i++) {
				if(letter.equals("A")) {
					setIcon(alphabetIMG.get(alphabet.indexOf(alphabet.get(0))));
					setBounds(900+((x*50)), 100, 50, 50);
				}
				if(letter.equals(alphabet.get(i))) {
					setIcon(alphabetIMG.get(alphabet.indexOf(alphabet.get(i))));
					setBounds(700+((x*50)), 100, 50, 50);
					
				}
			}
			addMouseMotionListener(this);
			/*ImageIcon letterImg = new ImageIcon("Letras\\A.PNG");
			setIcon(letterImg);
			setBounds(700, 100, 30, 30);*/
			
			
			
		
	}

		@Override
		public void mouseDragged(MouseEvent e) {
			setLocation(this.getX()+e.getX()-this.getWidth()/2,this.getY()+e.getY()-this.getHeight()/2);
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		public void alphabetFill() {
			alphabet.add("A");
			alphabet.add("B");
			alphabet.add("C");
			alphabet.add("D");
			alphabet.add("E");
			alphabet.add("F");
			alphabet.add("G");
			alphabet.add("H");
			alphabet.add("I");
			alphabet.add("J");
			alphabet.add("K");
			alphabet.add("L");
			alphabet.add("N");
			alphabet.add("Ñ");
			alphabet.add("O");
			alphabet.add("P");
			alphabet.add("Q");
			alphabet.add("R");
			alphabet.add("S");
			alphabet.add("T");
			alphabet.add("U");
			alphabet.add("V");
			alphabet.add("W");
			alphabet.add("X");
			alphabet.add("Y");
			alphabet.add("Z");
		}
		public void alphabetIMGFill() {
			alphabetIMG.add(new ImageIcon("Letras\\A.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\B.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\C.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\D.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\E.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\F.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\G.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\H.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\I.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\J.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\K.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\L.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\N.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\Ñ.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\O.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\P.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\Q.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\R.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\S.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\T.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\U.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\V.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\W.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\X.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\Y.PNG"));
			alphabetIMG.add(new ImageIcon("Letras\\Z.PNG"));
		}

}
