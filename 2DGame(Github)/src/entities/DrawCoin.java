package entities;

import java.awt.*;
import java.awt.image.BufferedImage;



public class DrawCoin extends Coins {
	private boolean isDisplayed = true;

    public DrawCoin(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public void drawCharacter(Graphics g) {
    	// Initially start by displaying coins
        if (isDisplayed) {
            BufferedImage coin = img.getSubimage(0, 0, 28, 26);
            g.drawImage(coin, (int) (x), (int) (y), width, height, null);
            drawHitbox(g);
        }
    }

    // If the coin is visible and the coins hitbox intersects the players hitbox
    public boolean checkCollisionWithPlayer(Player player) {
        if (isDisplayed && super.getHitbox().intersects(player.getHitbox())) {
            isDisplayed = false; // Turn the coin that was touched off 
            return true; // Collision detected
        }
        return false; // No collision
    }

    public boolean isDisplayed() {
        return isDisplayed;
    }
}