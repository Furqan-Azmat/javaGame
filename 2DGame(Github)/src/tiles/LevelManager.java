package tiles;
import static main.Game.TILE_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entities.DrawCoin;
import entities.EnemyType1;
import entities.EnemyType2;
import entities.EnemyType3;
import main.Game;
import utils.LoadSave;


public class LevelManager {
	
	private Game game;
	private Level level;
	private BufferedImage[] levelSprite;
	private int lvlNum = 1;
    private DrawCoin[] coins;
	
	public LevelManager(Game game) {
		this.game = game;
		importTileSprite();
		level = new Level(LoadSave.getLevelData(lvlNum)); //number of tiles we have
        initializeCoins(); // Populate the coins array based on the level
	}

	private void importTileSprite() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVELATLAS);
		levelSprite = new BufferedImage[2];	//number of tiles our tile sprite has
		for(int j = 0; j < 1; j++) //height of tile sprite
			for(int i = 0; i < 2; i++) { //width of tile sprite
				int index = j*2 + i;
				levelSprite[index] = img.getSubimage(i * 16, j * 16, 16, 16); // 32 - size of tile
			}
	}

	 public void draw(Graphics g, int lvlOffset) {
		    int x = 0;
		    int y = 0;
		    int tileNum;
		        for (int r = 0; r < Game.TILES_IN_HEIGHT; r++) {
		            for (int c = 0; c < Game.WORLD_IN_WIDTH; c++) {
		                tileNum = level.getSpriteIndex(r, c);
		                g.drawImage(levelSprite[tileNum], x, y, TILE_SIZE - lvlOffset,TILE_SIZE, null);
		                x += TILE_SIZE;
		            }
		            y += TILE_SIZE;
		            x = 0;
		        }
		    }

	 public void addEnemies(Graphics g) {
			switch(lvlNum) { // create enemies for each map
				case 1:
					new EnemyType1(200, 576, 32, 32).drawCharacter(g);
					new EnemyType1(400, 576, 32, 32).drawCharacter(g);
					new EnemyType2(300, 300, 32, 32).drawCharacter(g);
					new EnemyType2(560, 350, 32, 32).drawCharacter(g);
					new EnemyType3(240, 476, 32, 32).drawCharacter(g);
					break;
				case 2:
					new EnemyType1(100, 100, 60, 60).drawCharacter(g);
					break;
				case 3:
					break;
			}
		}
	 
	 // Loop through the coin array made below and draw them along with checking for player collision 
	 public void addCoins(Graphics g) {
			for (DrawCoin coin : coins) {
	            coin.drawCharacter(g);
	            if (coin.checkCollisionWithPlayer(game.getPlayer())) {
	                game.increaseScore(10); // If the collision returns true add 10 to the score
	            }
	        }
		}
	 
	 // Initialize the array that will hold the coin objects 
	 private void initializeCoins() {
	        switch (lvlNum) {
	            case 1:
	                coins = new DrawCoin[]{
	                		new DrawCoin(525, 576, 32, 32),
	                		new DrawCoin(525, 544, 32, 32),
	                		new DrawCoin(564, 576, 32, 32),
	                		new DrawCoin(564, 544, 32, 32),
	                        // Add more coins as needed for level 1
	                };
	                break;
	            // Initialize coins for other levels similarly
	        }
	    }
		   
	public void update() {
		
	}

	public Level getCurrentLevel() {
		return level;
	}

}