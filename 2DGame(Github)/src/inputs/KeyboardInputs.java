package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entities.Player;

/**
 * 
 * Handle all keyboard related input for the game
 * 
 * @author Furqan, Licia, Farhana
 *
 */
public class KeyboardInputs implements KeyListener{
	private Player player;
	
	public KeyboardInputs(Player player) {
		this.player = player;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A: 
			player.setLeft(true); 
			break;
		
		case KeyEvent.VK_D: 
			player.setRight(true); 
			break;
			
		case KeyEvent.VK_SPACE: 
			player.setJump(true); 
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A: 
			player.setLeft(false); 
			break;

		case KeyEvent.VK_D: 
			player.setRight(false); 
			break;
			
		case KeyEvent.VK_SPACE: 
			player.setJump(false); 
			break;
		}
	}
}