package tiles;

import static main.Game.TILE_SIZE;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import entities.*;
import main.Game;
import utils.LoadSave;

public class LevelManager {
	
	private Game game;
	private Level level;
	private BufferedImage[] levelSprite;
	private int lvlNum = 1;
    private Coins[] coins;
    private Enemy[] enemies;
	
	public LevelManager(Game game) {
		this.game = game;
		importTileSprite();
		level = new Level(LoadSave.getLevelData(lvlNum)); //number of tiles we have
        initializeCoins(); // Populate the coins array based on the level
        initializeEnemies();
	}
	
    public void changeLevel(int newLevel) {
        lvlNum = newLevel;
        level = new Level(LoadSave.getLevelData(lvlNum));
        initializeCoins();
        initializeEnemies();
    }
    
    public int getLevelNumber() {
        return lvlNum;
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
	 
	 // Loop through the coin array made below and draw them along with checking for player collision 
	 public void addCoins(Graphics g) {
			for (Coins coin : coins) {
	            coin.drawCoin(g);
	            if (coin.checkCollisionWithPlayer(game.getPlayer())) {
	            	game.playSoundEffect(0);
	            	game.increaseScore(10); // If the collision returns true add 10 to the score
	            }
	        }
		}
	 public void addEnemies(Graphics g) {
			for (Enemy e : enemies) {
	            e.drawCharacter(g);
	            if (e.getHitbox().intersects(game.getPlayer().getHitbox())) {
	            	game.getPlayer().respawn();
	            }
	        }
		}
	 
	 private void initializeEnemies() {
	        switch (lvlNum) {
	            case 1:
	                enemies = new Enemy[]{
	                		new EnemyTypeGhost((6-1) *TILE_SIZE, (14-1)*TILE_SIZE, 32, 32),
	    					new EnemyTypeGhost((13-1) *TILE_SIZE, (17-1)*TILE_SIZE, 32, 32),
	    					new EnemyTypeSnail((4-1)*TILE_SIZE, (19-1)*TILE_SIZE, 32, 32),
	    					new EnemyTypeSnail((9-1)*TILE_SIZE, (19-1)*TILE_SIZE, 32, 32),
	    					new EnemyTypeBee((20-1)*TILE_SIZE, (12-1)*TILE_SIZE, 32, 32),
	                };
	                break;
	            case 2:
	            	enemies = new Enemy[]{
	            			new EnemyTypeSnail((9-1)*TILE_SIZE, (19-1)*TILE_SIZE, 32, 32),
	            			new EnemyTypeBee((20-1)*TILE_SIZE, (12-1)*TILE_SIZE, 32, 32),
	                };
	                break;
	        }
	 }

	 // Initialize the array that will hold the coin objects 
	 private void initializeCoins() {
		 switch (lvlNum) {
	     	case 1:
	     		coins = new Coins[]{
	     				new Coins(5 *TILE_SIZE, 16 * TILE_SIZE, TILE_SIZE, TILE_SIZE),
	                	new Coins((10 - 1) * TILE_SIZE , (16 - 2) * TILE_SIZE, TILE_SIZE, TILE_SIZE),
	                	new Coins((14-1) * TILE_SIZE, (15-2) * TILE_SIZE, TILE_SIZE, TILE_SIZE),
	                	new Coins((18-1)*TILE_SIZE, (16-2)*TILE_SIZE, TILE_SIZE, TILE_SIZE),
	                    // Add more coins as needed for level 1
	            };
	            break;
	     	case 2:
	        	coins = new Coins[]{
	        			new Coins(5 *TILE_SIZE, 16 * TILE_SIZE, TILE_SIZE, TILE_SIZE),
	        			new Coins((10 - 1) * TILE_SIZE , (16 - 2) * TILE_SIZE, TILE_SIZE, TILE_SIZE),
	        			// Add more coins as needed for level 2
		    	};
		    	break;
	 	}
	 }

	public void update() {
		
	}

	public Level getCurrentLevel() {
		return level;
	}

}