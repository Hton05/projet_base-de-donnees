package ClasseJFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ClasseJava.BaseDeDonnees;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BonCommande extends JFrame {

	
	private DefaultTableModel model = new DefaultTableModel();
	private JPanel contentPane;
	private ResultSet rs;
	private BaseDeDonnees db;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BonCommande frame = new BonCommande("72");
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
	public BonCommande() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 690, 324);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		db = new BaseDeDonnees();
		model.addColumn("Id commande");
		model.addColumn("Nom client");
		model.addColumn("Prénom client");
		model.addColumn("Libellé article");
		model.addColumn("Quantite");
		model.addColumn("Prix unitaire");
		model.addColumn("Montant");
		model.addColumn("Date de commande");
		model.setRowCount(0);
		rs = db.selectAll("v_bon_de_commande order by idCommande");
		try {
			while (rs.next()) {
				model.addRow(new Object [] { rs.getInt("idCommande"),
						rs.getString("nom_client"),
						rs.getString("prenom_client"),
						rs.getString("libelle_article"),
						rs.getString("qte_article"),
						rs.getDouble("prix_unitaire"),
						rs.getDouble("montant"),
						rs.getString("date_commande"),
				});
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		table.setModel(model);
	}
	
	public BonCommande(String idCom) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 690, 324);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		db = new BaseDeDonnees();
		model.addColumn("Id commande");
		model.addColumn("Nom client");
		model.addColumn("Prénom client");
		model.addColumn("Libellé article");
		model.addColumn("Quantite");
		model.addColumn("Prix unitaire");
		model.addColumn("Montant");
		model.addColumn("Date de commande");
		model.setRowCount(0);
		rs = db.selectAll("v_bon_de_commande", "idCommande = '" +idCom+"' order by idCommande");
		try {
			while (rs.next()) {
				model.addRow(new Object [] { rs.getInt("idCommande"),
						rs.getString("nom_client"),
						rs.getString("prenom_client"),
						rs.getString("libelle_article"),
						rs.getString("qte_article"),
						rs.getDouble("prix_unitaire"),
						rs.getDouble("montant"),
						rs.getString("date_commande"),
				});
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		table.setModel(model);
	}
	
	public void table() {
		model.setRowCount(0);
		rs = db.selectAll("v_bon_de_commande", "idCommande = '" +Commande.idCom+"'");
		try {
			while (rs.next()) {
				model.addRow(new Object [] { rs.getInt("idCommande"),
						rs.getString("nom_client"),
						rs.getString("prenom_client"),
						rs.getString("libelle_article"),
						rs.getString("qte_article"),
						rs.getDouble("prix_unitaire"),
						rs.getDouble("montant"),
						rs.getString("date_commande"),
				});
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		table.setModel(model);
	}
}
