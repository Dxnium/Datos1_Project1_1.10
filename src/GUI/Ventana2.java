package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Ventana2 extends JFrame {
	private boolean active = false;
	private int Xsize = 1920;
	private int Ysize = 1080;
	
	public Ventana2() {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame Ventana2 = new JFrame("Scrabble Ventana2");
				Ventana2.setSize(Xsize,Ysize);
				Ventana2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Ventana2.setVisible(true);
				Ventana2.setLocationRelativeTo(null);
				Image labelImg = new ImageIcon("Images\\img.png").getImage(); 
				Image reglasImg = new ImageIcon("Images\\ayuda.png").getImage();
				
				//Set layout manager
				Ventana2.setLayout(null);
				
				
				//swing component 
				JLabel serverLabel = new JLabel("");
				botonReglas reglas = new botonReglas();
				reglas.setBounds(0,0,128,128);
				serverLabel.setBounds(0, 0, 1720, 980);
				reglas.setIcon(new ImageIcon(reglasImg));
				serverLabel.setIcon(new ImageIcon(labelImg));

				//add swing component to content pane 
				//add swing component to content pane 
				Container c = Ventana2.getContentPane();
				c.add(serverLabel);
				c.add(reglas);
			}});
	}
}

