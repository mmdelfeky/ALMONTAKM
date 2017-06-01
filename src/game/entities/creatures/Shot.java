package game.entities.creatures;

import game.Handler;
import game.tiles.GrassTile;
import game.tiles.RockTile;
import game.tiles.StoneGrass;
import game.tiles.Tile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;




public class Shot extends Creature {

	private final static int RADIUS = 10;

	private int id;
	public Shot(Handler handler, float x, float y, float xDirection,
			float yDirection,int id) {
		super(handler, x, y, RADIUS, RADIUS);
		speed = 15;
		this.x = x;
		this.y = y;
		this.id=id;
		this.xMove = xDirection / 5 * speed;
		this.yMove = yDirection / 5 * speed;
		this.handler = handler;
		bounds.x = (int) x;
		bounds.y = (int) y;
		bounds.width = (int) RADIUS;
		bounds.height = (int) RADIUS;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval((int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), RADIUS,
				RADIUS);
	}

	@Override
	public void tick() {

		if (xMove != 0 && yMove != 0) {
			move();

		} else {
			if (xMove != 0)
				move();
			else if (yMove != 0)
				move();
			else {
			boolean flag=(handler.getWorld().getEntityManager().getPlayer().getId()==id);
			if(flag){
				if (handler.getWorld().getEntityManager().getPlayer().getLastAnim() == 0)
					yMove = speed;
				else if (handler.getWorld().getEntityManager().getPlayer().getLastAnim() == 1)
					yMove = -1 * speed;
				else if (handler.getWorld().getEntityManager().getPlayer().getLastAnim() == 2)
					xMove = -1 * speed;
				else
					xMove = speed;
			}
			else
			{
				if (handler.getWorld().getEntityManager().getPlayer2().getLastAnim() == 0)
					yMove = speed;
				else if (handler.getWorld().getEntityManager().getPlayer2().getLastAnim() == 1)
					yMove = -1 * speed;
				else if (handler.getWorld().getEntityManager().getPlayer2().getLastAnim() == 2)
					xMove = -1 * speed;
				else
					xMove = speed;	
			}
				move();
			}
		}

		if (x < 0
				|| (int) (x - handler.getGameCamera().getxOffset()) > handler
						.getWidth()
				|| y < 0
				|| (int) (y - handler.getGameCamera().getyOffset()) > handler
						.getHeight())
			vis = false;

	}

	@Override
	public void moveX() {
		if (xMove > 0) {// Moving right
			int tx = (int) (x + xMove + bounds.width) / Tile.TILEWIDTH;

			Object b1 = handler.getWorld().getTile(tx,
					(int) y / Tile.TILEHEIGHT);
			Object b2 = handler.getWorld().getTile(tx,
					(int) (y + bounds.height) / Tile.TILEHEIGHT);
			if (!(b1 instanceof RockTile) && !(b2 instanceof RockTile)
					&& !(b1 instanceof StoneGrass)
					&& !(b2 instanceof StoneGrass)) {

				x += xMove;

			} else {

				vis = false;

			}

		} else if (xMove < 0) {// Moving left
			int tx = (int) (x + xMove) / Tile.TILEWIDTH;

			Object b1 = handler.getWorld().getTile(tx,
					(int) y / Tile.TILEHEIGHT);
			Object b2 = handler.getWorld().getTile(tx,
					(int) (y + bounds.height) / Tile.TILEHEIGHT);
			handler.getWorld().getTile(tx,
					(int) (y + bounds.height) / Tile.TILEHEIGHT);
			if (!(b1 instanceof RockTile) && !(b2 instanceof RockTile)
					&& !(b1 instanceof StoneGrass)
					&& !(b2 instanceof StoneGrass)) {

				x += xMove;
			} else {

				vis = false;
			}

		}
	}

	@Override
	public void moveY() {
		if (yMove < 0) {// Up
			int ty = (int) (y + yMove) / Tile.TILEHEIGHT;

			Object b1 = handler.getWorld()
					.getTile((int) x / Tile.TILEWIDTH, ty);
			Object b2 = handler.getWorld().getTile(
					(int) (x + bounds.width) / Tile.TILEWIDTH, ty);
			if (!(b1 instanceof RockTile) && !(b2 instanceof RockTile)
					&& !(b1 instanceof StoneGrass)
					&& !(b2 instanceof StoneGrass)) {

				y += yMove;
			} else {

				vis = false;
			}

		} else if (yMove > 0) {// Down
			int ty = (int) (y + yMove + bounds.height) / Tile.TILEHEIGHT;

			Object b1 = handler.getWorld()
					.getTile((int) x / Tile.TILEWIDTH, ty);
			Object b2 = handler.getWorld().getTile(
					(int) (x + bounds.width) / Tile.TILEWIDTH, ty);

			if (!(b1 instanceof RockTile) && !(b2 instanceof RockTile)
					&& !(b1 instanceof StoneGrass)
					&& !(b2 instanceof StoneGrass)) {

				y += yMove;
			} else {

				vis = false;
			}

		}
	}

	public int getId() {
		return id;
	}
	

}
