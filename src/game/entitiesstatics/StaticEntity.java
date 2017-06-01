package game.entitiesstatics;

import game.Handler;
import game.entities.Entity;

import java.awt.Graphics;

public abstract class StaticEntity extends Entity{

	protected float scale;
	public StaticEntity(Handler handler, float x, float y, int width, int height,float scale) {
		super(handler, x, y, width, height);
		this.scale=scale;
		
	}

	

}
