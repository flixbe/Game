import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import core.entity.mob.Player;
import core.graphics.Renderer;
import core.input.Input;
import core.level.Level;
import core.level.SpawnLevel;

@SuppressWarnings("serial")
public class Main extends Canvas {

	private static final String TITLE = "Game";
	private static final int WIDTH = 300;
	private static final int HEIGHT = WIDTH / 16 * 9;
	private static final int SCALE = 3;
	
	private boolean running = false;
	
	private Renderer renderer;
	private Level level;
	private Player player;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Main() {
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		addKeyListener(new Input());
		renderer = new Renderer(WIDTH, HEIGHT, pixels);
		level = new SpawnLevel("/textures/level.png");
		player = new Player(8 * 16, 7 * 16, new Input());
	}
	
	private void start() {
		running = true;
		init();
		
		new Thread(() -> {
			requestFocus();
			
			long lastTime = System.nanoTime();
			long timer = System.currentTimeMillis();
			
			final double PART_TIME = 1_000_000_000.0 / 60.0;
			double delta = 0.0; 
			
			int updates = 0;
			int frames = 0;
			
			while (running) {
				long now = System.nanoTime();
				delta += (now - lastTime) / PART_TIME;
				lastTime = now;
				
				if (delta >= 1.0) {
					update();
					updates++;
					delta--;
				}
				
				render();
				frames++;
				
				if (System.currentTimeMillis() - timer > 1000) {
					timer += 1000;
					System.out.println("UPS: " + updates + " | FPS: " + frames);
					updates = 0;
					frames = 0;
				}
			}
			running = false;
		}, "Main").start();
	}
	
	private void update() {
		player.update();
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		renderer.clear();
		int xScroll = player.x - renderer.getWidth() / 2;
		int yScroll = player.y - renderer.getHeight() / 2;
		level.render(xScroll, yScroll, renderer);
		player.render(renderer);
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	private void init() {
		JFrame frame = new JFrame(TITLE);
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {	
		new Main().start();
	}
	
}