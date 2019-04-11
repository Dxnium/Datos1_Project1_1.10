package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Sockets.Cliente;
import Sockets.GameServer;
import game.logic.GameFlow;

import javax.swing.JLabel;
import java.awt.event.*;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Vent_Jugadores extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField CodigoInvitacion;
	public int numeroJugadores;
	public String codigoJuego;

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
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnContinuar.setBounds(169, 194, 106, 38);
		btnContinuar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				GameServer server = new GameServer();
				server.password = CodigoInvitacion.getText();
				server.setMensaje());	
				Cliente cliente = new Cliente("localhost");
				Vent_Datos Vent_2_2 = new Vent_Datos();
				Vent_2_2.setVisible(true);
				Vent_Jugadores.this.dispose();				
			}
		});
		contentPane.add(btnContinuar);
		
		JLabel lblCdigoDeInvitacin = new JLabel("C\u00F3digo de Invitaci\u00F3n");
		lblCdigoDeInvitacin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCdigoDeInvitacin.setBounds(169, 121, 119, 14);
		contentPane.add(lblCdigoDeInvitacin);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		   numeroJugadores = Integer.parseInt(e.getActionCommand());
		   CodigoInvitacion.setText("match_" + e.getActionCommand());
	}
	
}




