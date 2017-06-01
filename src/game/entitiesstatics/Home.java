package game.entitiesstatics;

import game.Handler;
import game.gfx.Assets;

import java.awt.Graphics;

public class Home extends StaticEntity {

	public Home(Handler handler, float x, float y) {
		super(handler, x, y, (int)(75 * 3.5), (int)(90 * 3.5),3.5f);
		// TODO Auto-generated constructor stub
		/*bounds.x=(int)(5*3.5);
		bounds.y=(int)(56*3.5);
		bounds.width = (int)(60 * 3.5);
		bounds.height =(int)( 32 * 3.5)  ;*/
		
		bounds.x=(int)(5*scale);
		bounds.y=(int)(56*scale);
		bounds.width = (int)(60 * scale);
		bounds.height =(int)( 23 * scale)  ;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.home, (int) (x - handler.getGameCamera()
				.getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width,
				height, null);

	}
}
