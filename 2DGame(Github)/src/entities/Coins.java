package entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import utils.LoadSave;

/**
 * 
 * Coin class that is responsible for creating coin objects and checking for collision
 * 
 * @author Furqan, Licia, Farhana
 *
 */

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
	
	/**
	 * Method to draw coins by using the Graphics method 
	 * 
	 * @param g
	 */
	public void drawCoin(Graphics g) {
    	// Initially start by displaying coins
        if (isDisplayed) {
            g.drawImage(coin, (int) (x), (int) (y), width, height, null);
            movement();
        }
    }
	
	/**
	 * Method to move the coin up and game 
	 * Moves the coin between 2 positions (locationA and locationB)
	 */
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
    
    /**
     * Method to check whether or not the player has "picked up" a coin 
     * Checks to see if isDisplayed && super.getHitbox().intersects(player.getHitbox())
     * are both true, if they are it sets isDisplayed to false which tells the code to no
     * longer have it drawn on screen
     * 
     * @param player
     * @return
     */
    public boolean checkCollisionWithPlayer(Player player) {
        if (isDisplayed && super.getHitbox().intersects(player.getHitbox())) {
            isDisplayed = false; // Turn the coin that was touched off 
            return true; // Collision detected
        }
        return false; // No collision
    }
    
    /**
     * Check the value of the isDiaplayed variable
     * @return
     */
    public boolean isDisplayed() {
        return isDisplayed;
    }
}