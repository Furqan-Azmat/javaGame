package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;

import static main.Game.GAME_WIDTH;
import static main.Game.GAME_HEIGHT;

/**
 * 
 * Class that creates the overall gamescreen as well as the score display 
 * 
 * @author Furqan, Licia, Farhana
 *
 */

public class GamePanel extends JPanel { 
	
	private Game game;
	
	public GamePanel(Game game) { // game panel constructor
		this.game = game;
		setPaneSize(); //calls the method to set window size
		addKeyListener(new KeyboardInputs(game.getPlayer()));
		game.getWindow().add(this);
	}
	
	//method to set size of game window
	private void setPaneSize() {
		Dimension dimension = new Dimension(GAME_WIDTH,GAME_HEIGHT); //size of the window
		setPreferredSize(dimension);
		System.out.println("size: " + GAME_WIDTH + " x " + GAME_HEIGHT);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
		drawScore(g);
	}
	
	// Display the score in the top left of the screen 
	private void drawScore(Graphics g) {
        int score = game.getScore();
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 30); // Adjust the position as needed
    }
	
	public Game getGame() {
		return game;
	}
	
}



