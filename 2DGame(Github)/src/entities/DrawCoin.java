package entities;

import java.awt.*;
import java.awt.image.BufferedImage;



public class DrawCoin extends Coins {
	private boolean isDisplayed = true;

    public DrawCoin(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public void drawCharacter(Graphics g) {
        if (isDisplayed) {
            BufferedImage coin = img.getSubimage(0, 0, 28, 26);
            g.drawImage(coin, (int) (x), (int) (y), width, height, null);
            drawHitbox(g);
        }
    }

    public boolean checkCollisionWithPlayer(Player player) {
        if (isDisplayed && super.getHitbox().intersects(player.getHitbox())) {
            isDisplayed = false;
            return true; // Collision detected
        }
        return false; // No collision
    }

    public boolean isDisplayed() {
        return isDisplayed;
    }
}