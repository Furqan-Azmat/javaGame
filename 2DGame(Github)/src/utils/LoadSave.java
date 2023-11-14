package utils;

import static main.Game.TILES_IN_HEIGHT;
import static main.Game.TILES_IN_WIDTH;
import static main.Game.WORLD_IN_WIDTH;
import static main.Game.WORLD_IN_HEIGHT;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {

	
	public static final String PLAYERATLAS = "player.png";
	public static final String LEVELATLAS = "tile_final.png";
	

	
	public static BufferedImage GetSpriteAtlas(String SpriteFileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + SpriteFileName);
		
		try { 
			img = ImageIO.read(is);
			
		} catch (IOException e) {
			e.printStackTrace();
		
		} finally {
			
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
		
	}
	
	public static int[][] getLevelData(){
		int[][] lvlData = new int[WORLD_IN_WIDTH][TILES_IN_HEIGHT];
		try {
			BufferedReader in = new BufferedReader(new FileReader("res/map_1_long.txt"));
			
			String line;
            int num;
            for (int r = 0; r < TILES_IN_HEIGHT; r++) {
                line = in.readLine();
                String numbers[] = line.split(" ");
                for (int c = 0; c < WORLD_IN_WIDTH; c++) {
                    num = Integer.parseInt(numbers[c]);
                    lvlData[c][r] = num; //map numbers stored in array
                }
            }
            in.close();
		} catch (IOException e) {
			System.out.println("file not read");
		}
		return lvlData;
	
		
	
		
	}
}
