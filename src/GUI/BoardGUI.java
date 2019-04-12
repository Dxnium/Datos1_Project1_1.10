package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.sun.security.ntlm.Client;

import Sockets.Cliente;



public class BoardGUI extends JPanel{
	public String myName;
	String[][] matrix;
	LetterGUI letterGUI = new LetterGUI();
	ArrayList<LetterGUI> lettersList = new ArrayList<LetterGUI>();
	Image labelImg = new ImageIcon("Images\\img.png").getImage(); 
	Image reglasImg = new ImageIcon("Images\\botonAyuda.png").getImage();
	ArrayList<letterOnMatrix> letterPosList = new ArrayList<letterOnMatrix>();
	

	public BoardGUI(String[][] matrix){
		
		botonReglas reglas = new botonReglas();
		reglas.setBounds(1000,10,128,128);
		reglas.setIcon(new ImageIcon(reglasImg));
		this.add(reglas);

		JButton btnScrabble = new JButton("Scrabble");
		btnScrabble.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnScrabble.setBounds(1000, 500, 102, 31);
		btnScrabble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getWordsLocation();
			}});
		//------------------------------------------------------------------------
		JButton btnConsulta = new JButton("Consulta");
		btnConsulta.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConsulta.setBounds(1000, 400, 102, 31);
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("consulta");
				Cliente cliente = new Cliente("localhost","checkTurno,"+ myName);
			}});
		this.add(btnConsulta);
		//--------------------------------------------------------------------------
		this.add(btnScrabble);
		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNext.setBounds(1000, 600, 102, 31);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}});
		this.add(btnNext);
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
		this.letterGUI = new LetterGUI("RR",300,450);
		this.add(letterGUI);

	}
	public void getWordsLocation() {
		for(int i = 0; i < lettersList.size();i++) {
			//System.out.println("Objeto: "+lettersList.get(i)+"\n"+"Letra: "+lettersList.get(i).getLetterAsigned()+"\n"+"PosX: "+lettersList.get(i).getX()+"\n"+"PosY: "+lettersList.get(i).getY());
			System.out.println("Columna: "+lettersList.get(i).getPosC()+"\n"+"Fila: "+lettersList.get(i).getPosF()+"\n"+"Letra: "+lettersList.get(i).getLetterAsigned()+"\n"+"PosX: "+lettersList.get(i).getX()+"\n"+"PosY: "+lettersList.get(i).getY());
		}
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
						lettersList.get(p).setPosC(Integer.toString(f));
						lettersList.get(p).setPosF(Integer.toString(c));	
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
