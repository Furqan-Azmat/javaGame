package entities;

import java.awt.*;


public abstract class Enemy extends Entity{
	

	public Enemy(float x, float y, int width, int height) {
		super(x, y, width, height);
		initializeHitbox(x, y, width, height);
	}
	
	public abstract void drawCharacter(Graphics g); // abstract method forces implementation of enemy appearance for each enemy type
	// hitbox method
	
	public void setMovement() {
		// set the back and forth enemy movement
	}


}
