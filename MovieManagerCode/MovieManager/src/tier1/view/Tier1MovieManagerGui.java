package tier1.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import tier1.controller.Tier1MovieManagerController;

public class Tier1MovieManagerGui implements Tier1MovieManagerView{

	private JFrame frmMovieManagerGui;
	private Tier1MovieManagerController controller;
	private JTextArea movies;
	private JTextField movieId;
	private JTextField availableMovies;
	private JTextField insertMovieId;

	/**
	 * Launch the application.
	 */
	@Override
	public void startView(Tier1MovieManagerController controller) {
		this.controller = controller;
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					//Tier1MovieManagerGui window = new Tier1MovieManagerGui();
					frmMovieManagerGui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		this.controller.execute(2);
	}

	/**
	 * Create the application.
	 */
	public Tier1MovieManagerGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMovieManagerGui = new JFrame();
		frmMovieManagerGui.setTitle("Movie Manager Gui");
		frmMovieManagerGui.getContentPane().setBackground(new Color(100,240,240));
		frmMovieManagerGui.getContentPane().setLayout(new BoxLayout(frmMovieManagerGui.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		frmMovieManagerGui.getContentPane().add(panel);
		panel.setLayout(null);
		
		movies = new JTextArea();
		movies.setEditable(false);
		movies.setLineWrap(true);
		movies.setBounds(0, 24, 675, 184);
		movies.setRows(1);
		movies.setColumns(1);
		panel.add(movies);
		
		movieId = new JTextField();
		movieId.setBounds(264, 261, 28, 22);
		movieId.setColumns(1);
		panel.add(movieId);

		
		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.execute(2);
			}
		});
		updateButton.setBounds(10, 221, 97, 25);
		panel.add(updateButton);
		
		
		
		JButton rentMovieButton = new JButton("Rent Movie");
		rentMovieButton.setEnabled(false);
		rentMovieButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				controller.execute(1);
			}
		});
		rentMovieButton.setBounds(304, 260, 97, 25);
		panel.add(rentMovieButton);
		
		availableMovies = new JTextField();
		availableMovies.setBackground(UIManager.getColor("Button.background"));
		availableMovies.setEditable(false);
		availableMovies.setBorder(null);
		availableMovies.setText("Available Movies:");
		availableMovies.setBounds(0, 0, 116, 22);
		panel.add(availableMovies);
		availableMovies.setColumns(10);
		
		
		insertMovieId = new JTextField();
		insertMovieId.setEditable(false);
		insertMovieId.setBorder(null);
		insertMovieId.setBackground(UIManager.getColor("Button.background"));
		insertMovieId.setText("Insert Movie Id:");
		insertMovieId.setBounds(155, 261, 97, 22);
		panel.add(insertMovieId);
		insertMovieId.setColumns(10);
		
		movieId.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void changedUpdate(DocumentEvent arg0) {
					// TODO Auto-generated method stub
					changed();
					
				}
				@Override
				public void insertUpdate(DocumentEvent arg0) {
					// TODO Auto-generated method stub
					changed();
				}
				@Override
				public void removeUpdate(DocumentEvent arg0) {
					// TODO Auto-generated method stub
					changed();
				}
				
				public void changed()
				{
					if (movieId.getText().equals("")) {
						rentMovieButton.setEnabled(false);
					}
					else {
						rentMovieButton.setEnabled(true);
					}
						
				}
		});
		frmMovieManagerGui.setBackground(Color.MAGENTA);
		frmMovieManagerGui.setBounds(100, 100, 693, 479);
		frmMovieManagerGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public String get() {
		return movieId.getText();
	}

	@Override
	public void showMovies(String text) {
		// TODO Auto-generated method stub
		movies.setText(text);
	}
	
	@Override
	public void showError() {
		JOptionPane.showMessageDialog(new JFrame(), "Invalid Input", "Error", JOptionPane.ERROR_MESSAGE);
	}
}
