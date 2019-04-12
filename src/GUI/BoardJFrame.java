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

// TODO: Auto-generated Javadoc
/**
 * The Class BoardJFrame is a JFrame that contains the JPanel.
 */
public class BoardJFrame extends JFrame {
	
	/** The instantiation of letter GUI. */
	LetterGUI letterGUI = new LetterGUI();
	
	/** The matrix is for writing the multipliers of the game board  */
	String[][] matrix = new String[15][15];
	
	/** The name. */
	public String name;

	
	/**
	 * Instantiates a new boardJframe.
	 *
	 * @param name the name
	 */
	public BoardJFrame(String name) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Scrabble - Juego");
		setSize(1200, 775);
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(new BorderLayout());
		initializeTableTop();
		this.name = name;
		BoardGUI board = new BoardGUI(matrix,name);
		
		
		this.add(board);
	}


	/**
	 * Initialize TableTop which is matrix that contains the multipliers of the game table.
	 */
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
