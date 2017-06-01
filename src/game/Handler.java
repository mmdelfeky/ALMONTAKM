package game;

import game.display.Display;
import game.gfx.GameCamera;
import game.input.KeyManager;
import game.input.MouseManager;
import game.worlds.World;

import java.io.Serializable;

public class Handler implements Serializable{
	
	private Game game;
	private World world;
	
	public Handler(Game game){
		this.game = game;
	}
	
	public GameCamera getGameCamera(){
		return game.getGameCamera();
	}
	
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	
	public int getWidth(){
		return game.getWidth();
	}
	
	public int getHeight(){
		return game.getHeight();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	public MouseManager getMouseManager()
	{
		return game.getMouseManager();
	}
	public Display getDisplay()
	{
		return game.getDisplay();
	}

}
