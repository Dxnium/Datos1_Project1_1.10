package GUI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Vent_Codigo extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Codigo;

	public Vent_Codigo() {
		setTitle("Scrabble");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDigiteElCdigo = new JLabel("Digite el c\u00F3digo de invitaci\u00F3n");
		lblDigiteElCdigo.setHorizontalAlignment(SwingConstants.LEFT);
		lblDigiteElCdigo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDigiteElCdigo.setBounds(98, 27, 266, 31);
		contentPane.add(lblDigiteElCdigo);
		
		textField_Codigo = new JTextField();
		textField_Codigo.setBounds(148, 104, 140, 31);
		contentPane.add(textField_Codigo);
		textField_Codigo.setColumns(10);
		
		JButton btnContinuar = new JButton("Unirse");
		btnContinuar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnContinuar.setBounds(171, 174, 102, 31);
		btnContinuar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				Vent_Datos Vent_2_2 = new Vent_Datos();
				Vent_2_2.setVisible(true);
				Vent_Codigo.this.dispose();				
			}
		});
		
		contentPane.add(btnContinuar);
	}

}