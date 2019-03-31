package GUI;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;



public class StartWindow extends JFrame {
	private boolean active = true;
	private int Xsize = 1920;
	private int Ysize = 1080;
	
	
	public StartWindow() {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame startFrame = new JFrame("Scrabble");
				startFrame.setSize(Xsize,Ysize);
				startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				startFrame.setVisible(true);
				
				//Set layout manager
				startFrame.setLayout(null);
				
				
		
				//swing component 
				JLabel background=new JLabel(new ImageIcon("\\Datos1_Project1_1.10\\IMG\\Fondo.jpg"));
				JButton buttonClient = new JButton("Cliente");
				buttonClient.setBounds(760, 540, 200, 40);
				JButton buttonServer = new JButton("Server");
				buttonServer.setBounds(960, 540, 200, 40);

				
				
//				//add swing component to content pane 
				Container c = startFrame.getContentPane();
				c.add(background);
				c.add(buttonClient);
				c.add(buttonServer);
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
