package GUI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.common.net.InetAddresses;

import Sockets.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Vent_Datos extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	public Vent_Datos() throws UnknownHostException {
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
						BoardJFrame boardJF = new BoardJFrame();
						boardJF.setVisible(true);
						Vent_Datos.this.dispose();
					}else {
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
