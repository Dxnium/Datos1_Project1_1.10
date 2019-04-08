package GUI;

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
				Image labelImg = new ImageIcon(this.getClass().getResource("/img.png")).getImage(); 
				
				//Set layout manager
				Ventana2.setLayout(null);
				
				
				//swing component 
				JLabel serverLabel = new JLabel("");
				serverLabel.setBounds(0, 0, 1720, 980);
				serverLabel.setIcon(new ImageIcon(labelImg));
				
				
				//add swing component to content pane 
				//add swing component to content pane 
				Container c = Ventana2.getContentPane();
				c.add(serverLabel);
			}});
	}
}

