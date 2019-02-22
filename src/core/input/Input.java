package core.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener {

	public static final boolean[] KEYS = new boolean [1024];
	
	private static int mousePositionX = -1;
	private static int mousePositionY = -1;
	private static int mouseButton = -1;
	
	public static boolean isKeyPressed(int keyCode) {
		return KEYS[keyCode];
	}
	
	public void keyPressed(KeyEvent e) {
		KEYS[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		KEYS[e.getKeyCode()] = false;
	}
	
	public void keyTyped(KeyEvent e) {
		
	}

	public static int getMouseX() {
		return mousePositionX;
	}
	
	public static int getMouseY() {
		return mousePositionY;
	}
	
	public static int getMouseButton() {
		return mouseButton;
	}
	
	public void mouseDragged(MouseEvent e) {
		mousePositionX = e.getX();
		mousePositionY = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		mouseButton = e.getButton();
	}

	public void mouseReleased(MouseEvent e) {
		mouseButton = MouseEvent.NOBUTTON;
	}

}