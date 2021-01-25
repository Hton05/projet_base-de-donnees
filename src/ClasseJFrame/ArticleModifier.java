package ClasseJFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
import java.awt.event.ActionEvent;

public class ArticleModifier extends JFrame {

	private JPanel contentPane;
	private JTextField libelle;
	private JTextField prxUnit;
	private JComboBox familleNom;
	private JSpinner quantite;
	private ResultSet rs;
	private BaseDeDonnees db;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierArticle frame = new ModifierArticle();
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
	public ArticleModifier(String id, String libelle1, double prxUnit1, int quantite1, String familleNom1) {
		
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
		
		familleNom = new JComboBox();
		String query = "SELECT * FROM famille_article order by nom_famille";
		rs = db.executeQuery(query);
		try {
			while (rs.next()) {
				String contenu = rs.getString("nom_famille");
				familleNom.addItem(contenu);
				}
			} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		familleNom.setBounds(160, 102, 221, 22);
		contentPane.add(familleNom);
		
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
		
		quantite = new JSpinner();
		quantite.setBounds(160, 305, 221, 20);
		contentPane.add(quantite);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "UPDATE article SET libelle_article = ?, prix_unitaire = ?, qte_en_stock = ?, idFamille_Article = ? WHERE id_Article = ?";
					PreparedStatement prepare = db.getConnection().prepareStatement(query);
					rs = db.selectAll("famille_article", "nom_famille = '" +familleNom.getSelectedItem().toString()+ "'");
					rs.next();
					int contient =  rs.getInt("idFamille_Article");
					prepare.setString(1, libelle.getText());
					prepare.setDouble(2, Double.parseDouble(prxUnit.getText()));
					prepare.setInt(3, Integer.parseInt(quantite.getValue().toString()));
					prepare.setInt(4, contient);
					prepare.setString(5, id);
					prepare.executeUpdate();
					setVisible(false);
					
				}catch (Exception e3) {
					JOptionPane.showMessageDialog(null, e3.getMessage());
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
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnActualiser.setForeground(Color.WHITE);
		btnActualiser.setFont(new Font("Dialog", Font.BOLD, 12));
		btnActualiser.setBorderPainted(false);
		btnActualiser.setBackground(new Color(28, 152, 207));
		btnActualiser.setBounds(151, 403, 125, 31);
		contentPane.add(btnActualiser);
		
		libelle = new JTextField();
		libelle.setHorizontalAlignment(SwingConstants.RIGHT);
		libelle.setText("");
		libelle.setColumns(10);
		libelle.setBounds(160, 178, 221, 20);
		contentPane.add(libelle);
		
		prxUnit = new JTextField();
		prxUnit.setHorizontalAlignment(SwingConstants.RIGHT);
		prxUnit.setText("");
		prxUnit.setColumns(10);
		prxUnit.setBounds(160, 244, 221, 20);
		contentPane.add(prxUnit);
		
		familleNom.setSelectedItem(familleNom1);
		libelle.setText(libelle1);
		prxUnit.setText(String.valueOf(prxUnit1));
		quantite.setModel(new SpinnerNumberModel(quantite1, quantite1, null, 1));
	}
}
