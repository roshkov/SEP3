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
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import tier1.controller.Tier1MovieCreatorController;

/**
 * Used to create a GUI for the user to interact with our system
 * @author Stefan
 *
 */
public class Tier1MovieCreatorGUI implements Tier1MovieCreatorView, Runnable {

	/**
	 * The view has access to the controller in order to give it specific commands when the user interacts with the GUI
	 * @see Tier1MovieCreatorController#execute(int)
	 */
	private Tier1MovieCreatorController controller;
	
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public Tier1MovieCreatorGUI() {
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Stefan\\Desktop\\'O'.jpg"));
		frame.getContentPane().setBackground(new Color(105, 105, 105));
		frame.getContentPane().setLayout(null);
		
		JButton createMovie = new JButton("Create Movie");
		createMovie.setBackground(new Color(255, 145, 164));
		createMovie.setContentAreaFilled(false);
        createMovie.setOpaque(true);
		createMovie.setBounds(37, 108, 126, 73);
		createMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                  createMovieActionPerformed(evt);
            }
        });
		frame.getContentPane().add(createMovie);
		
		JButton listMovies = new JButton("List Movies");
		listMovies.setBackground(new Color(255, 145, 164));
		listMovies.setContentAreaFilled(false);
		listMovies.setOpaque(true);
		listMovies.setBounds(229, 108, 126, 73);
		frame.getContentPane().add(listMovies);
		listMovies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listMoviesActionPerformed(evt);
          }
      });
		
		JButton quitButton = new JButton("Quit");
		quitButton.setBackground(new Color(255, 145, 164));
		quitButton.setContentAreaFilled(false);
		quitButton.setOpaque(true);
		quitButton.setBounds(425, 108, 126, 73);
		frame.getContentPane().add(quitButton);
		quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
          }
      });
		
		frame.setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 600, 357);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Tells the controller to ask the user for the data required to create a Movie
	 * @see common.Movie
	 * @see Tier1MovieCreatorController#execute(int)
	 * @param evt
	 */
	private void createMovieActionPerformed(java.awt.event.ActionEvent evt) {
		controller.execute(1);	
	}
	
	/**
	 * Tells the controller to display the movies to the user
	 * @param evt
	 */
	private void listMoviesActionPerformed(java.awt.event.ActionEvent evt) {
		controller.execute(2);	
	}
	
	/**
	 * Tells the controller to exit the program
	 * @param evt
	 */
	private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {
		controller.execute(0);	
	}
	
	@Override
	public String get(String string) {
		return (String)JOptionPane.showInputDialog(string);
	}

	@Override
	public void show(String text) {
		JOptionPane.showMessageDialog(null, text);	
	}

	@Override
	public void startView(Tier1MovieCreatorController controller) {
		this.controller = controller;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		initialize();
		this.frame.setVisible(true);
	}
}
