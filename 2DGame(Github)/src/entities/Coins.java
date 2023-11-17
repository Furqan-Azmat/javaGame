package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import utils.LoadSave;

public class Coins extends Entity{
	BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.COINATLAS);
	BufferedImage coin = img.getSubimage(0, 0, 28, 26);
	private boolean isDisplayed = true;
	private boolean up = true;
	float locationA, locationB;
	
	public Coins(float x, float y, int width, int height) {
		super(x, y, width, height);
		initializeHitbox(x, y, width, height);	
		locationA = y;
		locationB = y - 10;
	}
	
	public void drawCoin(Graphics g) {
    	// Initially start by displaying coins
        if (isDisplayed) {
            g.drawImage(coin, (int) (x), (int) (y), width, height, null);
            movement();
        }
    }
    public void movement() {
    	if (up == true)
			y = y - 0.25f;
		else
			y = y + 0.25f;
		if (y == locationB)
			up = false;
		else if (y == locationA)
			up = true;
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