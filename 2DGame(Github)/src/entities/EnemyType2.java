package entities;

import java.awt.*;

public class EnemyType2 extends Enemy {

	public EnemyType2(float x, float y, int width, int height) {
		super(x, y, width, height);
	}

	public void drawCharacter(Graphics g) {
		g.setColor(new Color(222, 0, 37));
		g.drawRect((int)x, (int)y, width, height);
	}

}
