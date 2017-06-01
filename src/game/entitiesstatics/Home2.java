package game.entitiesstatics;

import game.Handler;
import game.gfx.Assets;

import java.awt.Graphics;

public class Home2 extends StaticEntity {

	public Home2(Handler handler, float x, float y) {
		super(handler, x, y, (int)(145 * 3.5), (int)(175 * 3.5),3.5f);
		// TODO Auto-generated constructor stub
		bounds.x=10;
		bounds.y=100;
		bounds.width = (int)(145 * 3);
		bounds.height =(int)( 175 * 2)  ;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.home2, (int) (x - handler.getGameCamera()
				.getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width,
				height, null);

	}
}
