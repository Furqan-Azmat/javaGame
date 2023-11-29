package utils;

import static main.Game.GAME_WIDTH;
import static main.Game.TILES_IN_HEIGHT;
import static main.Game.TILES_IN_WIDTH;
import static main.Game.GAME_HEIGHT;
import static main.Game.TILE_SIZE;
import java.awt.geom.Rectangle2D;

/**
 * 
 * Class that is responsible for handling all game collision 
 * 
 * @author Furqan, Licia, Farhana
 *
 */

public class HelperMethods {
	
	/**
	 * 
	 * Create a barrier around the screen that prevents the player from leaving it 
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param lvlData
	 * @return
	 */
	public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
		if(!IsSolid(x,y, lvlData)) //top left 
			if(!IsSolid(x+width, y+height, lvlData)) // bottom right 
				if(!IsSolid(x+width, y, lvlData)) // top right 
					if(!IsSolid(x, y+height, lvlData)) // bottom left 
						return true;
		return false;				
	}

	
	/**
	 * 
	 * Check what parts of the game levels is considered "solid", i.e. the player would collide with 
	 * 
	 * @param x
	 * @param y
	 * @param lvlData
	 * @return
	 */
	private static boolean IsSolid(float x, float y, int[][] lvlData) {
		
		if(x < 0 || x >= GAME_WIDTH) //if x position is outside the entire game map width
			return true; // it is solid 
		if(y < 0 || y >= GAME_HEIGHT)// if y position is outside the game height 
			return true; //it is solid 
		//if both return false, then we are inside game  
		
		float xIndex = x / TILE_SIZE; //player size * scale x4
		float yIndex = y / TILE_SIZE;
		
		int value; //lvlData[(int) yIndex][(int) xIndex];
		
		for (int r = 0; r < TILES_IN_HEIGHT; r++) {
            //for (int c = 0; c < TILES_IN_WIDTH; c++) {
			for (int c = 0; c < TILES_IN_WIDTH; c++) {
            	//levelOne.getSpriteIndex(r, c);
            	value = lvlData[(int) xIndex ][(int) yIndex];
            	if (value == 0)
       			return true;
            }
		}
		return false;		
	}
	
	/**
	 * 
	 * Check if the player is next to a wall 
	 * 
	 * @param hitbox
	 * @param xSpeed
	 * @return
	 */
	public static float GetEntityPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
		int currentTile = (int) (hitbox.x / TILE_SIZE); // current tile the entity is on
		if (xSpeed > 0) {
			// right
			int tileXPosition = currentTile * TILE_SIZE;
			int xOffset = (int) (TILE_SIZE - hitbox.width);
			return tileXPosition + xOffset -1;
		}
		else {
			// left
			return currentTile * TILE_SIZE;
		}
	}
	
	/**
	 * 
	 * Controls how the player behaves when approaching terrain from below
	 * 
	 * @param hitbox
	 * @param ySpeed
	 * @return
	 */
	public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float ySpeed) {
		int currentTile = (int) (hitbox.y / TILE_SIZE); // current tile the entity is on
		if (ySpeed > 0) {
			// falling - touching floor
			int tileYPosition = currentTile * TILE_SIZE;
			int yOffset = (int) (TILE_SIZE - hitbox.height);
			return tileYPosition + yOffset -1;
		}
		else {
			// jumping
			return currentTile * TILE_SIZE;
		}
	}
	
	/**
	 * Check of player is on the floor 
	 * 
	 * @param hitbox
	 * @param lvlData
	 * @return
	 */
	public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
		//check the pixel below bottom left and bottom right 
		if(!IsSolid(hitbox.x, hitbox.y + hitbox.height +1, lvlData)) //bottom left
			if(!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height +1, lvlData)) // bottom right 
				return false;
		return true;	
	}
}
