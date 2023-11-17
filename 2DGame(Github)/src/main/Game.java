package main;

import java.awt.Graphics;

import Sound.Sound;
import entities.Player;
import tiles.LevelManager;

public class Game implements Runnable{
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS = 120;
	private final int UPS = 200;
	
	//screen size
	public final static int DEFAULT_TILE_SIZE = 16;
	public final static float SCALE = 2.0f;
	public final static int TILES_IN_WIDTH = 40; // visible game screen is 20 tiles wide
	public final static int TILES_IN_HEIGHT = 20; // visible game screen is 10 tiles long
	public final static int TILE_SIZE = (int) (DEFAULT_TILE_SIZE * SCALE);
	public final static int GAME_WIDTH = TILE_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILE_SIZE * TILES_IN_HEIGHT;
	
	//world size 
	public final static int WORLD_IN_WIDTH = 40; // the number of tiles the entire map is in width
	public final static int WORLD_IN_HEIGHT = 20; // game screen height and the entire map height is the same
	public final static int WORLD_WIDTH = TILE_SIZE * WORLD_IN_WIDTH; 
	public final static int WORLD_HEIGHT = TILE_SIZE * WORLD_IN_HEIGHT;
	
	
	// variables to handle longer levels 
	private int xLevelOffset;
	private int leftBorder = (int) (0.2 * GAME_WIDTH); // 20% of the game screen to the left
	private int rightBorder = (int) (0.8 * GAME_WIDTH); // 20% of the game screen to the right 
	//private int levelTileWidth = WORLD_IN_WIDTH; // number of tiles the map is in width 
	private int maxTileOffset = WORLD_IN_WIDTH - TILES_IN_WIDTH; //entire map - visible game screen 
	private int maxLevelOffsetX = maxTileOffset * TILE_SIZE;
		
	private int score = 0;

	private LevelManager levelManager;
	private Player player;
	private Sound sound;
	
	public Game(GameWindow window) { //game constructor
		initializeClasses();
		gameWindow = window;
		gamePanel = new GamePanel(this); //gamepanel object
		sound = new Sound();
		gamePanel.requestFocus(); //the inputs are focused to gamePanel
		startGameLoop();
	}
	
	// Get the score
	public int getScore() {
        return score;
    }

	// Update the score
    public void increaseScore(int points) {
        score += points;
        if (score == 40)
        	System.out.println("ALL COINS COLLECTED PLS CHANGE MAP");// ----- make it change the map pls
    }
	
	private void initializeClasses() {
		levelManager = new LevelManager(this);
		player = new Player((2-1) * TILE_SIZE,(19-1)*TILE_SIZE, (int) (16 * SCALE), (int) (16 * SCALE)); 
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());		
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	private void update() {
		levelManager.update();
		player.update();
		checkIfPlayerCloseToBorder();
	} 
	
	private void checkIfPlayerCloseToBorder() {
		
		int playerX = (int) player.getHitbox().x;
		int difference = playerX - xLevelOffset;
		
		if (difference > rightBorder) {
			xLevelOffset += difference - rightBorder;
		}
		else if(difference < leftBorder) {
			xLevelOffset += difference - leftBorder;
		}
		
		if(xLevelOffset > maxLevelOffsetX) {
			xLevelOffset = maxLevelOffsetX;
		}
		else if(xLevelOffset < 0) {
			xLevelOffset = 0;
		}
	}

	public void render(Graphics g) {	
		levelManager.draw(g, xLevelOffset);
		levelManager.addEnemies(g);
		levelManager.addCoins(g);
		player.render(g, xLevelOffset);
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
	
	public GameWindow getWindow() {
		return gameWindow;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void playMusic(int soundIndex) {
		sound.loadSound(soundIndex);
		sound.play();
		sound.loop();
	}
	
	public void playSoundEffect(int soundIndex) {
		sound.loadSound(soundIndex);
		sound.play();
	}
	
	public void stopMusic() {
		sound.stop();
	}
	

	

}
