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
	public GameWindow() { 
		setTitle("This is a game");
		setSize(1280, 640); // CHANGE THE SIZE OF THE WINDOW TO SUIT THE GAME 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); //runs the window center of the screen
		setResizable(false);
		window = this;
		handler = new ButtonHandler();
		
		menu();
		setVisible(true); // makes the window visible (ADD EVERYTHING THAT WILL SHOW UP BEFORE THIS LINE)
	}
	
	public void menu() {
		start = new JButton(new ImageIcon("res/start.png"));
		start.setRolloverIcon(new ImageIcon("res/hoverstart.png"));
		quit = new JButton(new ImageIcon("res/quit.png"));
		quit.setRolloverIcon(new ImageIcon("res/hoverquit.png"));
		JLabel gameTitle = new JLabel("THIS IS A GAME");
		gameTitle.setFont(new Font("Serif", Font.BOLD, 90));
		menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 300, 20));
		menuPanel.add(gameTitle);
		start.setPreferredSize(new Dimension(220, 90));
		start.addActionListener(handler);
		quit.addActionListener(handler);
		menuPanel.add(start);
		quit.setPreferredSize(new Dimension(220, 90));
		menuPanel.add(quit);
		add(menuPanel); // add menu panel to the game window
	}
	
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == start) { 
				System.out.println("starting game");
				menuPanel.setVisible(false);
				new Game(window);
			}
			else if (e.getSource() == quit) { // close the program
				dispose();
			}
		}
	}
}
