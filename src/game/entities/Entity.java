package game.entities;

import game.Handler;
import game.entities.creatures.Player;
import game.entities.creatures.Player2;
import game.entities.creatures.Shot;
import game.entities.creatures.Snail;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

public abstract class Entity implements Serializable {

	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean vis;

	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		bounds = new Rectangle(0, 0, width, height);
		vis = true;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isVisible() {
		return vis;
	}

	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}

	public void setVis(boolean vis) {
		this.vis = vis;
	}

	public boolean checkEntityCollesion(float xOffset, float yOffset) {
		for (Entity e : handler.getWorld().getEntityManager().getEnitites()) {

			if (e.equals(this))
				continue;

			boolean collesion;
			if (this instanceof Shot)
				collesion = e.getCollesionBounds(0f, 0f)
						.intersects(getBounds());
			else {
				collesion = e.getCollesionBounds(0f, 0f).intersects(
						getCollesionBounds(xOffset, yOffset));
			}
			if (collesion) {
				if (this instanceof Shot) {
					setVis(false);
					try {
						if (e instanceof Snail
								|| (e instanceof Player2)){
							//if(e instanceof Player2)
								
							e.setVis(false);
						}
					} catch (Exception E) {
						System.out.println(E.getMessage());
					}
				} else if (this instanceof Snail) {
					if (((Snail) this).getxMove() == 0) {

						((Snail) this).setyMove(((Snail) this).getyMove() * -1);

						if (((Snail) this).getyMove() > 0)
							((Snail) this).setInd(0);
						else if (((Snail) this).getyMove() < 0)
							((Snail) this).setInd(2);
					}

					else if (((Snail) this).getyMove() == 0) {
						((Snail) this).setxMove(((Snail) this).getxMove() * -1);

						if (((Snail) this).getxMove() > 0)
							((Snail) this).setInd(1);
						else if (((Snail) this).getxMove() < 0)
							((Snail) this).setInd(3);
					}

				}
				return true;

			}
		}
		return false;
	}

	public Rectangle getCollesionBounds(float xOffset, float yOffset) {

		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y
				+ bounds.y + yOffset), bounds.width, bounds.height);

	}

	public Rectangle getBounds() {
		return new Rectangle((int) (x), (int) (y), width, height);
	}

}
