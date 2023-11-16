package entities;

import java.awt.*;
import java.awt.image.BufferedImage;



public class DrawCoin extends Coins {
	
	

	public DrawCoin(float x, float y, int width, int height) {
		super(x, y, width, height);
	}

	public void drawCharacter(Graphics g) {
		BufferedImage coin = img.getSubimage(0, 0, 28, 26);
		g.drawImage(coin, (int)(x), (int) (y),width,height, null);
		drawHitbox(g);
	}
}