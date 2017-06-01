package game.tiles;

import game.gfx.Assets;

import java.awt.image.BufferedImage;

public class Bridge extends Tile{
	public Bridge(int id) {
		super(Assets.bridge, id);
	}

	@Override
	public boolean isSolid() {
		return true;
	}
	
	

}
