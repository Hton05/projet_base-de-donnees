package ClasseJFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Rapport extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rapport frame = new Rapport();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Rapport() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Rapport du jour");
		btnNewButton.setBounds(10, 11, 414, 32);
		contentPane.add(btnNewButton);
		
		JButton btnRapportDeLa = new JButton("Rapport de la semaine");
		btnRapportDeLa.setBounds(10, 54, 414, 32);
		contentPane.add(btnRapportDeLa);
		
		JButton btnRapportDuMois = new JButton("Rapport du mois");
		btnRapportDuMois.setBounds(10, 97, 414, 32);
		contentPane.add(btnRapportDuMois);
	}

}
