package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameWindow extends JFrame{
	private JPanel menuPanel;
	private JButton start, quit;
	private ButtonHandler handler;
	private GameWindow window;
	private Game game;
	public GameWindow() { 
		
		game = new Game(this);
		
		ImageIcon backgroudImage = new ImageIcon("res/back1.png");
		setContentPane(new BackgroundPanel(backgroudImage.getImage()));
	
		setTitle("This is a game");
		setSize(1294,675); // CHANGE THE SIZE OF THE WINDOW TO SUIT THE GAME 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); //runs the window center of the screen
		setResizable(false);
		
		window = this;
		handler = new ButtonHandler();
		menu();
		
		setVisible(true); // makes the window visible (ADD EVERYTHING THAT WILL SHOW UP BEFORE THIS LINE)
	
	
	}
	
	private class BackgroundPanel extends JPanel{
		
		private Image backgroudImage;
		
		public BackgroundPanel(Image backgroudImage) {
		this.backgroudImage = backgroudImage;
		
		}
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroudImage, 0, 0, 1294, 675, this);
		}
	}
	
	public void menu() {
		// create buttons with images for starting and quitting the game
		start = new JButton(new ImageIcon("res/start.png"));
		start.setRolloverIcon(new ImageIcon("res/hoverstart.png"));
		quit = new JButton(new ImageIcon("res/quit.png"));
		quit.setRolloverIcon(new ImageIcon("res/hoverquit.png"));
		
		// create a label for game title 
		//JLabel gameTitle = new JLabel("THIS IS A GAME");
		//gameTitle.setFont(new Font("Serif", Font.BOLD, 40));
		
		
		menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		//menuPanel.add(gameTitle);
		start.setPreferredSize(new Dimension(220, 90));
		start.addActionListener(handler);
		quit.addActionListener(handler);
		menuPanel.add(start);
		quit.setPreferredSize(new Dimension(220, 90));
		menuPanel.add(quit);
		
		add(menuPanel); // add menu panel to the game window
		
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
