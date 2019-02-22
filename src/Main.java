import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import core.graphics.Renderer;
import core.input.Input;

@SuppressWarnings("serial")
public class Main extends Canvas {

	private static final String TITLE = "Game";
	private static final int WIDTH = 300;
	private static final int HEIGHT = WIDTH / 16 * 9;
	private static final int SCALE = 3;
	
	private boolean running = false;
	
	private Renderer renderer;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	private int x = 0;
	private int y = 0;
	
	public Main() {
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		addKeyListener(new Input());
		renderer = new Renderer(WIDTH, HEIGHT, pixels);
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
		if (Input.isKeyPressed(KeyEvent.VK_W) || Input.isKeyPressed(KeyEvent.VK_UP))    y++;
		if (Input.isKeyPressed(KeyEvent.VK_S) || Input.isKeyPressed(KeyEvent.VK_DOWN))  y--;
		if (Input.isKeyPressed(KeyEvent.VK_A) || Input.isKeyPressed(KeyEvent.VK_LEFT))  x++;
		if (Input.isKeyPressed(KeyEvent.VK_D) || Input.isKeyPressed(KeyEvent.VK_RIGHT)) x--;
		
		for (int i = 0; i < Input.KEYS.length; i++)
			if (Input.KEYS[i])
				System.out.println("KEY: " + i);
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		renderer.clear();
		renderer.render(x, y);
		
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