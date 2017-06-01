package game.tiles;

import game.gfx.Animation;
import game.gfx.Assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Waterfall extends Tile {

	private Animation anim;

	public Waterfall(int id) {
		super(Assets.waterFall[0], id);
		anim = new Animation(400, Assets.waterFall);
	}

	@Override
	public void tick() {
		anim.tick();
	}

	@Override
	public void render(Graphics g, int x, int y) {
		g.drawImage(anim.getCurrentFrame(), (int) x,
				(int) y, TILEWIDTH, TILEHEIGHT, null);
		
	}

	@Override
	public boolean isSolid() {
		return true;
	}

	

}
