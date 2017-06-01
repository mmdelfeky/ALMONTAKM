package game.network;

import java.net.InetAddress;

public class ClientInfo {
	private InetAddress ip;
	private int port;
	private int ID;
	
	public ClientInfo(InetAddress ip, int port, int ID){
		this.ip = ip;
		this.port = port;
		this.ID = ID;
	}

	public InetAddress getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}
	public int getID(){
		return ID;
	}
	
}
