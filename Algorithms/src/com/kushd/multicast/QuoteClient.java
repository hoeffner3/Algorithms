package com.kushd.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class QuoteClient {
	
	public static void main(String[] args) throws IOException {
       // if (args.length != 1) {
       //      System.out.println("Usage: java QuoteClient <hostname>");
       //      return;
        //}
 
        // get a datagram socket
        //DatagramSocket socket = new DatagramSocket();
 
        // send request
        //byte[] buf = new byte[256];
        //InetAddress address = InetAddress.getByName("localhost");
        //DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        //socket.send(packet);
   
        // get response
        //packet = new DatagramPacket(buf, buf.length);
        //socket.receive(packet);
 
        // display response
        //String received = new String(packet.getData());
        //System.out.println("Quote of the Moment: " + received);
   
        //socket.close();
		
		MulticastSocket socket = new MulticastSocket(4446);
		InetAddress group = InetAddress.getByName("230.0.0.1");
		socket.joinGroup(group);
		
		DatagramPacket packet;
		for(int i=0;i<10;i++){
			byte[] buf = new byte[256];
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			
			String received = new String(packet.getData());
	        System.out.println("Quote of the Moment: " + received);
		}
		socket.leaveGroup(group);
		socket.close();
    }

}
