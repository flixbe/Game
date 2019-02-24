package core.level.tile;

import core.graphics.Renderer;
import core.graphics.Sprite;

public class Tile {

	public int x, y;
	public Sprite sprite;
	
	public static Tile grass = new Grass(Sprite.grass);
	public static Tile void_tile  = new Void(Sprite.void_sprite);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Renderer renderer) {
		
	}
	
	public boolean solid() {
		return false;
	}
	
}