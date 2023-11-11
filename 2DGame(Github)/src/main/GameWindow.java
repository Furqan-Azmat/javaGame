package main;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
	
	public GameWindow(GamePanel gamepanel) { 
		
		this.add(gamepanel);
		//this.setBackground(Color.BLACK); //not working?
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); //runs the window center of the screen
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	
	}

}
