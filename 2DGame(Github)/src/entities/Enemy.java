package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import utils.LoadSave;

public abstract class Enemy extends Entity{
    BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.ENEMYATLAS);
    private float locationA;
    private float locationB;
    private boolean forward = true;

    public Enemy(float x, float y, int width, int height) {
        super(x, y, width, height);
        locationA = x;
        locationB = x + 100;
    }

    public abstract void drawCharacter(Graphics g); // abstract method forces implementation of enemy appearance for each enemy type

    public void movement(float speed) {
        // horizontally move enemy back and forth
        if (forward == true)
            x = x + speed;
        else
            x = x - speed;
        // check if enemy should move forward or backward
        if (x == locationB)
            forward = false;
        else if (x == locationA)
            forward = true;
    }
}


