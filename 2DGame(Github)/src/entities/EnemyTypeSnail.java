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

public class EnemyTypeSnail extends Enemy {
	public EnemyTypeSnail(float x, float y, int width, int height) {
		super(x, y, width, height);
	}

	
	/**
	 * Override the drawCharacter method in parent class to create new enemy
	 * with the appropriate features 
	 */
	public void drawCharacter(Graphics g) {
		BufferedImage enemyImage = img.getSubimage(16, 0, 16, 16);
		g.drawImage(enemyImage, (int)(x), (int) (y),width,height, null);
		initializeHitbox(x, y, width, height);	
		movement(0.5f);
	}
}