package graphics;

import java.awt.Color;
import java.awt.Container;

import javax.swing.*;

public class Myframe extends JFrame{
	Myframe(){
		setTitle("Primer Ventana");
		setBounds(300, 100, 300, 300);
		setVisible(true);
		Container contentpane = getContentPane();
		
		JLabel texto1 = new JLabel("Hola");
		
	}

}

