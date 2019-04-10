package GUI;

import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import Sockets.Cliente;
import Sockets.GameServer;



public class StartWindow extends JFrame {
	private boolean active = true;
	private int Xsize = 1600;
	private int Ysize = 900;
	
	
	public StartWindow() {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame startFrame = new JFrame("Scrabble");
				startFrame.setSize(Xsize,Ysize);
				startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				startFrame.setVisible(true);
				startFrame.setLocationRelativeTo(null);
				Image fondoImg = new ImageIcon("Images\\Fondo.JPG").getImage();
			
				//Set layout manager
				startFrame.setLayout(null);
				
				
		
				//swing component 
				JLabel fondo=new JLabel("");
				fondo.setBounds(0, 0, 1600, 900);
				fondo.setIcon(new ImageIcon(fondoImg));
				JButton buttonClient = new JButton("Cliente");
				buttonClient.setBounds(560, 540, 200, 40);
				JButton buttonServer = new JButton("Server");
				buttonServer.setBounds(1260, 540, 200, 40);
				

				
				
//				//add swing component to content pane 
				Container c = startFrame.getContentPane();
				c.add(fondo);
				c.add(buttonClient);
				c.add(buttonServer);
				
				buttonClient.addActionListener(new ActionListener() {
		//Crea la instancia del cliente para que se una 		
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("Inicia el cliente");
						Cliente cliente = new Cliente("localhost");	
						startFrame.dispose();
						Ventana2 ventana2 = new Ventana2();
					}
					
					
					
				});
		//Crear la instancia del cliente 		
				buttonServer.addActionListener( new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("Inicia el Server");
						//New thread running the Server
						GameServer hilo =  new GameServer();
						startFrame.dispose();
						Ventana2 ventana2 = new Ventana2();
						
						//
						
						
					}
				});
				
				} 
			});
		
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}

}

	
