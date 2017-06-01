package game.states;

import game.Handler;

import java.awt.Graphics;

public abstract class State {

	private static State currentState = null;
	boolean first = true;
	protected int delay = 6;
	protected int delayCounter = 0;

	public static void setState(State state) {
		currentState = state;
	}

	public static State getState() {
		return currentState;
	}

	// CLASS

	protected Handler handler;

	public State(Handler handler) {
		this.handler = handler;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

}
