package game.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


public class Server extends Connection {
	private int port;
	private InetAddress ip;

	private ClientInfo clientInfo;
	private List<ClientInfo> clients;

	public Server(InetAddress ip, int port) {
		try {
			clients = new ArrayList<>();
			this.port = port;
			this.ip = ip;
			socket = new DatagramSocket(port);

		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	private void receiveS() throws IOException {

		byte[] data = new byte[30];
		DatagramPacket packet = new DatagramPacket(data, data.length);
		socket.receive(packet);
		String message = new String(packet.getData());
		if (message.startsWith("/c/")) {
			
			addClient(packet);
		} else if(message.startsWith("ID")){
			message = message.substring(3);
			sendToOther(message);
		}
		else if(message.startsWith("dis")){
			int id = Integer.parseInt(message.split("/")[1]);
			for(int i = 0; i < clients.size(); i++){
				if(clients.get(i).getID() == id)
					clients.remove(i);
			}
		}
		
		if(clients.size() == 1){
			String newmsg = "-500" + "/" + "-500" + "/" + "0" + "/" + "0" + "/"+ "false" +"/" + "true" + "/";
			send(newmsg,clients.get(0).getIp(),clients.get(0).getPort());
		}
	}

	

	private void sendToOther(String msg) {
		String[] s = msg.split("/");

		int ID;
		ID = Integer.parseInt(s[0]);

		String newmsg = "" + s[1] + "/" + s[2] + "/" + s[3] + "/" + s[4] + "/"+s[5]+"/" + s[6] + "/";

		for (int i = 0; i < clients.size(); i++) {
			if ((clients.get(i).getID() != ID))
				send(newmsg, clients.get(i).getIp(), clients.get(i).getPort());
		}

	}

	public void addClient(DatagramPacket receivePacket) {

		int ID = UniqueId.getid();
		clientInfo = new ClientInfo(receivePacket.getAddress(),
				receivePacket.getPort(), ID);
		clients.add(clientInfo);
		send("" + ID, receivePacket.getAddress(), receivePacket.getPort());
		
		System.out.println("Server sended ID :: " + ID);
		System.out.println("IP ::" + receivePacket.getAddress());

	}

	public static void main(String[] args) {

		InetAddress ip = null;
		try {
			ip = InetAddress.getByName("localhost");
		} catch (UnknownHostException e1) {
			
			e1.printStackTrace();
		}
		Server server = new Server(ip, 8888);
			while (true) {
					try {
						server.receiveS();
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
		
	}
}