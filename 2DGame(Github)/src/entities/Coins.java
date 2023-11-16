package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import utils.LoadSave;

public abstract class Coins extends Entity{
	BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.COINATLAS);
	//private BufferedImage enemy1, enemy2, enemy3;
	public Coins(float x, float y, int width, int height) {
		super(x, y, width, height);
		initializeHitbox(x, y, width, height);	
	}
	
	public abstract void drawCharacter(Graphics g); // abstract method forces implementation of enemy appearance for each enemy type
	
}