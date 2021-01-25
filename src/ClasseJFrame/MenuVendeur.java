package ClasseJFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuVendeur extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuVendeur frame = new MenuVendeur();
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
	public MenuVendeur() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCommander = new JButton("Placer une commande");
		btnCommander.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Commande frame = new Commande();
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnCommander.setBounds(10, 11, 217, 23);
		contentPane.add(btnCommander);
		
		JButton btnProforma = new JButton("Commande");
		btnProforma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BonCommande frame = new BonCommande();
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnProforma.setBounds(20, 159, 414, 23);
		contentPane.add(btnProforma);
		
		JButton Client = new JButton("Article");
		Client.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Article frame = new Article();
				frame.setVisible(true);
				setVisible(false);
			}
		});
		Client.setBounds(20, 193, 414, 23);
		contentPane.add(Client);
		
		JButton btnClient = new JButton("Client");
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client frame = new Client();
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnClient.setBounds(20, 227, 414, 23);
		contentPane.add(btnClient);
	}

}
