package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import static main.Game.SCALE;

/**
 * 
 * Entity class that handles creating the hitbox used by subclasses
 * 
 * @author Furqan, Licia, Farhana
 *
 */

public abstract class Entity {

	protected float x, y;
	protected int width, height;
	protected Rectangle2D.Float hitbox;
	
	public Entity(float x, float y, int width, int height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
	
	/**
	 * 
	 * Draw a red square to represent the entities hitbox
	 * Used for testing purposes only 
	 * 
	 * @param g
	 */
	//for debugging hitbox
	protected void drawHitbox(Graphics g) { 
		g.setColor(Color.RED);
		g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
	}

	protected void initializeHitbox(float x, float y, int width, int height) {
		hitbox = new Rectangle2D.Float(x, y, width, height);
	}
	
	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}
}
