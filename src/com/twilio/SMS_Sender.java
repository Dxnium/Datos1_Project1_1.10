package com.twilio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.Vent_Inicio;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;

// TODO: Auto-generated Javadoc
/**
 * The Class SMS_Sender.
 */
public class SMS_Sender extends JFrame {

	/** The content pane. */
	private JPanel contentPane;
	
	/** The Mensaje. */
	private JTextField Mensaje;

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SMS_Sender frame = new SMS_Sender();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */ 
	@SuppressWarnings("rawtypes")
	public SMS_Sender() {
		
		SMS msgSMS = new SMS();
		
		setTitle("Scrabble - Env\u00EDo SMS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Mensaje = new JTextField();
		Mensaje.setBounds(83, 93, 254, 20);
		contentPane.add(Mensaje);
		Mensaje.setColumns(10);
		
		JLabel lblEscribaElMensaje = new JLabel("Escriba el mensaje a enviar");
		lblEscribaElMensaje.setBounds(83, 68, 130, 14);
		contentPane.add(lblEscribaElMensaje);
		
		JComboBox ListaTel = new JComboBox();
		ListaTel.setModel(new DefaultComboBoxModel(new String[] {"+50683778693", "+50689652399", "+50661900272", "+50683683222"}));
		ListaTel.setBounds(83, 149, 173, 20);
		contentPane.add(ListaTel);
		
		JLabel lblSeleccioneElNmero = new JLabel("Seleccione el n\u00FAmero de un experto");
		lblSeleccioneElNmero.setBounds(83, 124, 196, 14);
		contentPane.add(lblSeleccioneElNmero);
		
		JButton btnEnviar = new JButton("Enviar SMS");
		btnEnviar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEnviar.setBounds(167, 200, 100, 23);
		btnEnviar.addActionListener(new ActionListener() {			
		    public void actionPerformed(ActionEvent e) {	
			    System.out.println(Mensaje.getText());
			    System.out.println(ListaTel.getSelectedItem());	
		        msgSMS.EnvioSMS(Mensaje.getText(), (String)ListaTel.getSelectedItem());
		        SMS_Sender.this.dispose();

		    }
		});
		contentPane.add(btnEnviar);
		
		JLabel lbl_titulo = new JLabel("Envio de SMS al Experto");
		lbl_titulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_titulo.setBounds(105, 28, 242, 20);
		contentPane.add(lbl_titulo);
	}
}
