package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 
 * Subclass of Enemy class to create specific enemy
 * 
 * @author Furqan, Licia, Farhana
 *
 */

public class EnemyTypeGhost extends Enemy {

	public EnemyTypeGhost(float x, float y, int width, int height) {
		super(x, y, width, height);
	}
	
	/**
	 * Override the drawCharacter method in parent class to create new enemy
	 * with the appropriate features 
	 */
	public void drawCharacter(Graphics g) {
		BufferedImage enemyImage = img.getSubimage(0, 0, 16, 16);
		g.drawImage(enemyImage, (int)(x), (int) (y),width,height, null);
		initializeHitbox(x, y, width, height);	
		movement(1f);
		
	}
}
