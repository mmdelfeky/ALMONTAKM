package game.network;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.xml.stream.events.StartDocument;

public class Client extends Connection {

	private InetAddress ipServer;
	private int portServer;
	public static int ID;
	private int x;
	private int y;
	private int xMove, yMove;
	private char fire;
	private char vis = 't';

	public int getxMove() {
		return xMove;
	}

	public int getyMove() {
		return yMove;
	}

	public Client(InetAddress ipServer, int portServer) {

		try {

			this.ipServer = ipServer;
			this.portServer = portServer;
			socket = new DatagramSocket();

			send("/c/", this.ipServer, this.portServer);
			String msg = receive();

			this.ID = Integer.parseInt(msg.trim());
			System.out.println("I received  Id= " + this.ID);

		} catch (Exception e1) {
		
			e1.printStackTrace();
		}

	}

	public void sendC(int x, int y, int xMove, int yMove, String fire, String vis) {
		/*new Thread() {
			public void run() {*/

				String message = ("ID/" + ID + "/" + x + "/" + y + "/" + xMove + "/"
						+ yMove + "/" + fire + "/" + vis + "/").trim();
				send(message, ipServer, portServer);

			/*}
		}.start();*/

	}

	public void receiveC() {
		/*new Thread() {
			public void run() {*/
				String message = receive();
				
				if (message != null) {
					String str[] = message.split("/");
					x = Integer.parseInt(str[0]);
					y = Integer.parseInt(str[1]);
					xMove = Integer.parseInt(str[2]);
					yMove = Integer.parseInt(str[3]);
					fire = str[4].charAt(0);
					vis = str[5].charAt(0);
					
				}

		/*	}
		}.start();*/

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getID() {
		return ID;
	}

	public boolean getFire() {
		if (fire == 't')
			return true;
		else
			return false;
	}
	
	public boolean isVis(){
		if(vis == 't'){
			return true;
		}
		else
			return false;
	}

}
