package core.graphics;

import java.util.Random;

import core.level.tile.Tile;

public class Renderer {

	private static final Random random = new Random();
	
	private final int MAP_SIZE = 64;
	private final int MAP_SIZE_MASK = MAP_SIZE - 1;
	
	private int width, height;
	private int xOffset, yOffset;
	private int tileIndex;
	private int[] pixels;
	private int[] tiles = new int[MAP_SIZE * MAP_SIZE];	
	
	public Renderer(int width, int height, int[] pixels) {
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = 0;
	}
	
	public void renderTile(int xPosition, int yPosition, Tile tile) {
		xPosition -= xOffset;
		yPosition -= yOffset;
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int yAbsolute = y + yPosition;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xAbsolute = x + xPosition;
				if (xAbsolute < -tile.sprite.SIZE || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break;
				if (xAbsolute < 0) xAbsolute = 0;
				pixels[xAbsolute + yAbsolute * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
}