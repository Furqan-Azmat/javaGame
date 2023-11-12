package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
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
	boolean isDraw = false;
	
	public GamePanel(Game game) { // game panel constructor
		
		
		mouseInputs = new MouseInputs(this);
		this.game = game;
		
		JFrame frm = new JFrame("Main Menu");
		JPanel content = new JPanel();
		frm.add(content);
		
		JButton draw = new JButton("Draw");
		JButton stop = new JButton("Stop");
		content.add(draw);
		content.add(stop);
		frm.setSize(300, 100);
		frm.setLocationRelativeTo(null) ;
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setVisible(true) ;
		
		draw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isDraw = true;
            }
        });
		
		stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 isDraw = false;
                 repaint();

                try {
                    Thread.sleep(100); // Add a short delay to allow Swing to update the UI
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
		
		
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

	    if (isDraw) {
	        game.render(g);
	    }
	
	}
	
	
	
	public Game getGame() {
		return game;
	}
	
}



