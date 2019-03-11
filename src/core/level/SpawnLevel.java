package core.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import core.level.tile.Grass;
import core.level.tile.Tile;

public class SpawnLevel extends Level {
	
	public SpawnLevel(String path) {
		super(path);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			pixelsColors = new int[w * h];
			image.getRGB(0, 0, w, h, pixelsColors, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("[ERROR] COULDN'T LOAD LEVEL FILE!");
		}
	}
	
	protected void generateLevel() {
		
	}
	
}