package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Sockets.Cliente;
import Sockets.GameServer;
import game.logic.GameFlow;

import javax.swing.JLabel;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

// TODO: Auto-generated Javadoc
/**
 * The Class Vent_Jugadores.
 */
public class Vent_Jugadores extends JFrame implements ActionListener {

	/** The content pane. */
	private JPanel contentPane;
	
	/** The Codigo invitacion. */
	private JTextField CodigoInvitacion;
	
	/** The numero jugadores. */
	public int numeroJugadores;
	
	/** The codigo juego. */
	public String codigoJuego;

	/**
	 * Instantiates a new vent jugadores.
	 */
	public Vent_Jugadores() {
		
		setTitle("Scrabble");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingresa la cantidad de jugadores");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(53, 21, 353, 38);
		contentPane.add(lblNewLabel);
		
		JRadioButton N2 = new JRadioButton("2");
		N2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		N2.setBounds(134, 82, 38, 23);
		N2.setActionCommand("2");
		contentPane.add(N2);
		
		JRadioButton N3 = new JRadioButton("3");
		N3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		N3.setBounds(213, 82, 38, 23);
		N3.setActionCommand("3");
		contentPane.add(N3);
		
		JRadioButton N4 = new JRadioButton("4");
		N4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		N4.setBounds(281, 82, 38, 23);
		N4.setActionCommand("4");
		contentPane.add(N4);
		
		N2.addActionListener(this);
		N3.addActionListener(this);
		N4.addActionListener(this);		
		
		CodigoInvitacion = new JTextField();
		CodigoInvitacion.setEditable(false);
		CodigoInvitacion.setToolTipText("C\u00F3digo de Invitaci\u00F3n");
		CodigoInvitacion.setBounds(169, 146, 106, 28);
		contentPane.add(CodigoInvitacion);
		CodigoInvitacion.setColumns(10);
		
		JButton btnContinuar = new JButton();
		btnContinuar.setIcon(new ImageIcon("Images\\continuar.png)"));
		btnContinuar.setBounds(169, 194, 106, 38);
		btnContinuar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				GameServer server = new GameServer();
				server.password = CodigoInvitacion.getText();
				Cliente cliente = new Cliente("localhost","startgame,"+Integer.toString(numeroJugadores));
				Vent_Datos Vent_2_2;
				try {
					Vent_2_2 = new Vent_Datos(server);
					Vent_2_2.setVisible(true);
					Vent_Jugadores.this.dispose();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
			}
		});
		contentPane.add(btnContinuar);
		
		JLabel lblCdigoDeInvitacin = new JLabel("C\u00F3digo de Invitaci\u00F3n");
		lblCdigoDeInvitacin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCdigoDeInvitacin.setBounds(169, 121, 119, 14);
		contentPane.add(lblCdigoDeInvitacin);
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		   numeroJugadores = Integer.parseInt(e.getActionCommand());
			try {
				CodigoInvitacion.setText( generateGameCode());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
	}
	
	/**
	 * Generate game code.
	 *
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String generateGameCode() throws IOException {
		BufferedReader reader = null;
		reader= new BufferedReader(new FileReader("characters.txt"));
		int count=0;
		String line;
		String letterArray[]= new String[26];
		String numberArray[]= new String[10];
		while((line = reader.readLine()) != null) {
			if(count==0) {
				letterArray = line.split(",");
			}else{
				numberArray=line.split(",");
			}
			count++;
		}
		reader.close();
		String GameCode="";
		int amount=0;
		while(amount<6) {
			if(amount<3) {
				int randomLetter = ThreadLocalRandom.current().nextInt(1, 26);
				GameCode=GameCode+letterArray[randomLetter];
			}else{
				int randomNum = ThreadLocalRandom.current().nextInt(1, 10);
				GameCode=GameCode+numberArray[randomNum];
			}
			amount++;
		}
		System.out.println(GameCode);
		return GameCode;
	}
	
}




