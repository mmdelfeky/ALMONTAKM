package game.network;

import game.entities.creatures.Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;



public class Connection {

	protected DatagramSocket socket;//TO DEAL WITH UDP SCOKET
	protected DatagramPacket sendPacket;//PACKET FORM OF UDP
	protected DatagramPacket receivePacket; 
	//FRIST PARAM IS THE THING YOU WILL SEND
    //SECOND PARAM THE DESTINATION IP
  	public void send(String str, InetAddress ip, int port) {

	
				byte[] data = str.getBytes();
				sendPacket = new DatagramPacket(data, data.length, ip, port);
				try {
					socket.send(sendPacket);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		
		}



	public String receive() {
		String str = null;
try{
		byte[] data = new byte[30];
		DatagramPacket receivePacket = new DatagramPacket(data, data.length);
		socket.receive(receivePacket);
		str = new String(receivePacket.getData());
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}

		return str;
	}
}
