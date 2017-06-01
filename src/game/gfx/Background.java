package game.gfx;

import game.Handler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Background {
	
private BufferedImage image;
	
	private double x;
	private double y;
	
	//private double moveScale;
	private Handler handler;
	public Background(String s,Handler handler) {
		image = ImageLoader.loadImage(s);
		this.handler= handler;
		
	}
	
	
	
	
	
	public void draw(Graphics g) {
		
		g.drawImage(image, (int)x, (int)y,handler.getGame().getWidth(),handler.getGame().getHeight(), null);
		
	}
	

}
