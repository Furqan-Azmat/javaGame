package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs; 

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

import static main.Game.GAME_WIDTH;
import static main.Game.GAME_HEIGHT;


public class GamePanel extends JPanel { 
	
	//global vars 
	private MouseInputs mouseInputs;
	private Game game;
	
	
	public GamePanel(Game game) { // game panel constructor
		
		mouseInputs = new MouseInputs(this);
		this.game = game;
	
		setPaneSize(); //calls the method to set window size
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs); // press, release, click, enter, and exit
		addMouseMotionListener(mouseInputs); // movement, dragging
		
	}
	
	//method to set size of game window
	private void setPaneSize() {
		Dimension dimension = new Dimension(GAME_WIDTH,GAME_HEIGHT); //size of the window
		setPreferredSize(dimension);
		System.out.println("size: " + GAME_WIDTH + " x " + GAME_HEIGHT);
	}
	
	public void updateGame() {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
	}
	
	public Game getGame() {
		return game;
	}
	
}



