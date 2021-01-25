package ClasseJFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ClasseJava.BaseDeDonnees;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JDesktopPane;
import javax.swing.JCheckBox;

public class Commande extends JFrame {

	private JPanel contentPane;
	private JTable tableArticle;
	private JLabel prix;
	private JLabel total;
	private JLabel article;
	private JSpinner quantite;
	private JComboBox rechercherPar;
	private JComboBox trierPar;
	
	private ResultSet rs;
	private BaseDeDonnees db;
	private DefaultTableModel model = new DefaultTableModel();
	private DefaultTableModel model3 = new DefaultTableModel();
	private String idArticle = "";
	private ArrayList <String> listArticle = new ArrayList();
	private ArrayList <Integer> listQuantite = new ArrayList();
	private double compter = 0;
	private double montant = 0;
	public static String idCom = "";
	private JTable tableCommande;
	private JTextField recherche;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Commande frame = new Commande();
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
	public Commande() {
		/*setPreferredSize(new Dimension(1000, 700));
		setVisible(true);
		setMinimumSize(new Dimension(1000, 700));
		setMaximumSize(new Dimension(1000, 700));*/
		db = new BaseDeDonnees ();
		
		Color couleur_back =Color.decode("#F2F3F4");
		Color couleur1 = Color.decode("#17202A");
		Color couleur2 = Color.decode("#1C98CF");
		Color entete = Color.decode("#083346");
		Color vert =Color.decode("#27AE60");
		Color rouge =Color.decode("#D32222");
		Color orange =Color.decode("#E67E22");
		Color mauve =Color.decode("#6C3483");
		
		model.addColumn("Id");
		model.addColumn("Libellé");
		model.addColumn("Prix unitaire");
		model.addColumn("Date de création");
		model.addColumn("Quantité en stock");
		model.addColumn("Famille");	
		
		model3.addColumn("Libellé");
		model3.addColumn("Prix unitaire");
		model3.addColumn("Quantité");
		model3.addColumn("Total");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(183, 0, 1000, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(100, 192, 800, 201);
		contentPane.add(scrollPane_1);
		
		tableArticle = new JTable();
		tableArticle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				trierPar.setSelectedItem(String.valueOf(tableArticle.getValueAt(tableArticle.getSelectedRow(), 5)));
				idArticle = tableArticle.getValueAt(tableArticle.getSelectedRow(), 0).toString();
				article.setText(String.valueOf(tableArticle.getValueAt(tableArticle.getSelectedRow(), 1)));
				prix.setText(String.valueOf(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2)));
				montant = Double.parseDouble(prix.getText());
				compter = Double.parseDouble(String.valueOf(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2)));
				int max = Integer.parseInt(String.valueOf(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4)));
				quantite.setModel(new SpinnerNumberModel(1, 1, max, 1));
			}
		});
		scrollPane_1.setViewportView(tableArticle);
		tableArticle.setSelectionForeground(Color.WHITE);
		tableArticle.setSelectionBackground(couleur2);
		tableArticle.setRowHeight(30);
		tableArticle.setShowGrid(false);
		
		JLabel lblArticle = new JLabel("Article");
		lblArticle.setHorizontalAlignment(SwingConstants.LEFT);
		lblArticle.setBounds(100, 405, 96, 20);
		contentPane.add(lblArticle);
		
		quantite = new JSpinner();
		quantite.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				prix.setText("" +Integer.parseInt(quantite.getValue().toString())*montant);
			}
		});
		quantite.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		quantite.setModel(new SpinnerNumberModel(0, 0, 0, 1));
		quantite.setBounds(532, 405, 88, 20);
		contentPane.add(quantite);
		
		JLabel lblMontant = new JLabel("Montant");
		lblMontant.setHorizontalAlignment(SwingConstants.LEFT);
		lblMontant.setBounds(100, 436, 96, 20);
		contentPane.add(lblMontant);
		
		JLabel lblNature = new JLabel("Nature");
		lblNature.setHorizontalAlignment(SwingConstants.LEFT);
		lblNature.setBounds(299, 437, 96, 20);
		contentPane.add(lblNature);
		
		JComboBox nature = new JComboBox();
		nature.setModel(new DefaultComboBoxModel(new String[] {"Facture", "Proforma"}));
		nature.setBounds(405, 436, 113, 22);
		contentPane.add(nature);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(article.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "information incomplète");
				} else {
					if(tableCommande.getRowCount() == 0)
					{
						listArticle.add(idArticle);
						listQuantite.add(Integer.parseInt(quantite.getValue().toString()));
						//model3.setRowCount(0);
						model3.addRow(new Object [] {
							article.getText(),
							compter,
							quantite.getValue().toString(),
							prix.getText()
						});
						article.setText("");
						tableCommande.setModel(model3);
						total.setText("" +(Double.parseDouble(total.getText()) + Double.parseDouble(prix.getText())));
						quantite.setValue(0);			
					}else {
						String comparaison = String.valueOf(tableArticle.getValueAt(tableArticle.getSelectedRow(), 1));
						int i = 1;
						while(i < tableCommande.getRowCount() && !comparaison.equals(String.valueOf(tableCommande.getValueAt(i-1, 0)))){
							i++;
						}
						if(i == tableCommande.getRowCount() && comparaison.equals(String.valueOf(tableCommande.getValueAt(i-1, 0)))) {
							int j = listQuantite.get(i-1);
							int k = j+Integer.parseInt(quantite.getValue().toString());
							double l = k*Double.parseDouble(String.valueOf(tableCommande.getValueAt(i-1, 1)));
							double m = Integer.parseInt(quantite.getValue().toString())*Double.parseDouble(String.valueOf(tableCommande.getValueAt(i-1, 1)));
							listQuantite.add(i-1, k);
							model3.setValueAt(k, i-1, 2);
							model3.setValueAt(l, i-1, 3);
							tableCommande.setModel(model3);
							article.setText("");
							tableCommande.setModel(model3);
							total.setText("" +(Double.parseDouble(total.getText()) + m));
							quantite.setValue(0);
						} else if (comparaison.equals(String.valueOf(tableCommande.getValueAt(i-1, 0)))) {
							int j = listQuantite.get(i-1);
							int k = j+Integer.parseInt(quantite.getValue().toString());
							double l = k*Double.parseDouble(String.valueOf(tableCommande.getValueAt(i-1, 1)));
							double m = Integer.parseInt(quantite.getValue().toString())*Double.parseDouble(String.valueOf(tableCommande.getValueAt(i-1, 1)));
							listQuantite.add(i-1, k);
							model3.setValueAt(k, i-1, 2);
							model3.setValueAt(l, i-1, 3);
							tableCommande.setModel(model3);
							article.setText("");
							tableCommande.setModel(model3);
							total.setText("" +(Double.parseDouble(total.getText()) + m));
							quantite.setValue(0);
						} else {
							listArticle.add(idArticle);
							listQuantite.add(Integer.parseInt(quantite.getValue().toString()));
							//model3.setRowCount(0);
							model3.addRow(new Object [] {
								article.getText(),
								compter,
								quantite.getValue().toString(),
								prix.getText()
							});
							article.setText("");
							tableCommande.setModel(model3);
							total.setText("" +(Double.parseDouble(total.getText()) + Double.parseDouble(prix.getText())));
							quantite.setValue(0);		
						}
					}
				}
			}
		});
		btnNewButton.setBounds(630, 404, 88, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Commander");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableCommande.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "information incomplete");
				}else {
					ClientVendeur frame = new ClientVendeur(total.getText(), nature.getSelectedItem().toString(), listArticle, listQuantite);
					frame.setVisible(true);
					model3.setRowCount(0);
					tableCommande.setModel(model3);
					total.setText("0.0");
				}
				
			}
		});
		btnNewButton_1.setBounds(100, 695, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Annuler");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listArticle.clear();
				listQuantite.clear();
				model3.setRowCount(0);
				article.setText("");
				tableCommande.setModel(model3);
				total.setText("0.0");
				quantite.setValue(0);
			}
		});
		btnNewButton_1_1.setBounds(200, 695, 89, 23);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_2 = new JButton("Bon de commande");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BonCommande frame1 = new BonCommande();
				frame1.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(299, 695, 148, 23);
		contentPane.add(btnNewButton_2);
		
		prix = new JLabel("0");
		prix.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		prix.setHorizontalAlignment(SwingConstants.RIGHT);
		prix.setBounds(206, 436, 79, 20);
		contentPane.add(prix);
		
		article = new JLabel("");
		article.setBorder(new LineBorder(new Color(0, 0, 0)));
		article.setHorizontalAlignment(SwingConstants.LEFT);
		article.setBounds(206, 405, 316, 20);
		contentPane.add(article);
		
		JButton btnNewButton_2_1 = new JButton("Bon de livraison");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BonLivraison frame1 = new BonLivraison();
				frame1.setVisible(true);
			}
		});
		btnNewButton_2_1.setBounds(459, 695, 148, 23);
		contentPane.add(btnNewButton_2_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(100, 483, 800, 201);
		contentPane.add(scrollPane_2);
		
		tableCommande = new JTable();
		scrollPane_2.setViewportView(tableCommande);
		tableCommande.setSelectionForeground(Color.WHITE);
		tableCommande.setSelectionBackground(couleur2);
		tableCommande.setRowHeight(30);
		tableCommande.setShowGrid(false);
		
		JLabel lblTotal = new JLabel("Grand total");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setBounds(715, 698, 96, 20);
		contentPane.add(lblTotal);
		
		total = new JLabel("0.0");
		total.setHorizontalAlignment(SwingConstants.RIGHT);
		total.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		total.setBounds(821, 698, 79, 20);
		contentPane.add(total);
		
		JPanel panel = new JPanel();
		panel.setBackground(entete);
		panel.setBounds(0, 0, 1000, 88);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 150, 88);
		lblNewLabel_1.setIcon(new ImageIcon(this.getClass().getResource("/logo2.png")));
		panel.add(lblNewLabel_1);
		
		JLabel lblGestionArticle = new JLabel("VENTE");
		lblGestionArticle.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionArticle.setForeground(Color.WHITE);
		lblGestionArticle.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 40));
		lblGestionArticle.setBounds(402, 31, 305, 48);
		panel.add(lblGestionArticle);
		
		rechercherPar = new JComboBox();
		rechercherPar.setModel(new DefaultComboBoxModel(new String[] {"Code produit", "Libell\u00E9", "Prix", "Date", "Quantit\u00E9", "Cat\u00E9gorie"}));
		rechercherPar.setBounds(100, 122, 146, 26);
		contentPane.add(rechercherPar);
		
		recherche = new JTextField();
		recherche.setText("Recherche");
		recherche.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				recherche.setText("");
				recherche.setForeground(couleur1);
			}
		});
		recherche.setForeground(Color.decode("#D0D3D4"));
		recherche.setFont(new Font("Dialog", Font.BOLD, 12));
		recherche.setColumns(10);
		recherche.setBounds(271, 124, 268, 23);
		contentPane.add(recherche);
		
		JButton btnRecherche = new JButton("Recherche");
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (recherche.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "SVP entrer qq chose");
				} else {
					model.setRowCount(0);
					String etat = recherche.getText();
					if (rechercherPar.getSelectedItem().toString().equals("Code produit")) {
						tableRechercher("id_Article", etat);						
					} else if (rechercherPar.getSelectedItem().toString().equals("Libellé")) {
						tableRechercher("libelle_article", etat);
					} else if (rechercherPar.getSelectedItem().toString().equals("Prix")) {
						tableRechercher("prix_unitaire", etat);
					} else if (rechercherPar.getSelectedItem().toString().equals("Date")) {
						tableRechercher("date_creation", etat);
					} else if (rechercherPar.getSelectedItem().toString().equals("Quantité")) {
						tableRechercher("qte_en_stock", etat);
					} else {
						tableRechercher("nom_famille", etat);
					}
				}
			}
		});
		btnRecherche.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				recherche.setForeground(Color.decode("#D0D3D4"));
				recherche.setText("Rechercher par");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				//btnRecherche.setBackground(Color.decode("#EAECEE"));
				btnRecherche.setIcon(new ImageIcon(this.getClass().getResource("/search3.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRecherche.setIcon(new ImageIcon(this.getClass().getResource("/search4.png")));
			}
		});
		
		btnRecherche.setForeground(Color.WHITE);
		btnRecherche.setBorderPainted(false);
		btnRecherche.setBackground(couleur_back);
		btnRecherche.setIcon(new ImageIcon(this.getClass().getResource("/search4.png")));
		btnRecherche.setBounds(540, 118, 67, 34);
		contentPane.add(btnRecherche);
		
		JLabel lblTrierPar = new JLabel("Trier par");
		lblTrierPar.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 16));
		lblTrierPar.setForeground(couleur1);
		lblTrierPar.setBounds(625, 124, 70, 23);
		contentPane.add(lblTrierPar);
		
		trierPar = new JComboBox();
		trierPar.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String etat = "";
				if(trierPar.getSelectedItem().toString().equals("Id")) {
					etat = "id_Article";
				} else if(trierPar.getSelectedItem().toString().equals("Libellé")){
					etat = "libelle_article";
				}
				else if(trierPar.getSelectedItem().toString().equals("Prix")){
					etat = "prix_unitaire";
				}
				else if(trierPar.getSelectedItem().toString().equals("Date")){
					etat = "date_creation";
				}
				else if(trierPar.getSelectedItem().toString().equals("Quantité")){
					etat = "qte_en_stock";
				}
				else {
					etat = "nom_famille";
				}
				model.setRowCount(0);
				String query = "SELECT * FROM article, famille_article WHERE article.idFamille_Article = famille_article.idFamille_Article AND qte_en_stock > 0 order by " +etat;
				rs = db.executeQuery(query);
				try {
					while (rs.next()) {
						model.addRow(new Object [] { rs.getInt("id_Article"),
								rs.getString("libelle_article"),
								rs.getDouble("prix_unitaire"),
								rs.getString("date_creation"),
								rs.getInt("qte_en_stock"),
								rs.getString("nom_famille")
						});
					}
				} catch(Exception e1) {
					System.err.println(e1.getMessage());
				}
				tableArticle.setModel(model);
			}
		});
		trierPar.setModel(new DefaultComboBoxModel(new String[] {"Code produit", "Libell\u00E9", "Prix", "Date", "Quantit\u00E9", "Cat\u00E9gorie"}));
		
		trierPar.setBounds(713, 124, 140, 23);
		
		contentPane.add(trierPar);

		tableArticle();
		tableCommande();
	}
	
	public void tableArticle () {
		model.setRowCount(0);
		String query = "SELECT * FROM article, famille_article WHERE article.idFamille_Article = famille_article.idFamille_Article AND article.qte_en_stock>0";
		rs = db.executeQuery(query);
		try {
			while (rs.next()) {
				model.addRow(new Object [] { rs.getInt("id_Article"),
						rs.getString("libelle_article"),
						rs.getDouble("prix_unitaire"),
						rs.getString("date_creation"),
						rs.getInt("qte_en_stock"),
						rs.getString("nom_famille")
				});
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		tableArticle.setModel(model);
	}
	
	public void tableCommande () {
		model3.setRowCount(0);
		tableCommande.setModel(model3);
	}
	
	public void tableRechercher(String recherche, String etat) {
		String query = "SELECT * FROM article, famille_article WHERE article.idFamille_Article = famille_article.idFamille_Article AND "+recherche+" LIKE '%" +etat+ "%'";
		rs = db.executeQuery(query);	
		try {
			while (rs.next()) {
				model.addRow(new Object [] { rs.getInt("id_Article"),
						rs.getString("libelle_article"),
						rs.getDouble("prix_unitaire"),
						rs.getString("date_creation"),
						rs.getInt("qte_en_stock"),
						rs.getString("nom_famille")
				});
			}
		} catch(Exception e1) {
			System.err.println(e1.getMessage());
		}
		tableArticle.setModel(model);
	}
}
