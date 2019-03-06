package core.entity.mob;

import core.entity.Entity;
import core.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int direction = 0;
	protected boolean moving = false;
	
	public void move(int xAxis, int yAxis) {
		if (yAxis < 0) direction = 1;
		if (xAxis > 0) direction = 2;
		if (yAxis > 0) direction = 3;
		if (xAxis < 0) direction = 4;
		
		
		if (!collision()) {
			x += xAxis;
			y += yAxis;	
		}
	}
	
	public void update() {
		
	}
	
	public void render() {
		
	}
	
	private boolean collision() {
		return false;
	}
	
}
