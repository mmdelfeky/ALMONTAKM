package game.entitiesstatics;

import game.Handler;
import game.gfx.Assets;

import java.awt.Graphics;

public class Tree extends StaticEntity{
	
	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, 62*3, 82*3,3.5f);
		bounds.x=(int)(20*scale);
		bounds.y=(int)(62*scale);
		bounds.width = (int)(12*scale);
		bounds.height = (int)(8*scale) ;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) (x - handler.getGameCamera()
				.getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width,
				height, null);

	}
}


