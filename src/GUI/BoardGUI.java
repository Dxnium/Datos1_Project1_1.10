package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.json.simple.JSONArray;

import JSON.Decode;
import JSON.Encode;
import Msg.Message;
import Sockets.Cliente;



// TODO: Auto-generated Javadoc
/**
 * The Class BoardGUI is a JPanel that contains all the graphical elements created.
 */
public class BoardGUI extends JPanel{
	
	/** The my name. */
	public String myName;
	
	/** An array of strings that contains letters of the player's deck */
	String[] titles;
	
	/** The matrix that contains the multiplier of the */
	String[][] matrix;
	
	/** An instantiation of letterGUI class. */
	LetterGUI letterGUI = new LetterGUI();
	
	/** The letters list that contains objects of the class LetterGUI */
	ArrayList<LetterGUI> lettersList = new ArrayList<LetterGUI>();
	
	/** The labelImg contains an ImageIcon. */
	Image labelImg = new ImageIcon("Images\\img.png").getImage(); 
	
	/** The reglasImg contains an ImageIcon. */
	Image reglasImg = new ImageIcon("Images\\botonAyuda.png").getImage();





	
	/** The cliente. */
	Cliente cliente;
	
	/** The msg. */
	Message msg = new Message("");
	
	/** The encode. */
	Encode encode;

	/**
	 * Instantiates a new boardGUI that contains the game board where you can put tiles.
	 *
	 * @param matrix the matrix
	 * @param name the name
	 */

	public BoardGUI(String[][] matrix,String name){
		this.myName = name;
		botonReglas reglas = new botonReglas();
		reglas.setBounds(1000,10,167,59);
		reglas.setIcon(new ImageIcon(reglasImg));
		this.add(reglas);

		JButton btnScrabble = new JButton();
		btnScrabble.setIcon(new ImageIcon("Images\\scrabble.png"));
		btnScrabble.setBounds(1000, 500, 167, 59);
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
				if(cliente.msg.contains("MatrizJson")) {
					StringWriter toJson = new StringWriter();
					toJson = toJson.append(cliente.msg, 0,cliente.msg.length());
					Decode decode = new Decode(toJson);
					titles = decode.titles.split(",");
					crearTitles(titles);
				}
			}});
		this.add(btnConsulta);
		//--------------------------------------------------------------------------
		this.add(btnScrabble);
		JButton btnNext = new JButton();
		btnNext.setIcon(new ImageIcon("Images\\pasar.png"));
		btnNext.setBounds(1000, 600, 167, 59);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}});
		this.add(btnNext);
		this.matrix = matrix;
		setVisible(true);
		setBounds(0, 0, 1200, 800);
		setLayout(null);



		//		this.letterGUI = new LetterGUI("A",1);
		//		lettersList.add(letterGUI);
		//		this.add(letterGUI);
		//		this.letterGUI = new LetterGUI("B",2);
		//		lettersList.add(letterGUI);
		//		this.add(letterGUI);
		//		this.letterGUI = new LetterGUI("C",3);
		//		lettersList.add(letterGUI);
		//		this.add(letterGUI);
		//		this.letterGUI = new LetterGUI("D",4);
		//		lettersList.add(letterGUI);
		//		this.add(letterGUI);
		//		this.letterGUI = new LetterGUI("RR",300,450);
		//		this.add(letterGUI);

	}
	
	/**
	 * Gets the words location on the screen and on the matrix.
	 *
	 * @return the words location
	 */
	public void getWordsLocation() {
		String[][] fichas = new String[8][3];
		for(int i = 1; i < 8 ;i++) {
			if(lettersList.get(i).getPosF()!=null) {
				System.out.println(i);
				fichas[i][0] = Integer.toString(lettersList.get(i).posDeck);
				fichas[i][1] = lettersList.get(i).posF;
				fichas[i][2] = lettersList.get(i).getPosC();
			}
			}
			fichas[0][0] = this.myName;
			System.out.println(Arrays.deepToString(fichas));
			this.msg.setMatrizdoble(fichas);
			StringBuilder builder = new StringBuilder();
			for(String[] s : fichas) {
				for(String i : s) {
			    builder.append(s);
			}
			}
			String str = builder.toString();
			System.out.println(Arrays.deepToString(fichas)+"Writer");
			this.cliente = new Cliente("localhost", "posicionLetras,"+Arrays.deepToString(fichas));
			
			//System.out.println("Objeto: "+lettersList.get(i)+"\n"+"Letra: "+lettersList.get(i).getLetterAsigned()+"\n"+"PosX: "+lettersList.get(i).getX()+"\n"+"PosY: "+lettersList.get(i).getY());
//			System.out.println("Columna: "+lettersList.get(i).getPosC()+"\n"+"Fila: "+lettersList.get(i).getPosF()+"\n"+"Letra: "+lettersList.get(i).getLetterAsigned()+"\n"+"PosX: "+lettersList.get(i).getX()+"\n"+"PosY: "+lettersList.get(i).getY());
		}
	
	/**
	 * CrearTitles subtract of a json message the letter that will be assigned to the tile.
	 *
	 * @param titles the titles
	 */
	public void crearTitles(String[] titles) {
		for(int i=1;i<8;i++) {
			System.out.println(Arrays.deepToString(titles));
			if(titles[i].length()>3) {
				if(titles[i].contains("]")) {
					System.out.println(titles[i].substring(1,2));
					this.letterGUI = new LetterGUI(titles[i].substring(1, 2),i);
					lettersList.add(letterGUI);
					this.add(letterGUI);
				}
				System.out.println(titles[i].substring(1,3));
				this.letterGUI = new LetterGUI(titles[i].substring(1, 3),i);
				lettersList.add(letterGUI);
				this.add(letterGUI);
			}else {
				System.out.println(titles[i].substring(1,2));
				this.letterGUI = new LetterGUI(titles[i].substring(1, 2),i);
				lettersList.add(letterGUI);
				this.add(letterGUI);
			}
		}this.repaint();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
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
						for(int j= 0;j<lettersList.size();j++) {
							for(int n =0;n<lettersList.size();n++) {
							
								
								if(!lettersList.get(n).getPosC().equals("19") && !lettersList.get(n).getPosF().equals("19")) {
									if(lettersList.get(j).getPosC().equals(lettersList.get(n).getPosC()) && lettersList.get(j).getPosF().equals(lettersList.get(n).getPosF()) && j != n) {
										lettersList.get(n).setLocation(lettersList.get(n).getPosInicialX(),lettersList.get(n).getPosInicialY());
										lettersList.get(n).setPosC("19");
										lettersList.get(n).setPosF("19");
									}
								}
							}

						}


					}
					else {

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
