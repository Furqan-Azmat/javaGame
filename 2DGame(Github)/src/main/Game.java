package main;

import java.awt.Graphics;

import entities.Player;
import tiles.LevelManager;

public class Game implements Runnable{
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS = 120;
	private final int UPS = 200;
	
	public final static int DEFAULT_TILE_SIZE = 16;
	public final static float SCALE = 2.0f;
	public final static int TILES_IN_WIDTH = 20;
	public final static int TILES_IN_HEIGHT = 10;
	public final static int TILE_SIZE = (int) (DEFAULT_TILE_SIZE * SCALE);
	public final static int GAME_WIDTH = TILE_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILE_SIZE * TILES_IN_HEIGHT;

	private LevelManager levelManager;
	private Player player;
	
	
	public Game() { //game constructor
		
		initializeClasses();
		
		gamePanel = new GamePanel(this); //gamepanel object
		gameWindow = new GameWindow(gamePanel); //gamePanel is passed to gameWindow
		gamePanel.requestFocus(); //the inputs are focused to gamePanel
		levelManager = new LevelManager(this);
		
		startGameLoop();
		
	}

	private void initializeClasses() {
		levelManager = new LevelManager(this);
		player = new Player(100,100, (int) (TILES_IN_WIDTH * SCALE), (int) (TILES_IN_HEIGHT * SCALE));
		
		
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	private void update() {
		//levelManager.update();
		player.update();
		
		
	} 
	
	public void render(Graphics g) {
		
		levelManager.draw(g);
		player.render(g);
		
		

	}
	

	@Override
	//game loop
	public void run() {
		
		double timePerFrame = 1000000000.0 / FPS; //how long each frame is going to last in nanoseconds
		double timePerUpdate = 1000000000.0 / UPS; //frequency of updates per second
	
		long previousTime = System.nanoTime(); //current time when the program starts in nanoseconds
		
		int frames = 0;
		int updates = 0;
	
		long lastCheck = System.currentTimeMillis();
		
		double deltaTime = 0;
		double deltaFrame = 0;
		
		while(true) { //infinite loop
			
			long currentTime = System.nanoTime();
			
			deltaTime += (currentTime - previousTime) / timePerUpdate;
			deltaFrame += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime; 
			
			if (deltaTime >= 1) {
				// if the time now - the last time we drew is >= timeperframe, then draw again
				update();
				updates++;
				deltaTime --;
			}
			
			if (deltaFrame >= 1) {
				gamePanel.repaint();
				frames++;
				deltaFrame--;
			}
			
			//counts how many frames per second
			if (System.currentTimeMillis() - lastCheck >= 1000) { 
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	
	

	

}
