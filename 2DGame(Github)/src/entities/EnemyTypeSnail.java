package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyTypeSnail extends Enemy {
	public EnemyTypeSnail(float x, float y, int width, int height) {
		super(x, y, width, height);
	}

	public void drawCharacter(Graphics g) {
		BufferedImage enemyImage = img.getSubimage(16, 0, 16, 16);
		g.drawImage(enemyImage, (int)(x), (int) (y),width,height, null);
		initializeHitbox(x, y, width, height);	
		movement(0.5f);
	}
}