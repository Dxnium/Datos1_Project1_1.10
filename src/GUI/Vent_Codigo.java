package GUI;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import JSON.Decode;
import Sockets.Cliente;

// TODO: Auto-generated Javadoc
/**
 * The Class Vent_Codigo is the window where you indicate a player inserts the code for joining a game, the player name and the ip address .
 */
public class Vent_Codigo extends JFrame {

	/** The contentPane is where all the graphical components will be */
	private JPanel contentPane;
	
	/** The text field for adding the code for  joining a game*/
	private JTextField textField_Codigo;
	
	/** The text_field_IP is where you can insert your IP */
	private JTextField textField_IP;
	
	/** The decode. */
	public Decode decode; 
	
	/**
	 * Instantiates a new Vent_Codigo.
	 */
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
		
		
		JButton btnContinuar = new JButton();
		btnContinuar.setIcon(new ImageIcon("Images\\continuar.png"));
		btnContinuar.setBounds(167, 299, 102, 31);
		btnContinuar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				String password;
				System.out.println("Nombre"+textField_IP.getText());
				System.out.println("Codigo"+textField_Codigo.getText());
				System.out.println("IP"+textField_Nombre.getText());
				Cliente cliente = new Cliente(textField_Nombre.getText(),"password,none");
				StringWriter toJson = new StringWriter();
				toJson = toJson.append(cliente.msg , 0, cliente.msg.length());
				Decode decode = new Decode(toJson);
				System.out.println(decode.datos[0]);
				if (textField_Codigo.getText().equals(decode.datos[0])) {
					Cliente cliente2 = new Cliente(textField_Nombre.getText(), "agregarJugador,"+textField_IP.getText());
					Cliente cliente3 = new Cliente(textField_Nombre.getText(), "playOrder");
					BoardJFrame boardJF = new BoardJFrame(textField_IP.getText());
					boardJF.name= textField_IP.getText();
					boardJF.setVisible(true);
					Vent_Codigo.this.dispose();
				}
				else {
					JOptionPane.showMessageDialog(contentPane,"Codigo Invalido"); 
				}
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
