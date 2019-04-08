package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import game.logic.*;



public class BoardGUI extends JPanel{
	String[][] matrix;
	public BoardGUI(String[][] matrix){
		this.matrix = matrix;
		setVisible(true);
		setBounds(0, 0, 1200, 800);
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		
		
		
		g.setColor(Color.BLACK);
		for(int f=0; f < 15; f++){
			for(int c =0;c< 15;c++) {
				g.drawRect((f*50),(c*50),50,50);
				
				
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
