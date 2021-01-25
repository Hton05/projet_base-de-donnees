package ClasseJFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

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

import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class ArticleSupprimer extends JFrame {

	private JPanel contentPane;
	private BaseDeDonnees db;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupprimerArticle frame = new SupprimerArticle();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public ArticleSupprimer(String id, String libelle1, double prxUnit1, int quantite1, String familleNom1) {
		db = new BaseDeDonnees();
		//couleur 
		Color couleur_back =Color.decode("#F2F3F4");
		Color couleur1 = Color.decode("#17202A");
		Color couleur2 = Color.decode("#1C98CF");
		Color entete = Color.decode("#083346");
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JLabel lblFamille = new JLabel("Cat\u00E9gorie");
		lblFamille.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFamille.setHorizontalAlignment(SwingConstants.CENTER);
		lblFamille.setForeground(new Color(23, 32, 42));
		lblFamille.setBackground(Color.BLUE);
		lblFamille.setBounds(37, 101, 101, 22);
		contentPane.add(lblFamille);
		
		JLabel lblLibell = new JLabel("Libell\u00E9");
		lblLibell.setFont(new Font("Dialog", Font.BOLD, 16));
		lblLibell.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibell.setForeground(new Color(23, 32, 42));
		lblLibell.setBounds(31, 170, 82, 31);
		contentPane.add(lblLibell);
		
		JLabel lblPrixUnitaire = new JLabel("Prix unitaire");
		lblPrixUnitaire.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPrixUnitaire.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrixUnitaire.setForeground(new Color(23, 32, 42));
		lblPrixUnitaire.setBounds(38, 234, 118, 35);
		contentPane.add(lblPrixUnitaire);
		
		JLabel lblQuantit = new JLabel("Quantit\u00E9");
		lblQuantit.setFont(new Font("Dialog", Font.BOLD, 16));
		lblQuantit.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantit.setForeground(new Color(23, 32, 42));
		lblQuantit.setBounds(28, 294, 110, 39);
		contentPane.add(lblQuantit);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Cet article ne sera pas supprimé mais la quantité en stock sera mis à 0");
				if (JOptionPane.showConfirmDialog(null, "Confirmer", "Attention", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					try {
						String query = "UPDATE article SET qte_en_stock = ? WHERE id_Article = ?";
						PreparedStatement prepare = db.getConnection().prepareStatement(query);
						prepare.setDouble(1, 0.0);
						prepare.setString(2, id);
						prepare.executeUpdate();
						setVisible(false);
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}					
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
		btnEnregistrer.setBounds(12, 404, 126, 31);
		contentPane.add(btnEnregistrer);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setForeground(Color.WHITE);
		btnAnnuler.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAnnuler.setBorderPainted(false);
		btnAnnuler.setBackground(new Color(230, 126, 34));
		btnAnnuler.setBounds(288, 403, 125, 31);
		contentPane.add(btnAnnuler);
		
		JButton btnActualiser = new JButton("Modifier");
		btnActualiser.setForeground(Color.WHITE);
		btnActualiser.setFont(new Font("Dialog", Font.BOLD, 12));
		btnActualiser.setBorderPainted(false);
		btnActualiser.setBackground(new Color(28, 152, 207));
		btnActualiser.setBounds(151, 403, 125, 31);
		contentPane.add(btnActualiser);
		
		JLabel familleNom = new JLabel("");
		familleNom.setHorizontalAlignment(SwingConstants.CENTER);
		familleNom.setForeground(new Color(23, 32, 42));
		familleNom.setFont(new Font("Dialog", Font.BOLD, 16));
		familleNom.setBackground(Color.BLUE);
		familleNom.setBounds(148, 101, 251, 22);
		contentPane.add(familleNom);
		
		JLabel libelle = new JLabel("");
		libelle.setHorizontalAlignment(SwingConstants.CENTER);
		libelle.setForeground(new Color(23, 32, 42));
		libelle.setFont(new Font("Dialog", Font.BOLD, 16));
		libelle.setBackground(Color.BLUE);
		libelle.setBounds(151, 170, 251, 22);
		contentPane.add(libelle);
		
		JLabel prxUnit = new JLabel("");
		prxUnit.setHorizontalAlignment(SwingConstants.CENTER);
		prxUnit.setForeground(new Color(23, 32, 42));
		prxUnit.setFont(new Font("Dialog", Font.BOLD, 16));
		prxUnit.setBackground(Color.BLUE);
		prxUnit.setBounds(148, 234, 251, 22);
		contentPane.add(prxUnit);
		
		JLabel quantite = new JLabel("");
		quantite.setHorizontalAlignment(SwingConstants.CENTER);
		quantite.setForeground(new Color(23, 32, 42));
		quantite.setFont(new Font("Dialog", Font.BOLD, 16));
		quantite.setBackground(Color.BLUE);
		quantite.setBounds(151, 294, 251, 22);
		contentPane.add(quantite);
		
		familleNom.setText(familleNom1);
		libelle.setText(libelle1);
		prxUnit.setText(String.valueOf(prxUnit1));
		quantite.setText(String.valueOf(quantite1));
	}
}
