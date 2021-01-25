package ClasseJFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ClasseJava.BaseDeDonnees;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JEditorPane;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Article extends JFrame {

	private JPanel contentPane;
	private JTable tableArticle;
	private JTextField recherche;
	private JComboBox trierPar;
	private JComboBox rechercherPar;
	
	private ResultSet rs;
	private BaseDeDonnees db;
	private DefaultTableModel model = new DefaultTableModel();
	private JTextField rechercherId;
	private String libelle;
	private String familleNom;
	private double prxUnit;
	private int quantite;
	private String id;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Article frame = new Article();
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
	public Article() {
		/*setPreferredSize(new Dimension(1000, 700));
		setVisible(true);
		setMinimumSize(new Dimension(1000, 700));
		setMaximumSize(new Dimension(1000, 700));*/
		
		
		db = new BaseDeDonnees ();
		model.addColumn("Id");
		model.addColumn("Libellé");
		model.addColumn("Prix unitaire");
		model.addColumn("Date de création");
		model.addColumn("Quantité en stock");
		model.addColumn("Catégorie");
		
		//couleur 
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
		contentPane.setMinimumSize(new Dimension(1000, 700));
		contentPane.setMaximumSize(new Dimension(1000, 700));
		
		contentPane.setBackground(couleur_back);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 192, 800, 340);
		contentPane.add(scrollPane);
		
		tableArticle = new JTable();

		tableArticle.setSelectionForeground(Color.WHITE);
		tableArticle.setSelectionBackground(couleur2);
		tableArticle.setRowHeight(30);
		tableArticle.setShowGrid(false);
		
		tableArticle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = String.valueOf(tableArticle.getValueAt(tableArticle.getSelectedRow(), 0));
				libelle = String.valueOf(tableArticle.getValueAt(tableArticle.getSelectedRow(), 1));
				prxUnit = Double.parseDouble(String.valueOf(tableArticle.getValueAt(tableArticle.getSelectedRow(), 2)));
				quantite = Integer.parseInt(String.valueOf(tableArticle.getValueAt(tableArticle.getSelectedRow(), 4)));
				familleNom = String.valueOf(tableArticle.getValueAt(tableArticle.getSelectedRow(), 5));	
			}
		});
		scrollPane.setViewportView(tableArticle);
		
		trierPar = new JComboBox();
		trierPar.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String etat = "";
				if(trierPar.getSelectedItem().toString().equals("Code produit")) {
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
				String query = "SELECT * FROM article, famille_article WHERE article.idFamille_Article = famille_article.idFamille_Article order by " +etat;
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
		
		/*JLabel lblNewLabel_2 = new JLabel("Rechercher par ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(365, 235, 146, 22);
		contentPane.add(lblNewLabel_2);*/
		
		rechercherPar = new JComboBox();
		rechercherPar.setModel(new DefaultComboBoxModel(new String[] {"Code produit", "Libell\u00E9", "Prix", "Date", "Quantit\u00E9", "Cat\u00E9gorie"}));
		
		rechercherPar.setBounds(100, 122, 146, 26);
		
		contentPane.add(rechercherPar);
		
		recherche = new JTextField();
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
		btnRecherche.setBounds(540, 118, 67, 34);
		contentPane.add(btnRecherche);
		
		JLabel lblTrierPar = new JLabel("Trier par");
		lblTrierPar.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 16));
		lblTrierPar.setForeground(couleur1);
		lblTrierPar.setBounds(625, 124, 70, 23);
		contentPane.add(lblTrierPar);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setBackground(couleur_back);
				btnNewButton.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setBackground(Color.decode("#EAECEE"));
				btnNewButton.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 22));
			}
		});
		
		btnNewButton.setSelected(true);
		btnNewButton.setForeground(new Color(28, 152, 207));
		btnNewButton.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(couleur_back);
		btnNewButton.setBounds(92, 558, 154, 42);
		contentPane.add(btnNewButton);
		btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/add6.png")));
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArticleAjouter frame = new ArticleAjouter();
				frame.setVisible(true);
				actualiser();
			}
		});
		btnNewButton.setBounds(78, 576, 154, 42);
		contentPane.add(btnNewButton);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnModifier.setBackground(Color.decode("#FDEBD0"));
				btnModifier.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 22));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnModifier.setBackground(couleur_back);
				btnModifier.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
			}
		});
		
		btnModifier.setSelected(true);
		btnModifier.setForeground(new Color(230, 126, 34));
		btnModifier.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
		btnModifier.setBorderPainted(false);
		btnModifier.setBackground(new Color(242, 243, 244));
		btnModifier.setBounds(271, 576, 167, 42);
		contentPane.add(btnModifier);
		btnModifier.setIcon(new ImageIcon(this.getClass().getResource("/modify2.png")));
		
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id == null) {
					JOptionPane.showMessageDialog(null, "Choisissez un article");
				} else {
					ArticleModifier frame = new ArticleModifier(id, libelle, prxUnit, quantite, familleNom);
					frame.setVisible(true);
				}
			}
		});
		btnModifier.setBounds(271, 576, 167, 42);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSupprimer.setBackground(Color.decode("#FDF2E9"));
				btnSupprimer.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 22));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSupprimer.setBackground(couleur_back);
				btnSupprimer.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
			}
		});
		btnSupprimer.setForeground(new Color(211, 34, 34));
		btnSupprimer.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
		btnSupprimer.setBorderPainted(false);
		btnSupprimer.setBackground(new Color(242, 243, 244));
		btnSupprimer.setBounds(501, 578, 194, 39);
		btnSupprimer.setIcon(new ImageIcon(this.getClass().getResource("/delete3.png")));
		contentPane.add(btnSupprimer);
		
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id == null) {
					JOptionPane.showMessageDialog(null, "Choisissez un article");
				} else {
					ArticleSupprimer frame = new ArticleSupprimer(id, libelle, prxUnit, quantite, familleNom);
					frame.setVisible(true);
				}
			}
		});
		btnSupprimer.setBounds(501, 576, 194, 42);
		contentPane.add(btnSupprimer);
		
		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnActualiser.setBackground(Color.decode("#EAECEE"));
				btnActualiser.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 22));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnActualiser.setBackground(couleur_back);
				btnActualiser.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
			}
		});
		btnActualiser.setIcon(new ImageIcon(this.getClass().getResource("/refresh1.png")));
		btnActualiser.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
		btnActualiser.setForeground(vert);
		btnActualiser.setBorderPainted(false);
		btnActualiser.setBackground(couleur_back);
		
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualiser();
			}
		});
		btnActualiser.setBounds(740, 576, 194, 42);
		contentPane.add(btnActualiser);
		
		JPanel panel = new JPanel();
		panel.setBackground(entete);
		panel.setBounds(0, 0, 1000, 88);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 150, 88);
		lblNewLabel.setIcon(new ImageIcon(this.getClass().getResource("/logo2.png")));
		panel.add(lblNewLabel);
		
		JLabel lblGestionArticle = new JLabel("PRODUIT");
		lblGestionArticle.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionArticle.setForeground(Color.WHITE);
		lblGestionArticle.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 40));
		lblGestionArticle.setBounds(402, 31, 305, 48);
		panel.add(lblGestionArticle);
		
		actualiser();
		
	}
	
	public void actualiser() {
		model.setRowCount(0);
		String query = "SELECT * FROM article, famille_article WHERE article.idFamille_Article = famille_article.idFamille_Article order by id_Article";
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
		trierPar.setSelectedItem("Id");
		rechercherPar.setSelectedItem("Id");
		tableArticle.setModel(model);
		recherche.setText("Rechercher par");
		recherche.setForeground(Color.decode("#D0D3D4"));
		recherche.setFont(new Font("Dialog", Font.BOLD, 12));
		
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
