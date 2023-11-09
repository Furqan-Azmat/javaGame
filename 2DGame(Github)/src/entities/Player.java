package entities;

import static utils.Constants.PlayerConstants.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import utils.LoadSave;

public class Player extends Entity{

	private BufferedImage[][] animation; //stores all the frames of the sprite in a 3D array
	private int animationTick, animationIndex, animationSpeed = 30;
	private int playerAction = IDLE; // default action
	private boolean moving = false; // if idle not moving
	private boolean left, up, right, down;
	private float playerSpeed = 1.0f;
	
	public Player(float x, float y, int width, int height) { //player constructor 
		super(x, y, width, height);
		loadAnimation();
	}

	public void update() {
		updatePlayerPosition(); //sets player position based on keyboard input 
		updateAnimation(); // animates the character
		setAnimation(); //sets animation based on player action 
	}
	
	public void render(Graphics g) {
		
		g.drawImage(animation[playerAction][animationIndex], (int)x, (int)y,64,64, null); //64 - size of the sprite, can increase and decrease size 

	}
	

	//method to load the player sprite and the frames of the sprite into an array
	private void loadAnimation() {
		 
			BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYERATLAS);
					
			animation = new BufferedImage[3][3]; //size of the sprite
			
			for (int i = 0; i < animation.length; i++)
			for(int j = 0; j < animation[i].length; j++)
				animation[i][j] = img.getSubimage(j*16, i*16, 16, 16); //16 is the size of each 'frame' is the sprite
		
	
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
	
	//method to set the animation of the player based on its action
	public void setAnimation() {
		int startAnimation = playerAction;
		
		if(moving)
			playerAction = RUNNING;
		else
			playerAction = IDLE;
		
		if (startAnimation != playerAction)
			resetAnimationTick();
	}
	
	private void resetAnimationTick() {
		animationTick = 0;
		animationIndex = 0;
	}

	private void updatePlayerPosition() {
		moving = false;
		
		if(left && !right) { //if character is moving ledt and not right
			x -= playerSpeed;
			moving = true;
		}
		else if (right && !left) {
			x += playerSpeed;
			moving = true;
		}
		
		if(up && !down) { //if character is moving up and not down
			y -= playerSpeed;
			moving = true;
		}
		else if (down && !up) {
			y += playerSpeed;
			moving = true;
		}
		
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
	
	
	
	
	
}
