package GUI;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import Sockets.Cliente;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Vent_Inicio extends JFrame {

	private JPanel contentPane;

	private JPanel contentPane2;
	private JPanel contentPane3;


	

	private boolean active = true;
	private int Xsize = 1600;
	private int Ysize = 900;
	
	public Vent_Inicio() {
		setTitle("Scrabble");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Xsize, Ysize);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		contentPane2 = new JPanel();
		contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane2.setLayout(null);
		contentPane2.setBounds(152, 102, 154, 50);
		
		contentPane3 = new JPanel();
		contentPane3.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane3.setLayout(null);
		contentPane3.setBounds(152, 172, 154, 50);
		
		
		
		
		//Conexión Cliente - Servidor (GUI)
		Image fondoImg = new ImageIcon("Images\\Fondo.JPG").getImage();
		JLabel fondo=new JLabel("");
		fondo.setBounds(0, 0, 1600, 900);
		fondo.setIcon(new ImageIcon(fondoImg));
		JButton buttonClient = new JButton("Cliente");
		
		JButton buttonServer = new JButton("Server");
		
		contentPane.add(fondo);
		//*************************************************
		JLabel lblBienvenidosAScrabble = new JLabel("Bienvenidos a Scrabble");
		lblBienvenidosAScrabble.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblBienvenidosAScrabble.setBounds(45, 11, 358, 37);
		contentPane.add(lblBienvenidosAScrabble);
		
		JButton btnIniciar = new JButton("Iniciar nuevo juego");
		btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnIniciar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				Vent_Jugadores Vent_2_1 = new Vent_Jugadores();
				Vent_2_1.setVisible(true);
				Vent_Inicio.this.dispose();				
			}
		});
		btnIniciar.setBounds(0, 0, 154, 50);
		contentPane2.add(btnIniciar);
		
		
		JButton btnUnirse = new JButton("Unirse a una partida");
		btnUnirse.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUnirse.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {				
				Vent_Codigo Vent_2 = new Vent_Codigo();
				Vent_2.setVisible(true);
				Vent_Inicio.this.dispose();	
			}
		});
		btnUnirse.setBounds(0, 0, 154, 50);
		contentPane3.add(btnUnirse);
		
		
		this.add(contentPane2);
		this.add(contentPane3);
		this.add(contentPane);
		
	}
}
