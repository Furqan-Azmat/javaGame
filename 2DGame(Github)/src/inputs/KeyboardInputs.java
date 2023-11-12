//handles all the keyboard inputs for our game 

package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entities.Player;

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
	
//	@Override
//	public void keyPressed(KeyEvent e) { //when w,a,s,d pressed, move up, left, down, or right
//		
//		switch(e.getKeyCode()) {
//		
//		case KeyEvent.VK_W: 
//			gamePanel.getGame().getPlayer().setUp(true); 
//			break;
//		case KeyEvent.VK_A: 
//			gamePanel.getGame().getPlayer().setLeft(true); 
//			break;
//		case KeyEvent.VK_S: 
//			gamePanel.getGame().getPlayer().setDown(true); 
//			break;
//		case KeyEvent.VK_D: 
//			gamePanel.getGame().getPlayer().setRight(true); 
//			break;
//		}
//	}
//
//	@Override
//	public void keyReleased(KeyEvent e) {
//		
//		switch(e.getKeyCode()) {
//		
//		case KeyEvent.VK_W: 
//			gamePanel.getGame().getPlayer().setUp(false); 
//			break;
//		case KeyEvent.VK_A: 
//			gamePanel.getGame().getPlayer().setLeft(false); 
//			break;
//		case KeyEvent.VK_S: 
//			gamePanel.getGame().getPlayer().setDown(false); 
//			break;
//		case KeyEvent.VK_D: 
//			gamePanel.getGame().getPlayer().setRight(false); 
//			break;
//		}
//	}
}