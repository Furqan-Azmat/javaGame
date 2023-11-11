package gameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Game;
import ui.MenuButton;

public class Menu extends State implements StateMethods{

	MenuButton menubutton;
	public Menu(Game game) {
		super(game);
		//menubutton = new MenuButton(); //breaks the game 
	}

	
	@Override
	public void update() {
		// we have to use the update and draw method cause in the game class, the menu is called using these two methods
		
		
	}

	@Override
	public void draw(Graphics g) {
	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
