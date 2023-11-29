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
        game.getPlayer().respawn();
        game.getPlayer().loadLvlData(level.getLevelData());
    }
    
	private void importTileSprite() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVELATLAS);
		levelSprite = new BufferedImage[2];	//number of tiles our tile sprite has
		for(int j = 0; j < 1; j++) //height of tile sprite
			for(int i = 0; i < 2; i++) { //width of tile sprite
				int index = j*2 + i;
				levelSprite[index] = img.getSubimage(i * 16, j * 16, 16, 16); // 16 - size of tile
			}
	}

	public void draw(Graphics g) {
		int x = 0;
		int y = 0;
		int tileNum;
		for (int r = 0; r < Game.TILES_IN_HEIGHT; r++) {
			for (int c = 0; c < Game.TILES_IN_WIDTH; c++) {
		    	tileNum = level.getSpriteIndex(r, c);
		    	g.drawImage(levelSprite[tileNum], x, y, TILE_SIZE ,TILE_SIZE, null);
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
	 // place enemies and respawn player when player touches the enemies
	 public void addEnemies(Graphics g) {
		 for (Enemy e : enemies) {
			 e.drawCharacter(g);
			 if (e.getHitbox().intersects(game.getPlayer().getHitbox())) {
				 game.playSoundEffect(3);
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
	        			  new EnemyTypeSnail(32 * TILE_SIZE, 18 * TILE_SIZE, 32, 32),
	        			  new EnemyTypeBee(19 * TILE_SIZE, 4 * TILE_SIZE, 32, 32),
	        			  new EnemyTypeBee(27 * TILE_SIZE, 12 * TILE_SIZE, 32, 32),
	            };
	            break;
	        case 2:
	        	enemies = new Enemy[]{
	        			  new EnemyTypeSnail(14 * TILE_SIZE, 18 * TILE_SIZE, 32, 32),
	        			  new EnemyTypeSnail(22 * TILE_SIZE, 18 * TILE_SIZE, 32, 32),
	            		  new EnemyTypeBee(18 * TILE_SIZE, 12 * TILE_SIZE, 32, 32),
	            		  new EnemyTypeGhost(15 * TILE_SIZE, 3 * TILE_SIZE, 32, 32),
	            		  new EnemyTypeGhost(21 * TILE_SIZE, 3 * TILE_SIZE, 32, 32),
	        	};
	            break;
	        case 3:
	        	enemies = new Enemy[]{
	        			  new EnemyTypeGhost(35 * TILE_SIZE, 11 * TILE_SIZE, 32, 32),
	        			  new EnemyTypeGhost(18 * TILE_SIZE, 5 * TILE_SIZE, 32, 32),
	        			  new EnemyTypeSnail(5 * TILE_SIZE, 18 * TILE_SIZE, 32, 32),
	        			  new EnemyTypeBee(1 * TILE_SIZE, 12 *TILE_SIZE, 32, 32),
	        			  new EnemyTypeBee(31 * TILE_SIZE, 5 *TILE_SIZE, 32, 32),
	            };
	            break;
		 }
	 }

	 // Initialize the array that will hold the coin objects for each map
	 private void initializeCoins() {
		 switch (lvlNum) {
	     	case 1:
	     		coins = new Coins[]{
	     				new Coins(15 * TILE_SIZE, 3 * TILE_SIZE, TILE_SIZE, TILE_SIZE),
	     				new Coins(5 * TILE_SIZE, 2 * TILE_SIZE, TILE_SIZE, TILE_SIZE),
	                	new Coins(38 * TILE_SIZE , 18 * TILE_SIZE, TILE_SIZE, TILE_SIZE),
	                	new Coins(38 * TILE_SIZE, 1   * TILE_SIZE, TILE_SIZE, TILE_SIZE),
	                	new Coins(25 * TILE_SIZE, 15 *TILE_SIZE, TILE_SIZE, TILE_SIZE),
	            };
	            break;
	     	case 2:
	        	coins = new Coins[]{
	        			new Coins(23 * TILE_SIZE , 10 * TILE_SIZE, TILE_SIZE, TILE_SIZE),
	        			new Coins(19* TILE_SIZE , 14 * TILE_SIZE, TILE_SIZE, TILE_SIZE),
	        			new Coins(8 * TILE_SIZE , 14 * TILE_SIZE, TILE_SIZE, TILE_SIZE),
	        			new Coins(20* TILE_SIZE , 2* TILE_SIZE, TILE_SIZE, TILE_SIZE),
	        			new Coins(33* TILE_SIZE , 16 * TILE_SIZE, TILE_SIZE, TILE_SIZE),
		    	};
		    	break;
	     	case 3:
	     		coins = new Coins[]{
	        			new Coins(3 * TILE_SIZE , 14 * TILE_SIZE, TILE_SIZE, TILE_SIZE),
	        			new Coins(4 * TILE_SIZE , 2 * TILE_SIZE, TILE_SIZE, TILE_SIZE),
	        			new Coins(17 * TILE_SIZE , 2 * TILE_SIZE, TILE_SIZE, TILE_SIZE),
	        			new Coins(37* TILE_SIZE , 14 * TILE_SIZE, TILE_SIZE, TILE_SIZE),
	        			new Coins(38 * TILE_SIZE , 5 * TILE_SIZE, TILE_SIZE, TILE_SIZE),
		    	};
	     		break;
	 	}
	 }

	public Level getCurrentLevel() {
		return level;
	}

}