package core.entity.mob;

import java.awt.event.KeyEvent;

import core.graphics.Renderer;
import core.graphics.Sprite;
import core.input.Input;

public class Player extends Mob {

	private Input input;
	
	public Player(Input input) {
		this.input = input; 
	}
	
	public Player(int x, int y, Input input) {
		this.x = x;
		this.y = y;
		this.input = input;
	}
	
	public void update() {
		int xa = 0, ya = 0;
		if (Input.isKeyPressed(KeyEvent.VK_W) || Input.isKeyPressed(KeyEvent.VK_UP))    ya--;
		if (Input.isKeyPressed(KeyEvent.VK_S) || Input.isKeyPressed(KeyEvent.VK_DOWN))  ya++;
		if (Input.isKeyPressed(KeyEvent.VK_A) || Input.isKeyPressed(KeyEvent.VK_LEFT))  xa--;
		if (Input.isKeyPressed(KeyEvent.VK_D) || Input.isKeyPressed(KeyEvent.VK_RIGHT)) xa++;
		
		if (xa != 0 || ya != 0) 
			move(xa, ya);
	}
	
	public void render(Renderer renderer) {
		renderer.renderPlayer(x, y, Sprite.player);
	}
	
}
