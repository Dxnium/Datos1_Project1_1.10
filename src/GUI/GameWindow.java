package GUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

// TODO: Auto-generated Javadoc
/**
 * The Class GameWindow.
 */
public class GameWindow extends JFrame {
	
	/** The active. */
	private boolean active = false;
	
	/** The Xsize. */
	private int Xsize = 1920;
	
	/** The Ysize. */
	private int Ysize = 1080;
	
	
	/**
	 * Instantiates a new game window.
	 */
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
