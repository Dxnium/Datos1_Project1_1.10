package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import game.logic.*;



public class BoardGUI extends JPanel{
	String[][] matrix;
	LetterGUI letterGUI = new LetterGUI();
	ArrayList<LetterGUI> lettersList = new ArrayList<LetterGUI>();
	public BoardGUI(String[][] matrix){
		this.matrix = matrix;
		setVisible(true);
		setBounds(0, 0, 1200, 800);
		setLayout(null);

		this.letterGUI = new LetterGUI("A",1);
		lettersList.add(letterGUI);
		this.add(letterGUI);
		this.letterGUI = new LetterGUI("B",2);
		lettersList.add(letterGUI);
		this.add(letterGUI);
		this.letterGUI = new LetterGUI("C",3);
		lettersList.add(letterGUI);
		this.add(letterGUI);
		this.letterGUI = new LetterGUI("D",4);
		lettersList.add(letterGUI);
		this.add(letterGUI);


	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);



		g.setColor(Color.BLACK);
		for(int f=0; f < 15; f++){
			for(int c =0;c< 15;c++) {
				Shape rect = new Rectangle((f*50),(c*50),50,50);
				g.drawRect((f*50),(c*50),50,50);
				for(int p = 0; p<lettersList.size(); p++) {
					if(rect.contains(lettersList.get(p).getX(),lettersList.get(p).getY())) {
						lettersList.get(p).setLocation((f*50),(c*50));
					}
				}

			}

		}
		for(int f=1; f <= 15; f++) {
			for(int c =1;c<= 15;c++) {

				g.setColor(Color.BLUE);
				g.drawString( matrix[c-1][f-1],(f*50)-25,(c*50)-25);
				

			}
		}

	}

}
