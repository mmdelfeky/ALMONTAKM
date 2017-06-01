package game;

import game.display.Display;
import game.gfx.Assets;
import game.gfx.GameCamera;
import game.input.KeyManager;
import game.input.MouseManager;
import game.input.WindowHandler;
import game.sounds.SoundLoader;
import game.states.GameState;
import game.states.MenuState;
import game.states.State;

import java.awt.Graphics;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.io.Serializable;

public class Game implements Runnable,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Display display;
	private int width, height;
	public String title;

	private boolean running = false;
	private Thread thread;

	private BufferStrategy bs;
	private Graphics g;

	// States
	private State gameState;
	private State menuState;
	// menu
	boolean menu = true;
	// Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	private WindowHandler windowHandler;

	// Camera
	private GameCamera gameCamera;

	// Handler
	private Handler handler;

	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		windowHandler = new WindowHandler();
		//startSound
		
	}

	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		display.getFrame().addWindowListener(windowHandler);
		Assets.init();

		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);

		try{gameState = new GameState(handler);}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		menuState = new MenuState(handler);
		State.setState(menuState);
	}

	private void tick() {
		keyManager.tick();
		if (isMenu()) {
			State.setState(menuState);
		} else {
			State.setState(gameState);
		}
		if (State.getState() != null)
			State.getState().tick();
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// Clear Screen
		g.clearRect(0, 0, display.getFrame().getWidth(), display.getFrame()
				.getWidth());
		// Draw Here!

		if (State.getState() != null)
			State.getState().render(g);

		// End Drawing!
		bs.show();
		g.dispose();
	}

	public void run() {

		init();

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;

			if (delta >= 1) {
				render();
				tick();
				delta--;
			}

		}

		stop();

	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Display getDisplay() {
		return display;
	}

	public boolean isMenu() {
		return menu;
	}

	public void setMenu(boolean menu) {
		this.menu = menu;
	}

}
