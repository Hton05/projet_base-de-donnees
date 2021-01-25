package ClasseJFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ClasseJava.BaseDeDonnees;

public class ClientAjouter extends JFrame {

	private JPanel contentPane;
	private BaseDeDonnees db;
	private JTextField nom;
	private JTextField prenom;
	private JTextField tel;
	private JTextField adresse;
	private JTextField email;
	
	public ClientAjouter() {
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
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdresse.setForeground(new Color(23, 32, 42));
		lblAdresse.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAdresse.setBounds(10, 256, 101, 22);
		contentPane.add(lblAdresse);
		
		JLabel lblFamille = new JLabel("Nom");
		lblFamille.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFamille.setHorizontalAlignment(SwingConstants.CENTER);
		lblFamille.setForeground(new Color(23, 32, 42));
		lblFamille.setBackground(Color.BLUE);
		lblFamille.setBounds(10, 99, 101, 22);
		contentPane.add(lblFamille);
		
		JLabel lblLibell = new JLabel("Pr\u00E9nom");
		lblLibell.setFont(new Font("Dialog", Font.BOLD, 16));
		lblLibell.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibell.setForeground(new Color(23, 32, 42));
		lblLibell.setBounds(10, 149, 101, 22);
		contentPane.add(lblLibell);
		
		JLabel lblPrixUnitaire = new JLabel("T\u00E9l\u00E9phone");
		lblPrixUnitaire.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPrixUnitaire.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrixUnitaire.setForeground(new Color(23, 32, 42));
		lblPrixUnitaire.setBounds(10, 208, 101, 22);
		contentPane.add(lblPrixUnitaire);
		
		JLabel lblQuantit = new JLabel("Email");
		lblQuantit.setFont(new Font("Dialog", Font.BOLD, 16));
		lblQuantit.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantit.setForeground(new Color(23, 32, 42));
		lblQuantit.setBounds(10, 312, 101, 22);
		contentPane.add(lblQuantit);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nom.getText().equals("") || prenom.getText().equals("") || tel.getText().equals("") || adresse.getText().equals("") || email.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "information incomplete");
				} else {
					String sg1 = nom.getText().substring(0, 1).toUpperCase();
					String sg2 = nom.getText().substring(1, nom.getText().length()).toLowerCase();
					String n1 = sg1+sg2;
					sg1 = prenom.getText().substring(0, 1).toUpperCase();
					sg2 = prenom.getText().substring(1, prenom.getText().length()).toLowerCase();
					String p1 = sg1+sg2;
					String [] nomColonne = {"nom_client", "prenom_client", "tel_client", "adresse_client", "email_client"};
					String [] contenu = {n1, p1, tel.getText(), adresse.getText(), email.getText().toLowerCase()};
					db.insert("client", nomColonne, contenu);
					setVisible(false);
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
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Client frame = new Client();
				frame.setVisible(true);
			}
		});
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
		
		nom = new JTextField();
		nom.setBounds(121, 99, 278, 22);
		contentPane.add(nom);
		nom.setColumns(10);
		
		prenom = new JTextField();
		prenom.setColumns(10);
		prenom.setBounds(121, 149, 278, 22);
		contentPane.add(prenom);
		
		tel = new JTextField();
		tel.setColumns(10);
		tel.setBounds(121, 208, 278, 22);
		contentPane.add(tel);
		
		adresse = new JTextField();
		adresse.setColumns(10);
		adresse.setBounds(121, 260, 278, 22);
		contentPane.add(adresse);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(121, 312, 278, 22);
		contentPane.add(email);
	}
}
