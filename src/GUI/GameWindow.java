package GUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GameWindow extends JFrame {
	private boolean active = false;
	private int Xsize = 1920;
	private int Ysize = 1080;
	
	
	public GameWindow() {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame gameFrame = new JFrame("Scrabble");
				gameFrame.setSize(Xsize,Ysize );
				gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gameFrame.setVisible(true);
				}
			});
		
		
	}
}
