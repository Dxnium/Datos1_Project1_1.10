package GUI;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import JSON.Decode;
import Sockets.Cliente;
import Sockets.GameServer;

public class Vent_Datos extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public GameServer server;
	public Decode decode;
	

	public Vent_Datos(GameServer server) throws UnknownHostException {
		this.server = server;
		setTitle("Scrabble");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbIP = new JLabel("IP:"+InetAddress.getLocalHost());
		lbIP.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbIP.setBounds(52, 40, 300, 22);
		contentPane.add(lbIP);

		JLabel lblNewLabel = new JLabel("Digite un nombre:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(52, 85, 129, 22);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(191, 88, 152, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Continuar");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(161, 194, 89, 23);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					System.out.println(!textField.getText().isEmpty());
					if(!textField.getText().isEmpty()) {
						Cliente cliente = new Cliente("localhost","agregarJugador,"+textField.getText());
						BoardJFrame boardJF = new BoardJFrame(textField.getText());
						boardJF.setVisible(true);
						Vent_Datos.this.dispose();
					}
					else {
						JOptionPane.showMessageDialog(contentPane,"Debe digitar un nombre"); 
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPane,"Debe digitar un nombre"); 
				}				
			}
		});
		contentPane.add(btnNewButton);
	}
	
}
