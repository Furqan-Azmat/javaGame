package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

/**
 * 
 * Class that creates the initial menu screen 
 * 
 * @author Furqan, Licia, Farhana
 *
 */

public class GameWindow extends JFrame{
	
	private JPanel menuPanel;
	private JButton start, quit;
	private ButtonHandler handler;
	private Game game;
	private JLayeredPane layeredPane;
	private ImageIcon backgroundIcon;
	private JLabel backgroundLabel;
	
	/**
	 * Set up main menu
	 */
	public GameWindow() { 
		game = new Game(this);
		
        URL resourceUrl = this.getClass().getResource("background.png");
        if (resourceUrl == null) {
            System.err.println("Error loading background image. Resource not found.");
        } else {
            backgroundIcon = new ImageIcon(resourceUrl);
        }
		
		backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setSize(1294,675);
		
		ImageIcon image = new ImageIcon("res/logo.png");
		
		setTitle("2D JAVA GAME");
		setSize(1294,675);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); //runs the window center of the screen
		setResizable(false);
		setIconImage(image.getImage());
		
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1294, 675));

        handler = new ButtonHandler();
		menu();
		
		layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
	    layeredPane.add(menuPanel, JLayeredPane.PALETTE_LAYER);
        add(layeredPane);
		
		setVisible(true); // makes the window visible (ADD EVERYTHING THAT WILL SHOW UP BEFORE THIS LINE)
	}
	
	
	/**
	 * Create buttons(Start and Quit) for main menu
	 */
	public void menu() {
		
		start = new JButton(new ImageIcon("res/start.png"));
		start.setRolloverIcon(new ImageIcon("res/hoverstart.png"));
		quit = new JButton(new ImageIcon("res/quit.png"));
		quit.setRolloverIcon(new ImageIcon("res/hoverquit.png"));
		menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 500, 50));
		menuPanel.setBounds(-50, 50, 500, 500);
		menuPanel.setOpaque(false);
		
	    start.setPreferredSize(new Dimension(220, 90));
	    quit.setPreferredSize(new Dimension(220, 90));
		
		start.addActionListener(handler);
		quit.addActionListener(handler);
		
		menuPanel.add(start);
		menuPanel.add(quit);
		
		this.add(menuPanel); // add menu panel to the game window
		game.playMusic(1);
	}
	
	public void hideMenuPanel() {
		menuPanel.setVisible(false);
		backgroundLabel.setVisible(false);
	}
	
	/**
	 * 
	 * Perform appropriate action after button is clicked 
	 *
	 */
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == start) { 
				System.out.println("starting game");
				menuPanel.setVisible(false);
				game.stopMusic();
				
				CharacterSelection charSelection = new CharacterSelection(GameWindow.this);
				charSelection.setVisible(true);
				game.playMusic(2);
			}
			else if (e.getSource() == quit) { // close the program
				System.exit(0);
			}
		}
	}
}