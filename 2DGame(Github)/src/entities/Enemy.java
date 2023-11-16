package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import utils.LoadSave;

public abstract class Enemy extends Entity{
	BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.ENEMYATLAS);
	//private BufferedImage enemy1, enemy2, enemy3;
	public Enemy(float x, float y, int width, int height) {
		super(x, y, width, height);
		initializeHitbox(x, y, width, height);	

	}
	
	public abstract void drawCharacter(Graphics g); // abstract method forces implementation of enemy appearance for each enemy type
	
}
