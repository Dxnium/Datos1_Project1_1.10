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
	Image labelImg = new ImageIcon("Images\\img.png").getImage(); 
	Image reglasImg = new ImageIcon("Images\\ayuda.png").getImage();
	
	public BoardGUI(String[][] matrix){
		botonReglas reglas = new botonReglas();
		reglas.setBounds(1000,10,128,128);
		reglas.setIcon(new ImageIcon(reglasImg));
		this.add(reglas);
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
		for(int f=0; f < 15; f++) {
			for(int c =0;c< 15;c++) {

				if(matrix[c][f].equals("1")) {
					g.drawImage(new ImageIcon("Images\\square.png").getImage(), f*50,c*50, null);
					
				}else if(matrix[c][f].equals("2")) {
					g.drawImage(new ImageIcon("Images\\square2.png").getImage(), f*50,c*50, null);
					
				}else if(matrix[c][f].equals("3")) {
					g.drawImage(new ImageIcon("Images\\square3.png").getImage(), f*50,c*50, null);
					
				}
								

			}
		}

	}

}