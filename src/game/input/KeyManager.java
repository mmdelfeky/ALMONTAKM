package game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	
	private boolean[] keys;
	public boolean up, down, left, right,fire,enter,esc;
	public boolean up1, down1, left1, right1,fire1;
	
	public KeyManager(){
		keys = new boolean[256];
	}
	
	public void tick(){
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		fire = keys[KeyEvent.VK_SPACE];
		
		enter = keys[KeyEvent.VK_ENTER];
		esc =keys[KeyEvent.VK_ESCAPE];
		
		up1 = keys[KeyEvent.VK_UP];
		down1 = keys[KeyEvent.VK_DOWN];
		left1 = keys[KeyEvent.VK_LEFT];
		right1 = keys[KeyEvent.VK_RIGHT];
		fire1 = keys[KeyEvent.VK_NUMPAD0];
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	

}
