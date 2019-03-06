package core.entity.mob;

import java.awt.event.KeyEvent;

import core.graphics.Renderer;
import core.graphics.Sprite;
import core.input.Input;

public class Player extends Mob {

	private Input input;
	private Sprite sprite;
	private int animate = 0;
	private boolean walking = false;
	
	public Player(Input input) {
		this.input = input;
		sprite = Sprite.player_forward_stay;
	}
	
	public Player(int x, int y, Input input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_forward_stay;
	}
	
	public void update() {
		int xa = 0, ya = 0;
		
		if (animate < 7500)
			animate++;
		else
			animate = 0;
		
		if (Input.isKeyPressed(KeyEvent.VK_W) || Input.isKeyPressed(KeyEvent.VK_UP))    ya--;
		if (Input.isKeyPressed(KeyEvent.VK_S) || Input.isKeyPressed(KeyEvent.VK_DOWN))  ya++;
		if (Input.isKeyPressed(KeyEvent.VK_A) || Input.isKeyPressed(KeyEvent.VK_LEFT))  xa--;
		if (Input.isKeyPressed(KeyEvent.VK_D) || Input.isKeyPressed(KeyEvent.VK_RIGHT)) xa++;
		
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}
	
	public void render(Renderer renderer) {
		if (direction == 1) {
			sprite = Sprite.player_forward_stay;
			if (walking) {
				if (animate % 20 > 10)
					sprite = Sprite.player_forward_walk_one;
				else
					sprite = Sprite.player_forward_walk_two;
			}
		}
		if (direction == 2) {
			sprite = Sprite.player_right_stay;
			if (walking) {
				if (animate % 20 > 10)
					sprite = Sprite.player_right_walk_one;
				else if (animate % 20 > 5)
					sprite = Sprite.player_right_stay;
				else
					sprite = Sprite.player_right_walk_two;
			}
		}
		if (direction == 3) {
			sprite = Sprite.player_backward_stay;
			if (walking) {
				if (animate % 20 > 10)
					sprite = Sprite.player_backward_walk_one;
				else
					sprite = Sprite.player_backward_walk_two;
			}
		}
		if (direction == 4) {
			sprite = Sprite.player_left_stay;
			if (walking) {
				if (animate % 20 > 10)
					sprite = Sprite.player_left_walk_one;
				else if (animate % 20 > 5)
					sprite = Sprite.player_left_stay;
				else
					sprite = Sprite.player_left_walk_two;
			}
		}
		renderer.renderPlayer(x, y, sprite);
	}
	
}
