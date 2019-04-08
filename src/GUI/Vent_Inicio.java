package GUI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vent_Inicio extends JFrame {

	private JPanel contentPane;

	public Vent_Inicio() {
		setTitle("Scrabble");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		btnIniciar.setBounds(151, 100, 154, 50);
		contentPane.add(btnIniciar);
		
		JButton btnUnirse = new JButton("Unirse a una partida");
		btnUnirse.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUnirse.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {				
				Vent_Codigo Vent_2 = new Vent_Codigo();
				Vent_2.setVisible(true);
				Vent_Inicio.this.dispose();	
			}
		});
		btnUnirse.setBounds(151, 174, 154, 50);
		contentPane.add(btnUnirse);
	}
}
