package ClasseJFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ArticleAjout extends Boutton {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArticleAjout frame = new ArticleAjout();
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
	public ArticleAjout() {
		super();
		
		/*setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);*/
		contentPane = new JPanel();
		super.contentPane.add(contentPane);
		/*contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);*/
		
		//contentPane.add(super.btnEnregistrer);
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
		super.contentPane.add(lblLibell);
	}

}
