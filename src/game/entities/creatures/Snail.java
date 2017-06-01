package game.entities.creatures;

import game.Handler;
import game.gfx.Animation;
import game.gfx.Assets;

import java.awt.Graphics;
import java.util.Random;

public class Snail extends Creature {

	private Animation[] anim;// animDown, animUp, animLeft, animRight;
	private final int changeSpeed = 80;
	private int ind;
	private int moveCounter;

	private int[] xMoves, yMoves;

	public Snail(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);

		moveCounter = 300;

		
		bounds.x = 18;
		bounds.y = 17;
		bounds.width = 33;
		bounds.height = 46;

		xMoves = new int[2];
		yMoves = new int[2];
		xMoves[0] = yMoves[0] = 1;
		xMoves[1] = yMoves[1] = -1;

		generateRandomPos();
		anim = new Animation[4];
		anim[0] = new Animation(changeSpeed, Assets.snail_down);
		anim[1] = new Animation(changeSpeed, Assets.snail_right);
		anim[2] = new Animation(changeSpeed, Assets.snail_up);
		anim[3] = new Animation(changeSpeed, Assets.snail_left);

	}

	@Override
	public void tick() {
		anim[ind].tick();
		move();

		generateRandomPos();
	}

	@Override
	public void render(Graphics g) {
		// ind = new Random().nextInt(3);
		for (int i = 0; i < 4; i++)
			if (ind == i) {
				g.drawImage(anim[i].getCurrentFrame(), (int) (x - handler
						.getGameCamera().getxOffset()), (int) (y - handler
						.getGameCamera().getyOffset()), width, height, null);
			}

	}

	/*
	 * private Animation getCurrentAnimationFrame() { if (xMove < 0) {
	 * 
	 * last = 2; } else if (xMove > 0) {
	 * 
	 * last = 3; } else if (yMove < 0) { last = 1; } else if (yMove > 0) { last
	 * = 0; }
	 * 
	 * return anim[last];
	 * 
	 * }
	 */
	private void generateRandomPos() {
		if (moveCounter <= 150) {
			moveCounter++;

		} else {
			int rnd = new Random().nextInt(2);
			if (rnd == 1) {
				rnd = new Random().nextInt(2);
				xMove = xMoves[rnd];
				if (rnd == 0) {
					ind = 1;
				} else {
					ind = 3;
				}
				yMove = 0;
			} else {
				rnd = new Random().nextInt(2);
				yMove = yMoves[rnd];
				if (rnd == 0) {
					ind = 0;
				} else {
					ind = 2;
				}
				xMove = 0;
			}
			moveCounter = 0;
		}
	}

	public void setInd(int ind) {
		this.ind = ind;
	}
	
}
