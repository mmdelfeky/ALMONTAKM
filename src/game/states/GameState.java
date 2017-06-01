package game.states;

import game.Handler;
import game.entities.creatures.Player;
import game.entities.creatures.Player2;
import game.network.Client;
import game.sounds.SoundLoader;
import game.worlds.World;

import java.awt.Graphics;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class GameState extends State {

	private World world;
	public static Client client;
	public static InetAddress ip;
	public static int port = 8888;

	public GameState(Handler handler) throws UnknownHostException {
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		try {
			ip = InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {

			e.printStackTrace();
		}
		client = new Client(ip, port);

		System.out.println("client started.......");
		world.getEntityManager().getPlayer().setId(client.getID());
		System.out.println("game state id :" + client.getID());
		
	}

	@Override
	public void tick() {
		if (delayCounter == delay) {
			// loadMusic();
			if (handler.getKeyManager().esc)
				handler.getGame().setMenu(true);
			delayCounter = 0;

		} else
			delayCounter++;

		world.tick();

		int x = (int) world.getEntityManager().getPlayer().getX();
		int y = (int) world.getEntityManager().getPlayer().getY();
		int xMove = (int) world.getEntityManager().getPlayer().getxMove();
		int yMove = (int) world.getEntityManager().getPlayer().getyMove();
		boolean fire = world.getEntityManager().getPlayer().isFiring();
		boolean vis = world.getEntityManager().getPlayer2().isVisible();
		// System.out.println("fire= "+fire);

		client.sendC(x, y, xMove, yMove, fire + "", vis + "");

		client.receiveC();
		if (!client.isVis())
			world.getEntityManager().getPlayer().setVis(client.isVis());
		world.getEntityManager().getPlayer2().setY(client.getY());
		world.getEntityManager().getPlayer2().setX(client.getX());
		world.getEntityManager().getPlayer2().setxMove(client.getxMove());
		world.getEntityManager().getPlayer2().setyMove(client.getyMove());
		world.getEntityManager().getPlayer2().setFiring(client.getFire());

		

	}

	@Override
	public void render(Graphics g) {

		world.render(g);

	}

	private void loadMusic() {
		if (!handler.getGame().isMenu()) {

			// if(first == false)
			// {
			SoundLoader.GAME.loop();
			// first =true;
			// }
			// else first =false;
		}

	}
}
