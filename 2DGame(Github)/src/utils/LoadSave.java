package utils;

import static main.Game.TILES_IN_HEIGHT;
import static main.Game.TILES_IN_WIDTH;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

/**
 * 
 * Class responsible for loading in the sprites and level information 
 * 
 * @author furqan, licia, farhana
 *
 */


public class LoadSave {
	
	private static String selectedCharacter = "";
	
	public static final String PLAYERATLAS = "player.png"; //finn atlas
	public static final String PLAYERATLAS2 = "player2.png"; //fionna atlas
	public static final String LEVELATLAS = "tile_final.png";
	public static final String ENEMYATLAS = "enemySprite.png";
	public static final String COINATLAS = "Coin1.png";

	/**
	 * 
	 * Load in the appropriate sprite 
	 * 
	 * @param SpriteFileName
	 * @return
	 */
	public static BufferedImage GetSpriteAtlas(String SpriteFileName) {
		BufferedImage img = null;
		InputStream in = LoadSave.class.getResourceAsStream("/" + SpriteFileName);
		try { 
			img = ImageIO.read(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Error reading sprite file");
		}
		return img;
	}
	
	
	/**
	 * Load the different levels and store the layout in an array 
	 * @param lvlnum
	 * @return
	 */
	public static int[][] getLevelData(int lvlnum){
		String fileName = null;
		switch (lvlnum) {
		case 1:
			fileName = "res/map_1.txt";
			break;
		case 2:
			fileName = "res/map_2.txt";
			break;
		case 3:
			fileName = "res/map_3.txt";
		}
		int[][] lvlData = new int[TILES_IN_WIDTH][TILES_IN_HEIGHT];
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String line;
            int num;
            for (int r = 0; r < TILES_IN_HEIGHT; r++) {
                line = in.readLine();
                String numbers[] = line.split(" ");
                for (int c = 0; c < Game.TILES_IN_WIDTH; c++) {
                    num = Integer.parseInt(numbers[c]);
                    lvlData[c][r] = num; //map numbers stored in array
                }
            }
            in.close();
		} catch (IOException e) {
			System.out.println("Error reading map file");
		}
		return lvlData;
	}

	/**
	 * load the proper player sprites depending on which character was selected 
	 * @return
	 */
	public static String getPlayerAtlas() {
		switch (selectedCharacter) {
        
		case "finn":
            return PLAYERATLAS;
            
        case "fionna":
            return PLAYERATLAS2;
		}
		return selectedCharacter;
	}
	
	public static boolean setSelectedCharacter(String character) {
        selectedCharacter = character;
		return true;
    }
}
