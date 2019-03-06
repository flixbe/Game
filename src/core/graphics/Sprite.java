package core.graphics;

public class Sprite {

	private int x, y;
	public final int SIZE;
	public int[] pixels;
	
	private SpriteSheet sheet;
	
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite void_sprite = new Sprite(16, 0);
	
	public static Sprite player_forward_stay  = new Sprite(16, 0, 12, SpriteSheet.tiles);
	public static Sprite player_right_stay    = new Sprite(16, 1, 12, SpriteSheet.tiles);
	public static Sprite player_backward_stay = new Sprite(16, 2, 12, SpriteSheet.tiles);
	public static Sprite player_left_stay     = new Sprite(16, 3, 12, SpriteSheet.tiles);
	
	public static Sprite player_forward_walk_one  = new Sprite(16, 0, 13, SpriteSheet.tiles);
	public static Sprite player_forward_walk_two  = new Sprite(16, 0, 15, SpriteSheet.tiles);
	public static Sprite player_right_walk_one    = new Sprite(16, 1, 13, SpriteSheet.tiles);
	public static Sprite player_right_walk_two    = new Sprite(16, 1, 15, SpriteSheet.tiles);
	public static Sprite player_backward_walk_one = new Sprite(16, 2, 13, SpriteSheet.tiles);
	public static Sprite player_backward_walk_two = new Sprite(16, 2, 15, SpriteSheet.tiles);
	public static Sprite player_left_walk_one     = new Sprite(16, 3, 13, SpriteSheet.tiles);
	public static Sprite player_left_walk_two     = new Sprite(16, 3, 15, SpriteSheet.tiles);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		
		load();
	}
	
	public Sprite(int size, int color) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	private void setColor(int color) {
		for (int i = 0; i < SIZE * SIZE; i++)
			pixels[i] = color;
	}
	
	private void load() {
		for (int y = 0; y < SIZE; y++)
			for (int x = 0; x < SIZE; x++)
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
	}
	
}