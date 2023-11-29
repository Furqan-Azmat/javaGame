package entities;

import static utils.Constants.PlayerConstants.GetSpriteAmount;
import static utils.Constants.PlayerConstants.IDLE;
import static utils.Constants.PlayerConstants.RUNNING;

import static utils.HelperMethods.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.LoadSave;
import main.Game;

import static main.Game.*;

/**
 * 
 * Player class that handles all the logic related to the playable character
 * 
 * @author Furqan, Licia, Farhana
 *
 */

public class Player extends Entity{
	//basic character movements 
	private BufferedImage[][] animation; //stores all the frames of the sprite in a 3D array
	private int animationTick, animationIndex, animationSpeed = 30; // how fast the player animates
	private int playerAction = IDLE; // default action
	private boolean moving = false; // if idle not moving
	private boolean left, up, right, down, jump; //wasd and jump
	private float playerSpeed = 0.5f * SCALE; // how fast the character moves 
	private int[][] lvlData;
	private float xDrawOffset = 1 * Game.SCALE;
	private float yDrawOffset = 0 * Game.SCALE;
	
	// jumping / gravity 
	private float airSpeed = 0f; // the speed at which we are traveling through the air, jumping and falling 
	private float gravity = 0.01f * SCALE; // the speed at which the player fall back down  
	private float jumpSpeed = -0.81f * SCALE; // jumping up in y direction 
	private float fallSpeedAfterCollision = 0.5f * SCALE; // in case the player is hitting the roof
	private boolean inAir = false; // is player in air 

	public Player(float x, float y, int width, int height) { //player constructor 
		super(x, y, width, height);
		loadAnimation();
		initializeHitbox(x, y, (int) (14 * SCALE),(int) (15 * SCALE)); //size of hitbox 	
	}

	/**
	 * Constantly update the player character with the proper information
	 */
	public void update() {
		updatePlayerPosition(); //sets player position based on keyboard input 
		updateAnimation(); // animates the character
		setAnimation(); //sets animation based on player action 
	}

	/**
	 * Draw the playable character
	 * @param g
	 */
	public void render(Graphics g) {
		g.drawImage(animation[playerAction][animationIndex], (int)(hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset),width,height, null); //64 - size of the sprite, can increase and decrease size 
		//drawHitbox(g); //for debugging hitbox 
	}
	
	
	/**
	 * Method for animating the playable character
	 */
	//method to loop through the array list of frames to "animate" the player
	private void updateAnimation() {
			animationTick++;
			if(animationTick >= animationSpeed) {
				animationTick = 0;
				animationIndex++;
				if(animationIndex >= GetSpriteAmount(playerAction)) //getSpritAmount returns the number of frames for each animation in the sprite
					animationIndex = 0;
			}
	}
	
	/**
	 * Method that is used to play the appropriate animation based off the player state
	 */
	public void setAnimation() {
		int startAnimation = playerAction;
		if(moving)
			playerAction = RUNNING; //if moving, use the running animation
		else
			playerAction = IDLE; //if idle, use idle animation 
		
		if (startAnimation != playerAction)
			resetAnimationTick();
	}

	private void resetAnimationTick() {
		animationTick = 0;
		animationIndex = 0;
	}
	/**
	 * Method used to allow the player to move around the screen as well as for checking 
	 * collision with the map layout making sure player does not get stuck
	 * and is able to move around freely
	 */
	private void updatePlayerPosition() {
		moving = false;

	    float xSpeed = 0;
	    if (left)
	        xSpeed -= playerSpeed;
	    if (right)
	        xSpeed += playerSpeed;
	    updateXPosition(xSpeed);
	    if (jump)
	        jump();
	    if (!left && !right && !inAir)
	        return;
	    if (!inAir) {
	        if (!IsEntityOnFloor(hitbox, lvlData)) {
	            inAir = true;
	        }
	    }
	    if (inAir) {
	        // Handle Y-axis movement and collisions
	        float ySpeed = airSpeed + gravity;
	        if (CanMoveHere(hitbox.x, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData)) {
	            hitbox.y += ySpeed;
	            airSpeed += gravity;
	        } else {
	            hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
	            if (airSpeed > 0)
	                resetInair();
	            else {
	                airSpeed = fallSpeedAfterCollision;
	            }
	        }
	    }
	    moving = true;
	}

	/**
	 * Method to update the player information if the character is in the air
	 */
	private void jump() {
		if(inAir)
			return;
		inAir = true;
		airSpeed = jumpSpeed;
	}

	private void resetInair() {
		inAir = false;
		airSpeed = 0;
	}

	/**
	 * Let the player move along the x-axis
	 * @param xSpeed
	 */
	private void updateXPosition(float xSpeed) {
		if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
	        hitbox.x += xSpeed;
	    } else {
	        // Handle collision with wall
	        hitbox.x = GetEntityPosNextToWall(hitbox, xSpeed);
	    }
	}

	/**
	 * Method to load in the appropriate player sprite and frames into an array allowing 
	 * for the character animation 
	 */
	private void loadAnimation() {	
		BufferedImage img;
	    if (LoadSave.getPlayerAtlas().equals(LoadSave.PLAYERATLAS)) {
	        img = LoadSave.GetSpriteAtlas(LoadSave.PLAYERATLAS);
	    } else {
	        img = LoadSave.GetSpriteAtlas(LoadSave.PLAYERATLAS2);
	    }

	    animation = new BufferedImage[3][3]; //size of the sprite
	    for (int i = 0; i < animation.length; i++)
	        for (int j = 0; j < animation[i].length; j++)
	            animation[i][j] = img.getSubimage(j * 16, i * 16, 16, 16); 
	}
		
	public void reloadAnimation() {
		loadAnimation();
	}
	
	/**
	 * 
	 * Check if the player is in the air 
	 * 
	 * @param lvlData
	 */
	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
		if(!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
	}
	
	/**
	 * Respawn the player to specified coordinates 
	 */
	public void respawn() {
		hitbox.x = 32f;
		hitbox.y = 576f;
	}
	public void resetDirectionBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
	
	public void setJump(boolean jump) {
		this.jump = jump;
	}
}