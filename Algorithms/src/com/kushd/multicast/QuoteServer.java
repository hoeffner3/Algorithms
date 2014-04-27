package com.kushd.multicast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class QuoteServer {
	
	public static void main(String[] args) throws IOException {
		new QuoteServerThread().start();
	}

}


class QuoteServerThread extends Thread {
	
	protected DatagramSocket socket = null;
	protected BufferedReader in = null;
	protected boolean moreQuotes = true;
	
	public QuoteServerThread() throws IOException {
		this("QuoteServer");
	}
	
	public QuoteServerThread(String name) throws IOException {
		super(name);
		socket = new DatagramSocket(4445);
		try{
			in = new BufferedReader(new FileReader("oneliners.txt"));
		}catch(Exception e){
			System.out.println("Could not open the file. Serving time instead");
		}
		
	}
	
	@Override
	public void run() {
		while(moreQuotes){
			try{
				byte[] buf = new byte[256];
				//DatagramPacket packet = new DatagramPacket(buf,buf.length);
				//socket.receive(packet);
				
				String dString = null;
				if(in == null){
					dString = (new Date()).toString();
				}else{
					dString = getNextQuote();
				}
				buf = dString.getBytes();
				
				//InetAddress address = packet.getAddress();
				//int port = packet.getPort();
				InetAddress group = InetAddress.getByName(
                        "230.0.0.1");
				DatagramPacket packet = new DatagramPacket(buf, buf.length,group,4446);
				socket.send(packet);
				
				try{
					sleep(1000);
				}catch(InterruptedException e){};
				
			}catch(IOException e){
				e.printStackTrace();
				moreQuotes = false;
			}
		}
		socket.close();
	}

	protected String getNextQuote() {
		String returnString=null;
		try{
			if((returnString = in.readLine()) == null){
				in.close();
				moreQuotes = false;
				returnString = "No more quotes, goodbye";
			}
		}catch(IOException e){
			returnString = "IOException occured in the server";
		}
		return returnString;
	}
	
}
