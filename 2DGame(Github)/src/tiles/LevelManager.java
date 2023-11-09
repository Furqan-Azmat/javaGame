package tiles;

import static main.Game.TILES_IN_HEIGHT;
import static main.Game.TILES_IN_WIDTH;
import static main.Game.TILE_SIZE;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.Game;
import utils.LoadSave;
import java.util.Scanner;

public class LevelManager {
	
	private Game game;
	private Tile[] tile;
	private BufferedImage[] levelSprite;
	private int mapTileNum[][];
	
	public LevelManager(Game game) {
		this.game = game;
		tile = new Tile[2]; //number of tiles we have
		mapTileNum = new int[TILES_IN_WIDTH][TILES_IN_HEIGHT];
		importTileSprite();
		importMap();
	
	}
	
	
	
	private void importMap() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("res/map_1.txt"));
			String line;
			int num;
			for (int r = 0; r < TILES_IN_HEIGHT; r++) {
				line = in.readLine();
				String numbers[] = line.split(" ");
				for (int c = 0; c < TILES_IN_WIDTH; c++) {
					num = Integer.parseInt(numbers[c]);
					mapTileNum[c][r] = num;
				}
			}
			
			in.close();
		} catch (IOException e) {
			System.out.println("not working bruj"); }	
	}

	private void importTileSprite() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVELATLAS);
		levelSprite = new BufferedImage[2];	
		for(int j = 0; j < 1; j++)
			for(int i = 0; i < 2; i++) {
				int index = j*2 + i;
				levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
			}
	}

	public void draw(Graphics g) {
	int x = 0;
	int y = 0;
	int tileNum;
		for (int r = 0; r < TILES_IN_HEIGHT; r++) {
			for (int c = 0; c < TILES_IN_WIDTH; c++) {
				tileNum = mapTileNum[c][r];
				g.drawImage(levelSprite[tileNum], x, y, TILE_SIZE,TILE_SIZE, null);
				x += TILE_SIZE;
			}
			y += TILE_SIZE;
			x = 0;
		}
	}

    public void printMap(int[][] mapTileNum) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(mapTileNum[i][j] + " ");
            }
            System.out.println();
        }}
	
//	public void update() {
//		
//	}
	

}