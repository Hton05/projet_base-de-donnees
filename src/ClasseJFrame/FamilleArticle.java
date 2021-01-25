package ClasseJFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ClasseJava.BaseDeDonnees;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.Point;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FamilleArticle extends JFrame {

	private JPanel contentPane;
	private JTable tableFamille;
	private JTextField nomFamille;
	private JButton btnSupprimer;
	private JButton btnModifier;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private JTextField rechercher;
	private JLabel lblRechercherPar;
	private JButton btnTrierPar;
	private JComboBox trierPar;
	private JComboBox rechercherPar;
	
	
	private ResultSet rs;
	private BaseDeDonnees db;
	private DefaultTableModel model = new DefaultTableModel();
	private JButton btnRetour;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FamilleArticle frame = new FamilleArticle();
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
	public FamilleArticle() {
		db = new BaseDeDonnees ();
		
		model.addColumn("Id");
		model.addColumn("Nom");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(314, 11, 306, 463);
		contentPane.add(scrollPane);
		
		tableFamille = new JTable();
		tableFamille.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nomFamille.setText(String.valueOf(tableFamille.getValueAt(tableFamille.getSelectedRow(), 1)));
			}
		});
		tableFamille.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"Id", "Nom Famille"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableFamille.getColumnModel().getColumn(0).setResizable(false);
		tableFamille.getColumnModel().getColumn(1).setResizable(false);
		scrollPane.setViewportView(tableFamille);
		
		JLabel lblNewLabel = new JLabel("Nom famille");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 197, 108, 14);
		contentPane.add(lblNewLabel);
		
		nomFamille = new JTextField();
		nomFamille.setBounds(128, 194, 176, 20);
		contentPane.add(nomFamille);
		nomFamille.setColumns(10);
		
		JButton btnNewButton = new JButton("Actualiser");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualiser();
			}
		});
		btnNewButton.setBounds(10, 417, 294, 23);
		contentPane.add(btnNewButton);
		
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.parseInt(String.valueOf(tableFamille.getValueAt(tableFamille.getSelectedRow(), 0)));
				if (JOptionPane.showConfirmDialog(null, "Confirmer", "Attention", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					db.delete("Famille_Article", "idFamille_Article = " +i);	
				}
				else {
					return;
				}
				actualiser();
			}
		});
		btnSupprimer.setBounds(10, 383, 294, 23);
		contentPane.add(btnSupprimer);
		
		btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nomFamille.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "information incomplete");
				} else {
					String [] nomColonne = {"nom_famille"};
					String [] contenu = {nomFamille.getText()};
					int i = Integer.parseInt(String.valueOf(tableFamille.getValueAt(tableFamille.getSelectedRow(), 0)));
					db.update("Famille_Article", nomColonne, contenu, "idFamille_Article = '" +i+ "'");
					actualiser();
				}
			}
		});
		btnModifier.setBounds(10, 349, 294, 23);
		contentPane.add(btnModifier);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nomFamille.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "information incomplete");
				} else {
					String [] nomColonne = {"nom_famille"};
					String [] contenu = {nomFamille.getText()};
					db.insert("Famille_Article", nomColonne, contenu);
					actualiser();
				}
			}
		});
		btnAjouter.setBounds(10, 315, 294, 23);
		contentPane.add(btnAjouter);
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rechercher.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "SVP entrer qq chose");
				} else {
					model.setRowCount(0);
					if (rechercherPar.getSelectedItem().toString().equals("Id")) {
						int etat = Integer.parseInt(rechercher.getText());
						String query = "SELECT * FROM famille_article WHERE idFamille_Article LIKE '%" +etat+ "%'";
						{
							rs = db.executeQuery(query);
						}				
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getString("idFamille_Article"),
										rs.getString("nom_famille")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						tableFamille.setModel(model);
					} else {
						String etat = rechercher.getText();
						String query = "SELECT * FROM famille_article WHERE nom_famille LIKE '%" +etat+ "%'";
						{
							rs = db.executeQuery(query);
						}				
						try {
							while (rs.next()) {
								model.addRow(new Object [] { rs.getString("idFamille_Article"),
										rs.getString("nom_famille")
								});
							}
						} catch(Exception e1) {
							System.err.println(e1.getMessage());
						}
						tableFamille.setModel(model);
					}
				}
			}
		});
		btnRechercher.setBounds(10, 281, 108, 23);
		contentPane.add(btnRechercher);
		
		rechercher = new JTextField();
		rechercher.setColumns(10);
		rechercher.setBounds(128, 282, 176, 20);
		contentPane.add(rechercher);
		
		lblRechercherPar = new JLabel("Rechercher par");
		lblRechercherPar.setHorizontalAlignment(SwingConstants.CENTER);
		lblRechercherPar.setBounds(10, 256, 108, 14);
		contentPane.add(lblRechercherPar);
		
		btnTrierPar = new JButton("Trier par");
		btnTrierPar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String etat = "";
				if(trierPar.getSelectedItem().toString().equals("Id")) {
					etat = "idFamille_Article";
				} else {
					etat = "nom_famille";
				}
				model.setRowCount(0);
				String query = "SELECT * FROM famille_article order by " +etat;
				{
					rs = db.executeQuery(query);
				}				
				try {
					while (rs.next()) {
						model.addRow(new Object [] { rs.getString("idFamille_Article"),
								rs.getString("nom_famille")
						});
					}
				} catch(Exception e1) {
					System.err.println(e1.getMessage());
				}
				tableFamille.setModel(model);
			}
		});
		btnTrierPar.setBounds(10, 222, 108, 23);
		contentPane.add(btnTrierPar);
		
		trierPar = new JComboBox();
		trierPar.setModel(new DefaultComboBoxModel(new String[] {"Id", "Nom famille"}));
		trierPar.setBounds(128, 222, 176, 22);
		contentPane.add(trierPar);
		
		rechercherPar = new JComboBox();
		rechercherPar.setModel(new DefaultComboBoxModel(new String[] {"Id", "Nom famille"}));
		rechercherPar.setBounds(128, 249, 176, 22);
		contentPane.add(rechercherPar);
		
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Menu frame = new Menu();
				frame.setVisible(true);
			}
		});
		btnRetour.setBounds(10, 451, 294, 23);
		contentPane.add(btnRetour);
		
		table();
	}
	
	public void table() {
		model.setRowCount(0);
		rs = db.selectAll("famille_article");
		try {
			while (rs.next()) {
				model.addRow(new Object [] { rs.getString("idFamille_Article"),
						rs.getString("nom_famille")
				});
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		tableFamille.setModel(model);
	}
	
	public void actualiser () {
		table();
		rechercher.setText("");
		nomFamille.setText("");
	}
}
