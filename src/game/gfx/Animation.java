package game.gfx;

import java.awt.image.BufferedImage;

public class Animation {
	
	protected int SPEED;
	public int index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	
	public Animation(int speed, BufferedImage[] frames){
		this.SPEED = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void tick(){
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > SPEED){
			index++;
			timer = 0;
			if(index >= frames.length)
				index = 0;
		}
	}
	
	public BufferedImage getCurrentFrame(){
		return frames[index];
	}
	public BufferedImage getFirstAnim()
	{
		return frames[0];
	}

}
