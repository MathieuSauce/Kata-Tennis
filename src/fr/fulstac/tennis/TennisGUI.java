package fr.fulstac.tennis;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * This class generates and handles the GUI using JavaSwing
 * The run method is the main method to initiate the GUI
 * generateMainPanel generates the initial display of the GUI, consisting of the two text fields for player names, as well as the start button
 * Upon pressing the start button, the game panel consisting of the two scoring buttons, as well as the display panel, consisting of the game text are generated.
 * The main panel is then swapped with this new game panel.
 * Each time a scoring button is pressed, the updateDisplayPannel is called, and updates the display using data from the match class
 */

public class TennisGUI {

	private Match match;
	private JPanel displayPanel;
	private JTextArea displayText;
	private JFrame jframe;
	private JPanel gamePanel;
	private JPanel mainPanel;

	public void run() {
		jframe = new JFrame("Tennis simulation");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(400,400);

		generateMainPanel();

		jframe.getContentPane().add(mainPanel);
		jframe.setVisible(true);

	}

	private void generateMainPanel() {
		mainPanel = new JPanel();
		JLabel player1NameLabel = new JLabel("Player 1 Name : ");
		JTextField player1TextField = new JTextField(15);

		JLabel player2NameLabel = new JLabel("Player 2 Name : ");
		JTextField player2TextField = new JTextField(15);

		mainPanel.add(player1NameLabel);
		mainPanel.add(player1TextField);

		mainPanel.add(player2NameLabel);
		mainPanel.add(player2TextField);



		JButton startButton = new JButton("Start Match");
		mainPanel.add(startButton);

		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(player1TextField.getText().equals("") || player2TextField.getText().equals("")) {
					showMessageDialog(null, "Please enter player names");
					return;
				}
				
				match = new Match(player1TextField.getText(), player2TextField.getText());
				generateDisplayPanel();
				generateGamePanel();

				jframe.getContentPane().add(gamePanel);
				jframe.getContentPane().remove(mainPanel);


				jframe.validate();
			}

		});
	}

	private void generateGamePanel() {
		gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout());
		JPanel playerButtonPanel = new JPanel();
		playerButtonPanel.setLayout(new FlowLayout());

		JButton player1Button = new JButton("Player 1 Scores");

		player1Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				match.scores(1);
				updateDisplayPanel();
			}

		});

		JButton player2Button = new JButton("Player 2 Scores");
		player2Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				match.scores(2);
				updateDisplayPanel();
			}

		});


		playerButtonPanel.add(player1Button);
		playerButtonPanel.add(player2Button);
		gamePanel.add(playerButtonPanel, BorderLayout.NORTH);
		gamePanel.add(displayPanel, BorderLayout.CENTER);
	}

	private void generateDisplayPanel() {
		displayPanel = new JPanel();
		displayText = new JTextArea(4, 20);
		displayText.setText(match.getMatchString());
		displayPanel.add(displayText);

	}

	private void updateDisplayPanel() {
		displayText.setText(match.getMatchString());
		jframe.validate();

	}

}
