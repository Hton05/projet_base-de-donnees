package ClasseJFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ClasseJava.BaseDeDonnees;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BonLivraison extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model = new DefaultTableModel();
	private ResultSet rs;
	private BaseDeDonnees db;
	private JTable table;
	private JComboBox rechercherPar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BonLivraison frame = new BonLivraison();
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
	public BonLivraison() {
		Color couleur_back =Color.decode("#F2F3F4");
		Color couleur1 = Color.decode("#17202A");
		Color couleur2 = Color.decode("#1C98CF");
		Color entete = Color.decode("#083346");
		Color vert =Color.decode("#27AE60");
		Color rouge =Color.decode("#D32222");
		Color orange =Color.decode("#E67E22");
		Color mauve =Color.decode("#6C3483");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(183, 0, 1000, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(entete);
		panel.setBounds(0, 0, 1000, 88);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 150, 88);
		lblNewLabel.setIcon(new ImageIcon(this.getClass().getResource("/logo2.png")));
		panel.add(lblNewLabel);
		
		JLabel lblGestionArticle = new JLabel("LIVRAISON");
		lblGestionArticle.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionArticle.setForeground(Color.WHITE);
		lblGestionArticle.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 40));
		lblGestionArticle.setBounds(402, 31, 305, 48);
		panel.add(lblGestionArticle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Dialog", Font.BOLD, 12));
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setForeground(Color.DARK_GRAY);
		scrollPane.setBounds(100, 192, 800, 343);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionForeground(Color.WHITE);
		table.setSelectionBackground(couleur2);
		table.setRowHeight(30);
		table.setShowGrid(false);
		scrollPane.setViewportView(table);
		
		
		db = new BaseDeDonnees();
		model.addColumn("Id commande");
		model.addColumn("Nom client");
		model.addColumn("Prénom client");
		model.addColumn("Adresse client");
		model.addColumn("Date de livraison");
		model.addColumn("Libellé article");
		model.addColumn("Prix unitaire");
		model.addColumn("Quantite");
		model.addColumn("Montant");
		model.setRowCount(0);
		rs = db.selectAll("v_bon_livraison"/*, "idCommande = '" +Commande.idCom+"'"*/);
		try {
			while (rs.next()) {
				model.addRow(new Object [] { rs.getInt("idCommande"),
						rs.getString("nom_client"),
						rs.getString("prenom_client"),
						rs.getString("adresse_client"),
						rs.getString("date_livraison"),
						rs.getString("libelle_article"),
						rs.getDouble("prix_unitaire"),
						rs.getString("qte_article"),						
						rs.getDouble("montant")						
				});
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		table.setModel(model);
		
		rechercherPar = new JComboBox();
		rechercherPar.setModel(new DefaultComboBoxModel(new String[] {"Id", "Nom", "Pr\u00E9nom", "T\u00E9l\u00E9phone", "Adresse\t", "Email"}));
		rechercherPar.setBounds(100, 122, 146, 26);
		contentPane.add(rechercherPar);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Retour frame = new Retour(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
				frame.setVisible(true);
				
			}
		});
		btnRetour.setSelected(true);
		btnRetour.setForeground(new Color(28, 152, 207));
		btnRetour.setFont(new Font("Dialog", Font.BOLD, 20));
		btnRetour.setBorderPainted(false);
		btnRetour.setBackground(Color.WHITE);
		btnRetour.setBounds(92, 546, 154, 42);
		contentPane.add(btnRetour);
	}
	
	public BonLivraison(String idCommande) {
		
	}
}
