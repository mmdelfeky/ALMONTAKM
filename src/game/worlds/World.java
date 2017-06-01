package game.worlds;

import game.Handler;
import game.entities.EntityManager;
import game.entities.creatures.Player;
import game.entities.creatures.Snail;
import game.entitiesstatics.Home;
import game.entitiesstatics.Tree;
import game.tiles.Tile;
import game.utils.Utils;

import java.awt.Graphics;
import java.util.Random;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private EntityManager entityManager;

	public World(Handler handler, String path) {
		this.handler = handler;

		entityManager = new EntityManager(handler, new Player(handler, 1000,
				1000));
		loadWorld(path);
		entityManager.getPlayer().setX(spawnX / 2);
		entityManager.getPlayer().setY(spawnY);
		// entityManager.addEntity(new Player2(handler, Client.x, Client.y));
		// homes
		entityManager.addEntity(new Home(handler, 1950, 900));
		//entityManager.addEntity(new Home2(handler, 800, 800));
		entityManager.addEntity(new Home(handler, 600, 1600));

		// snail
		entityManager.addEntity(new Snail(handler, 1800, 1200, 60, 60));
		entityManager.addEntity(new Snail(handler, 240, 1000, 60, 60));
		entityManager.addEntity(new Snail(handler, 200, 1500, 60, 60));
		entityManager.addEntity(new Snail(handler, 360, 800, 60, 60));


		entityManager.addEntity(new Snail(handler, 500, 1000, 60, 60));
		entityManager.addEntity(new Snail(handler, 620, 1500, 60, 60));
		entityManager.addEntity(new Snail(handler, 1900, 1200, 60, 60));
		
		// home trees
		// UP
		entityManager.addEntity(new Tree(handler, 300, 1300));
		entityManager.addEntity(new Tree(handler, 600, 1300));
		entityManager.addEntity(new Tree(handler, 900, 1300));
		// LEFT
		entityManager.addEntity(new Tree(handler, 300, 1600));

		entityManager.addEntity(new Tree(handler, 300, 1900));
		// RIGHT
		entityManager.addEntity(new Tree(handler, 900, 1600));
		
		entityManager.addEntity(new Tree(handler, 900, 1900));
		// DOWN
		entityManager.addEntity(new Tree(handler, 300, 1900));
		entityManager.addEntity(new Tree(handler, 600, 1900));

		// wood trees
		// DOWN
		entityManager.addEntity(new Tree(handler, 1600, 2000));
		
		entityManager.addEntity(new Tree(handler, 2200, 2000));
		// LEFT
		entityManager.addEntity(new Tree(handler, 1600, 1800));
		entityManager.addEntity(new Tree(handler, 1600, 1600));
		// UP
		
		entityManager.addEntity(new Tree(handler, 2200, 1550));
		// right
		entityManager.addEntity(new Tree(handler, 2300, 1800));
		
	}

	public void tick() {

		entityManager.tick();
	}

	public void render(Graphics g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset()
				/ Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width,
				(handler.getGameCamera().getxOffset() + handler.getWidth())
						/ Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset()
				/ Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height,
				(handler.getGameCamera().getyOffset() + handler.getHeight())
						/ Tile.TILEHEIGHT + 1);

		for (int y = yStart; y < yEnd; y++) {

			for (int x = xStart; x < xEnd; x++) {

				getTile(x, y).tick();
				getTile(x, y).render(
						g,
						(int) (x * Tile.TILEWIDTH - handler.getGameCamera()
								.getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera()
								.getyOffset()));

			}
			entityManager.render(g);
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;

		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null)
			return Tile.waterTile;
		return t;
	}

	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
	    file = Utils.loadFileAsString("res/positions/player2.txt");
	   
		String[] player = file.split("\\s+");
		int pos=new Random().nextInt(10);
		String [] xyPos=player[pos].split("/");
		
		spawnX = Integer.parseInt(xyPos[0]);
		spawnY = Integer.parseInt(xyPos[1]);

		tiles = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}
