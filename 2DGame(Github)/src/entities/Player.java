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
	private float jumpSpeed = -0.75f * SCALE; // jumping up in y direction 
	private float fallSpeedAfterCollision = 0.5f * SCALE; // in case the player is hitting the roof
	private boolean inAir = false; // is player in air 

	public Player(float x, float y, int width, int height) { //player constructor 
		super(x, y, width, height);
		loadAnimation();
		initializeHitbox(x, y, (int) (14 * SCALE),(int) (15 * SCALE)); //size of hitbox 	
	}

	public void update() {
		updatePlayerPosition(); //sets player position based on keyboard input 
		updateAnimation(); // animates the character
		setAnimation(); //sets animation based on player action 
	}

	public void render(Graphics g) {
		g.drawImage(animation[playerAction][animationIndex], (int)(hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset),width,height, null); //64 - size of the sprite, can increase and decrease size 
		//drawHitbox(g); //for debugging hitbox 
	}
	
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
	
	//method to set the animation of the player based on the action
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
	
	private void updatePlayerPosition() {
		moving = false;
		if(jump)
			jump();
		if(!left && !right && !inAir)
			return;
		float xSpeed = 0;
		if(left )  // if character is moving left 
			xSpeed -= playerSpeed;
		if (right ) // if character is moving right 
			xSpeed += playerSpeed; 
		if(!inAir) {
			if(!IsEntityOnFloor(hitbox, lvlData)) {
				inAir = true;
			}
		}
		if(inAir) { //need to check both x and y direction 
			if(CanMoveHere(hitbox.x + xSpeed, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
				hitbox.y += airSpeed;
				airSpeed += gravity;
				updateXPosition(xSpeed);
			}	
			else {
				hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
				if(airSpeed > 0)
					resetInair();
				else {
					airSpeed = fallSpeedAfterCollision;
					updateXPosition(xSpeed);
				}
			}
		}
		else { // if not in air, only need to check x direction 
			updateXPosition(xSpeed);
		}
		moving = true;
	}

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

	private void updateXPosition(float xSpeed) {
		if(CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x += xSpeed;
		}
		else {
			hitbox.x = GetEntityPosNextToWall(hitbox, xSpeed);
		}
	}

	//method to load the player sprite and the frames of the sprite into an array
	private void loadAnimation() {
			BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYERATLAS);	
			animation = new BufferedImage[3][3]; //size of the sprite
			for (int i = 0; i < animation.length; i++)
			for(int j = 0; j < animation[i].length; j++)
				animation[i][j] = img.getSubimage(j*16, i*16, 16, 16); //16 is the size of each 'frame' is the sprite
	}
	
	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
		if(!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
	}
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