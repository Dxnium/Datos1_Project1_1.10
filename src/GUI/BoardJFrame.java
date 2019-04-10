package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import game.logic.Square;

public class BoardJFrame extends JFrame {
	LetterGUI letterGUI = new LetterGUI();
	String[][] matrix = new String[15][15];
	

	ArrayList<LetterGUI> lettersList = new ArrayList<LetterGUI>();
	public BoardJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Scrabble - Juego");
		setSize(1200, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(new BorderLayout());
		initializeTableTop();
		BoardGUI board = new BoardGUI(matrix);
		
		this.add(board);
	}


	public void initializeTableTop() {//reads from a .txt file on project root, parses a document that contains the board multiplier values and places them
		BufferedReader reader = null;//on a Square object thats positioned on the matrix contained in the tableTop attribute
		try {
			reader= new BufferedReader(new FileReader("TableTop.txt"));

			String line;
			int row = 0;
			while((line = reader.readLine()) != null) {
				String lineArray[] = line.split(",");
				for(int x=0; x<lineArray.length;x++) {
					matrix[row][x] = lineArray[x];
				}
				row++;


			}
			System.out.println("Tabletop Initialized");
			
		

		} catch (IOException error){
			System.out.println("Error de lectura");
			error.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException closingError) {
				System.out.println("Error al crear diccionario");

			}
		}
	}
	
}
