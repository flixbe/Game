package core.level.tile;

import core.graphics.Renderer;
import core.graphics.Sprite;

public class Void extends Tile {

	public Void(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Renderer renderer) {
		renderer.renderTile(x << 4, y << 4, this);
	}
	
}
