package core.entity;

import java.util.Random;

import core.graphics.Renderer;
import core.level.Level;

public abstract class Entity {

	public int x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void update() {
		
	}
	
	public void render(Renderer renderer) {
		
	}
	
	public void remove() {
		removed = true; //remove from level
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
}
