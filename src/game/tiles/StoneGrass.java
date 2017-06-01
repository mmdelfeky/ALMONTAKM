package game.tiles;

import game.gfx.Assets;

import java.awt.image.BufferedImage;

public class StoneGrass extends Tile{

	public StoneGrass(int id) {
		super(Assets.stoneGrass, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}

}
