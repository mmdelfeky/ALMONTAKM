package game.entities.creatures;

import game.Handler;
import game.entities.Entity;
import game.tiles.Tile;

public abstract class Creature extends Entity {

	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 5.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 150,
			DEFAULT_CREATURE_HEIGHT = 117;
	public static int Score = 0;

	protected int health;
	protected float speed;
	protected float xMove, yMove;

	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}

	public void move() {
		if (!checkEntityCollesion(xMove, 0f))
			moveX();

		if (!checkEntityCollesion(0f, yMove))
			moveY();
	}

	public void moveX() {
		if (xMove > 0) {// Moving right
			int tx = (int) (x + xMove + bounds.x + bounds.width)
					/ Tile.TILEWIDTH;

			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx,
							(int) (y + bounds.y + bounds.height)
									/ Tile.TILEHEIGHT)) {
				x += xMove;
			} else {
				if (this instanceof Snail) {
					xMove = -xMove;
					((Snail) this).setInd(3);
					x += xMove;
				} else
					x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
			}

		} else if (xMove < 0) {// Moving left
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx,
							(int) (y + bounds.y + bounds.height)
									/ Tile.TILEHEIGHT)) {
				x += xMove;
			} else {
				if (this instanceof Snail) {
					xMove = -xMove;
					((Snail) this).setInd(1);
					x += xMove;
				} else
					x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
			}

		}
	}

	public void moveY() {
		if (yMove < 0) {// Up
			int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width)
							/ Tile.TILEWIDTH, ty)) {
				y += yMove;
			} else {
				if (this instanceof Snail) {
					yMove = -yMove;
					((Snail) this).setInd(0);
					y += yMove;
				} else
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
			}

		} else if (yMove > 0) {// Down
			int ty = (int) (y + yMove + bounds.y + bounds.height)
					/ Tile.TILEHEIGHT;

			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width)
							/ Tile.TILEWIDTH, ty)) {
				y += yMove;
			} else {
				if (this instanceof Snail) {
					yMove = -yMove;
					((Snail) this).setInd(2);
					y += yMove;
				} else
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
			}

		}
	}

	// GETTERS SETTERS

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
