package ClasseJFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Result;

import ClasseJava.BaseDeDonnees;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Client extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_Client;
	private JTextField rechercher;
	private JComboBox trierPar;
	private JComboBox rechercherPar;
	private ResultSet rs;
	private BaseDeDonnees db;
	private DefaultTableModel model = new DefaultTableModel();
	
	private String id;
	private String nom;
	private String prenom;
	private String tel;
	private String adresse;
	private String email;
	
	Color couleur_back =Color.decode("#F2F3F4");
	Color couleur1 = Color.decode("#17202A");
	Color couleur2 = Color.decode("#1C98CF");
	Color entete = Color.decode("#083346");
	Color vert =Color.decode("#27AE60");
	Color rouge =Color.decode("#D32222");
	Color orange =Color.decode("#E67E22");
	Color mauve =Color.decode("#6C3483");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
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
	public Client() {
		setMaximumSize(new Dimension(1000, 700));
		setMinimumSize(new Dimension(1000, 700));
		setResizable(false);
		db = new BaseDeDonnees ();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(183, 0, 1000, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		model.addColumn("Id");
		model.addColumn("Nom");
		model.addColumn("Prénom");
		model.addColumn("Téléphone");
		model.addColumn("Adresse");
		model.addColumn("Email");
		model.setRowCount(0);
		rs = db.selectAll("client");
		try {
			while (rs.next()) {
				model.addRow(new Object [] { rs.getString("idClient"),
						rs.getString("nom_client"),
						rs.getString("prenom_client"),
						rs.getString("tel_client"),
						rs.getString("adresse_client"),
						rs.getString("email_client")
				});
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
		JScrollPane tableClient = new JScrollPane();
		tableClient.setFont(new Font("Dialog", Font.BOLD, 12));
		tableClient.setBackground(Color.WHITE);
		tableClient.setForeground(Color.DARK_GRAY);
		tableClient.setBounds(100, 192, 800, 343);
		contentPane.add(tableClient);
		
		table_Client = new JTable();
		table_Client.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 0));
				nom = String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 1));
				prenom = String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 2));
				tel = String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 3));
				adresse = String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 4));
				email = String.valueOf(table_Client.getValueAt(table_Client.getSelectedRow(), 5));
			}
		});
		table_Client.setSelectionForeground(Color.WHITE);
		table_Client.setSelectionBackground(couleur2);
		table_Client.setRowHeight(30);
		table_Client.setShowGrid(false);
		table_Client.setModel(model);
		tableClient.setViewportView(table_Client);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setBackground(Color.decode("#EAECEE"));
				btnNewButton.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 22));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setBackground(couleur_back);
				btnNewButton.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
			}
		});
		btnNewButton.setBorderPainted(false);
		btnNewButton.setSelected(true);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/add6.png")));
		btnNewButton.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
		btnNewButton.setBackground(couleur_back);
		btnNewButton.setForeground(couleur2);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientAjouter frame = new ClientAjouter();
				frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(76, 577, 154, 42);
		contentPane.add(btnNewButton);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBorderPainted(false);
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
		btnModifier.setIcon(new ImageIcon(this.getClass().getResource("/modify2.png")));
		btnModifier.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
		btnModifier.setBackground(couleur_back);
		btnModifier.setForeground(orange);
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id == null) {
					JOptionPane.showMessageDialog(null, "Choisissez un client");
				} else {
					ClientModifier frame = new ClientModifier(id, nom, prenom, tel, adresse, email);
					frame.setVisible(true);
				}
			}
		});
		btnModifier.setBounds(268, 577, 167, 42);
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
		btnSupprimer.setIcon(new ImageIcon(this.getClass().getResource("/delete3.png")));
		btnSupprimer.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 20));
		btnSupprimer.setBorderPainted(false);
		btnSupprimer.setBackground(couleur_back);
		btnSupprimer.setForeground(rouge);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id == null) {
					JOptionPane.showMessageDialog(null, "Choisissez un client");
				} else {
					ClientSupprimer frame = new ClientSupprimer(id, nom, prenom, tel, adresse, email);
					frame.setVisible(true);
				}
			}
		});
		btnSupprimer.setBounds(470, 579, 194, 39);
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
		
		trierPar = new JComboBox();
		trierPar.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String etat = "";
				if(trierPar.getSelectedItem().toString().equals("Id")) {
					etat = "idClient";
				} else if(trierPar.getSelectedItem().toString().equals("Nom")){
					etat = "nom_client";
				}
				else if(trierPar.getSelectedItem().toString().equals("Prénom")){
					etat = "prenom_client";
				}
				else if(trierPar.getSelectedItem().toString().equals("Téléphone")){
					etat = "tel_client";
				}
				else if(trierPar.getSelectedItem().toString().equals("Adresse")){
					etat = "adresse_client";
				}
				else {
					etat = "email_client";
				}
				model.setRowCount(0);
				String query = "SELECT * FROM client order by " +etat;
				{
					rs = db.executeQuery(query);
				}				
				try {
					while (rs.next()) {
						model.addRow(new Object [] { rs.getString("idClient"),
								rs.getString("nom_client"),
								rs.getString("prenom_client"),
								rs.getString("tel_client"),
								rs.getString("adresse_client"),
								rs.getString("email_client"),
						});
					}
				} catch(Exception e1) {
					System.err.println(e1.getMessage());
				}
				table_Client.setModel(model);
			}
		});
		trierPar.setModel(new DefaultComboBoxModel(new String[] {"Id", "Nom", "Pr\u00E9nom", "T\u00E9l\u00E9phone", "Adresse\t", "Email"}));
		trierPar.setBounds(713, 124, 140, 23);
		contentPane.add(trierPar);
		
		rechercherPar = new JComboBox();
		rechercherPar.setModel(new DefaultComboBoxModel(new String[] {"Id", "Nom", "Pr\u00E9nom", "T\u00E9l\u00E9phone", "Adresse\t", "Email"}));
		rechercherPar.setBounds(100, 122, 146, 26);
		contentPane.add(rechercherPar);
		
		rechercher = new JTextField();
		rechercher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rechercher.setText("");
				rechercher.setForeground(couleur1);
			}
		});
		rechercher.setForeground(Color.decode("#D0D3D4"));
		rechercher.setFont(new Font("Dialog", Font.BOLD, 12));
		rechercher.setText("Rechercher par");
		rechercher.setBounds(271, 124, 268, 23);
		contentPane.add(rechercher);
		rechercher.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(entete);
		panel.setBounds(0, 0, 1000, 88);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 150, 88);
		lblNewLabel.setIcon(new ImageIcon(this.getClass().getResource("/logo2.png")));
		panel.add(lblNewLabel);
		
		JLabel lblGestionArticle = new JLabel("GESTION CLIENT");
		lblGestionArticle.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionArticle.setForeground(Color.WHITE);
		lblGestionArticle.setFont(new Font("Noto Sans CJK JP", Font.BOLD, 40));
		lblGestionArticle.setBounds(402, 31, 305, 48);
		panel.add(lblGestionArticle);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 24, 158, 95);
		panel.add(lblNewLabel_1);
		//lblNewLabel_1.setIcon(new ImageIcon("/home/valthebest/Pictures/logo2.png"));
		lblNewLabel_1.setIcon(new ImageIcon(this.getClass().getResource("/logo2.png")));
		
		JButton btnRecherche = new JButton("Recherche");
		btnRecherche.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rechercher.setForeground(Color.decode("#D0D3D4"));
				rechercher.setText("Rechercher par");
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
		btnRecherche.setIcon(new ImageIcon(this.getClass().getResource("/search4.png")));
		btnRecherche.setForeground(Color.WHITE);
		btnRecherche.setBorderPainted(false);
		btnRecherche.setBackground(new Color(242, 243, 244));
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rechercher.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "SVP entrer qq chose");
				} else {
					if (rechercherPar.getSelectedItem().toString().equals("Id")) {
						String etat = rechercher.getText();
						tableRechercher("idClient", etat);
					} else if (rechercherPar.getSelectedItem().toString().equals("Nom")) {
						String etat = rechercher.getText();
						tableRechercher("nom_client", etat);
					} else if (rechercherPar.getSelectedItem().toString().equals("Prénom")) {
						String etat = rechercher.getText();
						tableRechercher("prenom_client", etat);
					} else if (rechercherPar.getSelectedItem().toString().equals("Téléphone")) {
						String etat = rechercher.getText();
						tableRechercher("tel_client", etat);
					} else if (rechercherPar.getSelectedItem().toString().equals("Adresse")) {
						String etat = rechercher.getText();
						tableRechercher("adresse_client", etat);
					} else {
						String etat = rechercher.getText();
						tableRechercher("email_client", etat);
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
		
		/*JButton btnNewButton_2 = new JButton("Retour");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//setVisible(false);
				dispose();
				Menu frame = new Menu();
				frame.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(824, 591, 140, 23);
		contentPane.add(btnNewButton_2);*/
		
		//actualiser();
		
	}	
	public void actualiser () {
		model.setRowCount(0);
		rs = db.selectAll("client");
		try {
			while (rs.next()) {
				model.addRow(new Object [] { rs.getString("idClient"),
						rs.getString("nom_client"),
						rs.getString("prenom_client"),
						rs.getString("tel_client"),
						rs.getString("adresse_client"),
						rs.getString("email_client")
				});
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		table_Client.setModel(model);
		trierPar.setSelectedItem("Id");
		rechercherPar.setSelectedItem("Id");
		rechercher.setText("Rechercher par");
		rechercher.setForeground(Color.decode("#D0D3D4"));
		rechercher.setFont(new Font("Dialog", Font.BOLD, 12));
	}
	
	public void tableRechercher(String recherche, String etat) {
		model.setRowCount(0);
		String query = "SELECT * FROM client WHERE " +recherche+" LIKE '%" +etat+ "%'";
		rs = db.executeQuery(query);	
		try {
			while (rs.next()) {
				model.addRow(new Object [] { rs.getString("idClient"),
						rs.getString("nom_client"),
						rs.getString("prenom_client"),
						rs.getString("tel_client"),
						rs.getString("adresse_client"),
						rs.getString("email_client")
				});
			}
		} catch(Exception e1) {
			System.err.println(e1.getMessage());
		}
		table_Client.setModel(model);
	}
}
