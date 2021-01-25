package ClasseJFrame;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu1 extends JPanel {

	/**
	 * Create the panel.
	 */
	public Menu1() {
		setLayout(null);
		
		JButton btnNewButton = new JButton("Client");
		btnNewButton.setBounds(10, 11, 414, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Famille d'article");
		btnNewButton_1.setBounds(10, 45, 414, 23);
		add(btnNewButton_1);
		
		JButton Client = new JButton("Article");
		Client.setBounds(10, 79, 414, 23);
		add(Client);
		
		JButton btnCommander = new JButton("Commander");
		btnCommander.setBounds(10, 113, 414, 23);
		add(btnCommander);
		
		JButton btnRapport = new JButton("Rapport");
		btnRapport.setBounds(10, 147, 414, 23);
		add(btnRapport);

	}
}
