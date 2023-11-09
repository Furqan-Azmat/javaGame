package utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class LoadSave {

	public static final String PLAYERATLAS = "player.png";
	public static final String TILE1 = "tile_1.png";
	public static final String TILE2 = "tile_2.png";
	public static final String LEVELATLAS = "tile.png";

	
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
}
