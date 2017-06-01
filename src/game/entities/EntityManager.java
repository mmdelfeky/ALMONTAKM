package game.entities;

import game.Handler;
import game.entities.creatures.Player;
import game.entities.creatures.Player2;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class EntityManager implements Serializable{

	private Handler handler;
	private Player player;
	private Player2 player2;
	
	private ArrayList<Entity> entities;
	private Comparator<Entity> renderSort = new Comparator<Entity>() {

		@Override
		public int compare(Entity a, Entity b) {

			if (a.getY() + a.getHeight() < b.getY() + b.getHeight())
				return -1;
			else
				return 1;

		}
	};

	public EntityManager(Handler handler, Player player) {

		this.handler = handler;
		this.player = player;
		player2 =new Player2(handler, 1000, 1300);
		entities = new ArrayList<>();
		entities.add(player);
		entities.add(player2);
	}

	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			
			if (!e.vis)
				entities.remove(i);
			else
				e.tick();
		}

	}

	public void render(Graphics g) {

		for (Entity e : entities) {
			if(e.isVisible())
			e.render(g);
		}
		entities.sort(renderSort);

	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	// setters and getters
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	public void setPlayer2(Player2 player2,int x,int y) {
		this.player2 = player2;
		player2.setX(x);
		player2.setX(y);
		
	}
	public Player2 getPlayer2() {
		return player2;
	}

	public ArrayList<Entity> getEnitites() {
		return entities;
	}

	public void setEnitites(ArrayList<Entity> enitites) {
		this.entities = enitites;
	}
	

}
