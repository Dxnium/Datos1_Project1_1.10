package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import game.logic.*;



public class BoardGUI extends JPanel{
	Square[][] matrix;
	public BoardGUI(Square[][] matrix){
		this.matrix = matrix;
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		
		
		
		g.setColor(Color.BLACK);
		for(int f=0; f < 15; f++){
			for(int c =0;c< 15;c++) {
				g.drawRect((f*36),(c*36),36,36);
				
				
			}
			
		}
		for(int f=1; f <= 15; f++) {
			for(int c =1;c<= 15;c++) {
				
				g.setColor(Color.BLUE);
				g.drawString( matrix[c-1][f-1].getMultiplier().toString() ,(f*36)-17,(c*36)-17);
						
			}
		}
		
	}
	
}
