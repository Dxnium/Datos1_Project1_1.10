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
			
				if(letter.equals(alphabet.get(i))) {
					setIcon(alphabetIMG.get(alphabet.indexOf(alphabet.get(i))));
					setBounds(700+((x*50)), 100, 50, 50);
					
				}
			}
			addMouseMotionListener(this);
			/*ImageIcon letterImg = new ImageIcon("Images\\A.PNG");
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
