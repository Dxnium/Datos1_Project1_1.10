package GUI;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
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
				setLayout(new BorderLayout());
				
				
			
				//swing component  
				JTextArea textarea = new JTextArea();
				JButton button = new JButton("Click me");
				
				
				//add swing component to content pane 
				Container c = startFrame.getContentPane();
				c.add(textarea,BorderLayout.CENTER);
				c.add(button,BorderLayout.SOUTH);
				
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
