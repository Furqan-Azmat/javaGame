package tiles;

import static main.Game.TILES_IN_HEIGHT;
import static main.Game.TILES_IN_WIDTH;
import static main.Game.TILE_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import entities.EnemyType1;
import entities.EnemyType2;
import main.Game;
import utils.LoadSave;


public class LevelManager {
	
	private Game game;
	private Level level;
	private BufferedImage[] levelSprite;
	private int lvlNum = 1;
	
	public LevelManager(Game game) {
		this.game = game;
		importTileSprite();
		level = new Level(LoadSave.getLevelData(lvlNum)); //number of tiles we have
	}

	// method to import the text file with our level generation info
//	private void importMap() { //delete later
//        try {
//            BufferedReader in = new BufferedReader(new FileReader("res/map_1.txt"));
//            String line;
//            int num;
//            for (int r = 0; r < TILES_IN_HEIGHT; r++) {
//                line = in.readLine();
//                String numbers[] = line.split(" ");
//                for (int c = 0; c < TILES_IN_WIDTH; c++) {
//                    num = Integer.parseInt(numbers[c]);
//                    mapTileNum[c][r] = num; //map numbers stored in array
//                }
//            }
//            
//            in.close();
//        } catch (IOException e) {
//            System.out.println("file not read"); }
//    }

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
			switch(lvlNum) {
				case 1:
					new EnemyType1(300, 350, 60, 60).drawCharacter(g);
					//new EnemyType2(400, 300, 50, 60).drawCharacter(g);
					//new EnemyType2(450, 200, 50, 60).drawCharacter(g);
					break;
				case 2:
					new EnemyType1(100, 100, 60, 60).drawCharacter(g);
					break;
				case 3:
					break;
			}
		}
		   

	public void update() {
		
	}

	public Level getCurrentLevel() {
		return level;
	}

}