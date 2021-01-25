package ClasseJFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClasseJava.BaseDeDonnees;

import javax.swing.JButton;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientSupprimer extends JFrame {

	private JPanel contentPane;
	private BaseDeDonnees db;
	private ResultSet rs;

	public ClientSupprimer(String id, String nom1, String prenom1, String tel1, String adresse1, String email1) {
		db = new BaseDeDonnees();
		Color couleur_back =Color.decode("#F2F3F4");
		Color couleur1 = Color.decode("#17202A");
		Color couleur2 = Color.decode("#1C98CF");
		Color entete = Color.decode("#083346");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 425, 550);
		contentPane = new JPanel();
		contentPane.setBackground(couleur_back);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(entete);
		panel.setBounds(0, 0, 409, 88);
		panel.setLayout(null);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 150, 88);
		lblNewLabel.setIcon(new ImageIcon(this.getClass().getResource("/logo2.png")));
		panel.add(lblNewLabel);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Confirmer", "Attention", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					db.delete("client", "idClient = " +id);	
					setVisible(false);
				}
				else {
					return;
				}
			}
		});
		btnEnregistrer.setForeground(Color.WHITE);
		btnEnregistrer.setFont(new Font("Dialog", Font.BOLD, 12));
		btnEnregistrer.setBorderPainted(false);
		btnEnregistrer.setBackground(new Color(39, 174, 96));
		btnEnregistrer.setBounds(10, 469, 126, 31);
		contentPane.add(btnEnregistrer);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setForeground(Color.WHITE);
		btnAnnuler.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAnnuler.setBorderPainted(false);
		btnAnnuler.setBackground(new Color(230, 126, 34));
		btnAnnuler.setBounds(281, 469, 123, 31);
		contentPane.add(btnAnnuler);
		
		JButton btnActualiser = new JButton("Modifier");
		btnActualiser.setForeground(Color.WHITE);
		btnActualiser.setFont(new Font("Dialog", Font.BOLD, 12));
		btnActualiser.setBorderPainted(false);
		btnActualiser.setBackground(new Color(28, 152, 207));
		btnActualiser.setBounds(146, 469, 125, 31);
		contentPane.add(btnActualiser);
		
		JLabel nom = new JLabel("<dynamic>");
		nom.setHorizontalAlignment(SwingConstants.CENTER);
		nom.setForeground(new Color(23, 32, 42));
		nom.setFont(new Font("Dialog", Font.BOLD, 16));
		nom.setBackground(Color.BLUE);
		nom.setBounds(121, 99, 283, 22);
		contentPane.add(nom);
		
		JLabel prenom = new JLabel("<dynamic>");
		prenom.setHorizontalAlignment(SwingConstants.CENTER);
		prenom.setForeground(new Color(23, 32, 42));
		prenom.setFont(new Font("Dialog", Font.BOLD, 16));
		prenom.setBackground(Color.BLUE);
		prenom.setBounds(121, 149, 283, 22);
		contentPane.add(prenom);
		
		JLabel tel = new JLabel("0.0");
		tel.setHorizontalAlignment(SwingConstants.CENTER);
		tel.setForeground(new Color(23, 32, 42));
		tel.setFont(new Font("Dialog", Font.BOLD, 16));
		tel.setBackground(Color.BLUE);
		tel.setBounds(121, 208, 278, 22);
		contentPane.add(tel);
		
		JLabel adresse = new JLabel("0");
		adresse.setHorizontalAlignment(SwingConstants.CENTER);
		adresse.setForeground(new Color(23, 32, 42));
		adresse.setFont(new Font("Dialog", Font.BOLD, 16));
		adresse.setBackground(Color.BLUE);
		adresse.setBounds(121, 256, 278, 22);
		contentPane.add(adresse);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdresse.setForeground(new Color(23, 32, 42));
		lblAdresse.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAdresse.setBounds(10, 256, 101, 22);
		contentPane.add(lblAdresse);
		
		JLabel lblFamille = new JLabel("Nom");
		lblFamille.setHorizontalAlignment(SwingConstants.CENTER);
		lblFamille.setForeground(new Color(23, 32, 42));
		lblFamille.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFamille.setBackground(Color.BLUE);
		lblFamille.setBounds(10, 99, 101, 22);
		contentPane.add(lblFamille);
		
		JLabel lblLibell = new JLabel("Pr\u00E9nom");
		lblLibell.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibell.setForeground(new Color(23, 32, 42));
		lblLibell.setFont(new Font("Dialog", Font.BOLD, 16));
		lblLibell.setBounds(10, 149, 101, 22);
		contentPane.add(lblLibell);
		
		JLabel lblPrixUnitaire = new JLabel("T\u00E9l\u00E9phone");
		lblPrixUnitaire.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrixUnitaire.setForeground(new Color(23, 32, 42));
		lblPrixUnitaire.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPrixUnitaire.setBounds(10, 208, 101, 22);
		contentPane.add(lblPrixUnitaire);
		
		JLabel lblQuantit = new JLabel("Email");
		lblQuantit.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantit.setForeground(new Color(23, 32, 42));
		lblQuantit.setFont(new Font("Dialog", Font.BOLD, 16));
		lblQuantit.setBounds(10, 312, 101, 22);
		contentPane.add(lblQuantit);
		
		JLabel email = new JLabel("<dynamic>");
		email.setHorizontalAlignment(SwingConstants.CENTER);
		email.setForeground(new Color(23, 32, 42));
		email.setFont(new Font("Dialog", Font.BOLD, 16));
		email.setBackground(Color.BLUE);
		email.setBounds(121, 312, 283, 22);
		contentPane.add(email);
		
		nom.setText(nom1);
		prenom.setText(prenom1);
		tel.setText(tel1);
		adresse.setText(adresse1);
		email.setText(email1);
	}
}
