package ClasseJFrame;

import ClasseJava.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class Log_in extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JTextField pass;
	private ResultSet rs;
	private BaseDeDonnees db;
	private String username;
	private String password;
	

	/**
	 * Create the frame.
	 */
	public Log_in() {
		db = new BaseDeDonnees();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		user = new JTextField();
		user.setBounds(163, 55, 217, 20);
		contentPane.add(user);
		user.setColumns(10);
		
		pass = new JTextField();
		pass.setColumns(10);
		pass.setBounds(163, 103, 217, 20);
		contentPane.add(pass);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(27, 61, 79, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(27, 109, 79, 14);
		contentPane.add(lblPassword);
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rs = db.selectAll("employe", "username = '" +user.getText()+"' AND password = '" +pass.getText()+ "'");
				try {
					rs.next();
					if(rs.getString("Poste").equals("Administrateur")) {
						Menu frame = new Menu();
						frame.setVisible(true);
						setVisible(false);
					} else if(rs.getString("Poste").equals("Vendeur")) {
						MenuVendeur frame = new MenuVendeur();
						frame.setVisible(true);
						setVisible(false);
					} else {
						BonLivraison frame = new BonLivraison(); 
						frame.setVisible(true);
						setVisible(false);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Reessayer");
				}
			}
		});
		btnNewButton.setBounds(163, 156, 127, 23);
		contentPane.add(btnNewButton);
	}
}
