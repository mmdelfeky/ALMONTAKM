package game.entities.creatures;

import game.Handler;
import game.gfx.Animation;
import game.gfx.Assets;
import game.sounds.SoundLoader;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player2 extends Creature {

	// Animations
	private Animation[] anim;// animDown, animUp, animLeft, animRight;
	private final int changeSpeed = 80;
	private float shotX, shotY;
	private int shotCounter = 0;
	private final int SHOT_DELAY = 8;
	private boolean moving;
	private  int last = 0;
	private int Id;
	private boolean firing;

	public Player2(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH,
				Creature.DEFAULT_CREATURE_HEIGHT);

		bounds.x = 45;
		bounds.y = 80;
		bounds.width = 40;
		bounds.height = 35;
		moving = false;
		firing =false;
		// Animatons
		anim = new Animation[4];
		anim[0] = new Animation(changeSpeed, Assets.player_down);
		anim[1] = new Animation(changeSpeed, Assets.player_up);
		anim[2] = new Animation(changeSpeed, Assets.player_left);
		anim[3] = new Animation(changeSpeed, Assets.player_right);
	}

	@Override
	public void tick() {
		// Animations
		
			anim[last].tick();

		// Movement
		getInput();
		move();
		

//		handler.getGameCamera().centerOnEntity(this);
	}

	private void getInput() {
		
		if (xMove == 0 && yMove == 0){
			
		
			moving = false;
		}
		else {
			moving = true;
		}
		if(firing)
			fire();

	}

	@Override
	public void render(Graphics g) {

		drawPlayer(g);

	}

	private Animation getCurrentAnimationFrame() {
		if (xMove < 0) {

			last = 2;
		} else if (xMove > 0) {
			last = 3;
		} else if (yMove < 0) {
			last = 1;
		} else if (yMove > 0) {
			last = 0;
		}

		return anim[last];

	}

	private void fire() {

		if (shotCounter == 0) {

			initShot();
			Shot s = new Shot(handler, shotX, shotY, xMove, yMove,Id);
			handler.getWorld().getEntityManager().addEntity(s);
			SoundLoader.SHOT.play();
		}
		shotCounter++;
		if (shotCounter > SHOT_DELAY)
			shotCounter = 0;

	}

	public  int getLastAnim() {
		return last;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) (x + bounds.x), (int) (y + 25),
				bounds.width, bounds.height + 25);
	}

	private void initShot() {
		if (xMove != 0 && yMove != 0) {
			if (xMove > 0 && yMove > 0) {
				shotX = (int) (x + bounds.x + bounds.width + 20);
				shotY = (int) (y + bounds.y);
			} else if (xMove > 0 && yMove < 0) {
				shotX = (int) (x + bounds.x + bounds.width + 20);
				shotY = (int) (y + bounds.y);
			} else if (xMove < 0 && yMove > 0) {
				shotX = (int) x + bounds.x - 20;
				shotY = (int) (y + bounds.y);
			} else {
				shotX = (int) x + bounds.x - 20;
				shotY = (int) (y + bounds.y);
			}

		} else {
			if (last == 2) {
				shotX = (int) x + bounds.x - 20;
				shotY = (int) y + bounds.y + bounds.height / 2 - 8;
			} else if (last == 3) {
				shotX = (int) (x + bounds.x + bounds.width + 20);
				shotY = (int) y + bounds.y + bounds.height / 2 - 8;
			} else if (last == 0) {

				shotY = (int) (y + bounds.y) + 40;
				shotX = (int) x + bounds.x + 5;
			} else {
				shotY = (int) (y + bounds.y/2);
				shotX = (int) x + bounds.x + bounds.width - 5;
			}
		}
	}

	private void drawPlayer(Graphics g) {
		if (moving) {
			g.drawImage(getCurrentAnimationFrame().getCurrentFrame(),
					(int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()), width,
					height, null);
		} else {
			g.drawImage(getCurrentAnimationFrame().getFirstAnim(),
					(int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()), width,
					height, null);

		}

	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
	
	public boolean isFiring() {
		return firing;
	}

	public void setFiring(boolean firing) {
		this.firing = firing;
	}

}
