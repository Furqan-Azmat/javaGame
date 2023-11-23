package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class GameWindow extends JFrame{
	
	private JPanel menuPanel;
	private JButton start, quit;
	private ButtonHandler handler;
	private GameWindow window;
	private Game game;
	private JLayeredPane layeredPane;
	private ImageIcon backgroundIcon;
	private JLabel backgroundLabel;
	
	public GameWindow() { 
		
		game = new Game(this);
		
        URL resourceUrl = this.getClass().getResource("background1294x680.png");
        if (resourceUrl == null) {
            System.err.println("Error loading background image. Resource not found.");
        } else {
            backgroundIcon = new ImageIcon(resourceUrl);
        }
		
	        
		backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setSize(1294,675);
		
		setTitle("This is a game");
		setSize(1294,675); // CHANGE THE SIZE OF THE WINDOW TO SUIT THE GAME 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); //runs the window center of the screen
		setResizable(false);
		
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1294, 675));

        handler = new ButtonHandler();
		menu();
		
		layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
	    layeredPane.add(menuPanel, JLayeredPane.PALETTE_LAYER);

		
        add(layeredPane);
		
		window = this;
		
		
		setVisible(true); // makes the window visible (ADD EVERYTHING THAT WILL SHOW UP BEFORE THIS LINE)
	
	
	}
	

	
	public void menu() {
		// create buttons with images for starting and quitting the game
		
		start = new JButton(new ImageIcon("res/start.png"));
		start.setRolloverIcon(new ImageIcon("res/hoverstart.png"));
		quit = new JButton(new ImageIcon("res/quit.png"));
		quit.setRolloverIcon(new ImageIcon("res/hoverquit.png"));

//		start = new JButton("Start");
//		quit = new JButton("quit");
		
		// create a label for game title 
		//JLabel gameTitle = new JLabel("THIS IS A GAME");
		//gameTitle.setFont(new Font("Serif", Font.BOLD, 40));
		
		
		menuPanel = new JPanel(null);
		menuPanel.setBounds(0, 0, 220, 180);
		
		//menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		//menuPanel.add(gameTitle);
		
//		start.setPreferredSize(new Dimension(220, 90));
//		quit.setPreferredSize(new Dimension(220, 90));
		
		start.setBounds(0, 0, 220, 90);
        quit.setBounds(0, 90, 220, 90);
		
		start.addActionListener(handler);
		quit.addActionListener(handler);
		
		menuPanel.add(start);
		menuPanel.add(quit);
		
		
		
		this.add(menuPanel); // add menu panel to the game window
		
		game.playMusic(1);
	}
	
	
	
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == start) { 
				System.out.println("starting game");
				menuPanel.setVisible(false);
				game.stopMusic();
				new Game(window);
				game.playMusic(2);
			}
			else if (e.getSource() == quit) { // close the program
				dispose();
				
				
			}
		}
	}
}