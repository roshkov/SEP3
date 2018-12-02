package tier1.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class Tier1MovieCreatorGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tier1MovieCreatorGUI window = new Tier1MovieCreatorGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tier1MovieCreatorGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Stefan\\Desktop\\'O'.jpg"));
		frame.getContentPane().setBackground(new Color(105, 105, 105));
		frame.getContentPane().setLayout(null);
		
		JButton createMovie = new JButton("Create Movie");
		createMovie.setBounds(37, 108, 126, 73);
		frame.getContentPane().add(createMovie);
		
		JButton btnNewButton_1 = new JButton("List Movies");
		btnNewButton_1.setBounds(229, 108, 126, 73);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Quit");
		btnNewButton_2.setBounds(425, 108, 126, 73);
		frame.getContentPane().add(btnNewButton_2);
		frame.setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 600, 357);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
