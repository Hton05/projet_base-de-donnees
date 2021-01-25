package ClasseJFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ClasseJava.BaseDeDonnees;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Retourner extends JFrame {

	private JPanel contentPane;
	private ResultSet rs;
	private BaseDeDonnees db;	
	
	public Retourner(String id, String libelle1, int quantite1) {
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
		
		JLabel libelle = new JLabel("<dynamic>");
		libelle.setHorizontalAlignment(SwingConstants.CENTER);
		libelle.setForeground(new Color(23, 32, 42));
		libelle.setFont(new Font("Dialog", Font.BOLD, 16));
		libelle.setBackground(Color.BLUE);
		libelle.setBounds(130, 99, 251, 39);
		contentPane.add(libelle);
		
		JLabel lblQuantit = new JLabel("Quantit\u00E9");
		lblQuantit.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantit.setForeground(new Color(23, 32, 42));
		lblQuantit.setFont(new Font("Dialog", Font.BOLD, 16));
		lblQuantit.setBounds(10, 199, 110, 39);
		contentPane.add(lblQuantit);
		
		JLabel lblLibell = new JLabel("Libell\u00E9");
		lblLibell.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibell.setForeground(new Color(23, 32, 42));
		lblLibell.setFont(new Font("Dialog", Font.BOLD, 16));
		lblLibell.setBounds(10, 99, 110, 39);
		contentPane.add(lblLibell);
		
		JSpinner quantite = new JSpinner();
		quantite.setModel(new SpinnerNumberModel(1, 1, quantite1, 1));
		quantite.setBounds(130, 211, 251, 20);
		contentPane.add(quantite);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idArticle = 0;
				double qte = 0.0;
				double montant = 0.0;
				String[] colonne = {"id_Article", "prix_unitaire"};
				String etat = "libelle_article = '" +libelle1+ "'";
				rs = db.select("article", colonne, etat);
				try {
					rs.next();
					idArticle = rs.getInt("id_Article");
					qte = rs.getDouble("prix_unitaire");
					System.out.println(qte);
				} catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
				String query = "UPDATE article SET qte_en_stock = qte_en_stock + " +Integer.parseInt(quantite.getValue().toString())+ " WHERE id_Article = " +idArticle;
				db.executeUpdate(query);
				if(Integer.parseInt(quantite.getValue().toString()) == quantite1) {
					String etat2 = "idCommande = '" +id+ "' AND id_Article = '" +idArticle+ "'";
					db.delete("article_commande", etat2);
				} else {
					String query1 = "UPDATE article_commande SET qte_article = qte_article - " +Integer.parseInt(quantite.getValue().toString())+ " WHERE idCommande = " +id+ " AND id_Article = " +idArticle;
					db.executeUpdate(query1);					
				}
				montant = qte*Integer.parseInt(quantite.getValue().toString());
				String query1 = "UPDATE commande SET montant = montant - " +montant+ " WHERE idCommande = " +id;
				db.executeUpdate(query1);
			}
		});
		btnEnregistrer.setForeground(Color.WHITE);
		btnEnregistrer.setFont(new Font("Dialog", Font.BOLD, 12));
		btnEnregistrer.setBorderPainted(false);
		btnEnregistrer.setBackground(new Color(39, 174, 96));
		btnEnregistrer.setBounds(10, 249, 126, 31);
		contentPane.add(btnEnregistrer);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setForeground(Color.WHITE);
		btnAnnuler.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAnnuler.setBorderPainted(false);
		btnAnnuler.setBackground(new Color(230, 126, 34));
		btnAnnuler.setBounds(146, 249, 123, 31);
		contentPane.add(btnAnnuler);
		
		libelle.setText(libelle1);
		quantite.setValue(quantite1);
		
	}
}
