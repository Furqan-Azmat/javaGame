package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import utils.LoadSave;

public abstract class Coins extends Entity{
	BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.COINATLAS);
	public Coins(float x, float y, int width, int height) {
		super(x, y, width, height);
		initializeHitbox(x, y, width, height);	
	}
	
	public abstract void drawCharacter(Graphics g); 
	
}