package GUI;

import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JLabel;

import com.sun.org.apache.xerces.internal.util.URI.MalformedURIException;

@SuppressWarnings("serial")
public class botonReglas extends JLabel {
	private String link = "http://www.redeletras.com/rules.play.php";
	private boolean inside;
	
	 public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public boolean isInside() {
		return inside;
	}
	public void setInside(boolean inside) {
		this.inside = inside;
	}
	public botonReglas() {
		 addMouseListener(new MouseAdapter() {
			 @Override
			public void mousePressed(MouseEvent e) {
				try {
					openLink();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
			 @Override
			public void mouseEntered(MouseEvent e) {
				 inside = true;
				 repaint();
			}
			 @Override
			public void mouseExited(MouseEvent e) {
				 inside = false;
				 repaint();
			}
		});
		 
	 }
	 public void openLink() throws URISyntaxException {
		 try {
			Desktop.getDesktop().browse(new java.net.URI(link));
		} catch (MalformedURIException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	 
	 @Override
	public void paint(Graphics g) {
		 Graphics2D gd = (Graphics2D) g;
		 
		 if (inside) {
			 gd.rotate(Math.toRadians(30), getWidth()/2 , getHeight()/2);
		 }
		  super.paint(g);
	 }
	 
}