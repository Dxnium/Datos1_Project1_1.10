package GUI;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import JSON.De;
import Sockets.Cliente;

public class Vent_Codigo extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Codigo;
	private JTextField textField_IP;

	public Vent_Codigo() {
		setTitle("Scrabble");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDigiteElCdigo = new JLabel("Digite el c\u00F3digo de invitaci\u00F3n:");
		lblDigiteElCdigo.setHorizontalAlignment(SwingConstants.LEFT);
		lblDigiteElCdigo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDigiteElCdigo.setBounds(80, 22, 266, 31);
		contentPane.add(lblDigiteElCdigo);
		
		textField_Codigo = new JTextField();
		textField_Codigo.setBounds(148, 64, 140, 31);
		contentPane.add(textField_Codigo);
		textField_Codigo.setColumns(10);
		
		JTextField textField_Nombre = new JTextField();
		textField_Nombre.setBounds(148, 134, 140, 31);
		contentPane.add(textField_Nombre);
		textField_Nombre.setColumns(10);
		
		JLabel lbIP = new JLabel("Digite la IP:");
		lbIP.setHorizontalAlignment(SwingConstants.LEFT);
		lbIP.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbIP.setBounds(80, 102, 266, 31);
		contentPane.add(lbIP);
		
		textField_IP = new JTextField();
		textField_IP.setBounds(148, 226, 140, 31);
		contentPane.add(textField_IP);
		textField_IP.setColumns(10);
		
		
		JButton btnContinuar = new JButton("Unirse");
		btnContinuar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnContinuar.setBounds(167, 299, 102, 31);
		btnContinuar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				System.out.println(textField_IP.getText());
				Cliente cliente = new Cliente(textField_Nombre.getText(),"4,"+textField_IP.getText()+","+textField_Codigo.getText());
				Cliente cliente2 = new Cliente(textField_IP.getText());
				String msgdelServer = cliente2.msg;
				StringWriter toJson = new StringWriter();
				toJson = toJson.append(msgdelServer, 2, msgdelServer.length());
				De aaa = new De(toJson);
				if(aaa.dato.equals(textField_Codigo.getText()) ) {
					System.out.println("Contraseña correcta");
				}
				
		
					Vent_Datos Vent_2_2 = new Vent_Datos(null);
					Vent_2_2.setVisible(true);
					Vent_Codigo.this.dispose();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(contentPane,"error de conexion"); 
			}
								
			}
		});
		
		contentPane.add(btnContinuar);
		
		JLabel lblDigiteElNombre = new JLabel("Digite el nombre de jugador");
		lblDigiteElNombre.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDigiteElNombre.setBounds(81, 189, 281, 26);
		contentPane.add(lblDigiteElNombre);
	}
}
