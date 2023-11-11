package gameStates;

import entities.Player;
import main.Game;
import tiles.LevelManager;

import static main.Game.SCALE;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements StateMethods{

	private Player player;
	private LevelManager levelManager;
	
	public Playing(Game game) {
		super(game);
		initializeClasses();
		
	}

	private void initializeClasses() {
		levelManager = new LevelManager(game);
		player = new Player(100,100, (int) (16 * SCALE), (int) (16 * SCALE)); //(int) (16 * SCALE), (int) (16 * SCALE))
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());		
	}

	@Override
	public void update() {
		levelManager.update();
		player.update();
		
	}

	@Override
	public void draw(Graphics g) {
		levelManager.draw(g);
		player.render(g);
		
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
			
//		case KeyEvent.VK_BACK_SPACE: 
//			GameState.state = GameState.MENU;
//			break;
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
	
	public Player getPlayer() {
		return player;
	}

}
