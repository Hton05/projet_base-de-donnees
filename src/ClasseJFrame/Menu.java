package ClasseJFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 477, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Client");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Client frame = new Client();
				frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 11, 414, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Famille d'article");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				FamilleArticle frame = new FamilleArticle();
				frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(10, 45, 414, 23);
		contentPane.add(btnNewButton_1);
		
		JButton Client = new JButton("Article");
		Client.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Article frame = new Article();
				frame.setVisible(true);
			}
		});
		Client.setBounds(10, 79, 414, 23);
		contentPane.add(Client);
		
		JButton btnCommander = new JButton("Commander");
		btnCommander.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Commande frame = new Commande();
				frame.setVisible(true);
				//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
		});
		btnCommander.setBounds(10, 113, 414, 23);
		contentPane.add(btnCommander);
		
		JButton btnRapport = new JButton("Rapport");
		btnRapport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Rapport frame = new Rapport();
				frame.setVisible(true);
			}
		});
		btnRapport.setBounds(10, 147, 414, 23);
		contentPane.add(btnRapport);
		
		JButton btnRapport_1 = new JButton("Bon de commande");
		btnRapport_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				BonCommande frame = new BonCommande();
				frame.setVisible(true);
			}
		});
		btnRapport_1.setBounds(10, 181, 414, 23);
		contentPane.add(btnRapport_1);
		
		JButton btnRapport_2 = new JButton("Bon de livraison");
		btnRapport_2.setBounds(10, 215, 414, 23);
		contentPane.add(btnRapport_2);
	}
}
