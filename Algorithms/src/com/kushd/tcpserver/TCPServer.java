package com.kushd.tcpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {

	public static void main(String[] args) {
		new Thread(new TCPServerImpl(9000)).start();
	}
	
}


class TCPServerImpl implements Runnable {
	
	protected int serverPort = 8080;
	protected ServerSocket socket = null;
	protected boolean isStopped = false;
	protected Thread runningThread = null;
	protected ExecutorService threadPool = Executors.newFixedThreadPool(10);
	
	public TCPServerImpl(int serverPort) {
		this.serverPort = serverPort;
	}
	

	@Override
	public void run() {
		synchronized (this) {
			this.runningThread = Thread.currentThread();
		}
		openServerSocket();
		while(!isStopped()){
			Socket clientSocket = null;
            try {
                clientSocket = this.socket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                    System.out.println("Server Stopped.") ;
                    return;
                }
                throw new RuntimeException(
                    "Error accepting client connection", e);
            }
            this.threadPool.execute(
                new WorkerRunnable(clientSocket,
                    "Thread Pooled Server"));
		}
		threadPool.shutdown();
		System.out.println("Server Stopped");
		
	}
	
	private synchronized boolean isStopped(){
		return this.isStopped;
	}
	
	public synchronized void stop(){
		this.isStopped = true;
		try {
            this.socket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
	}
	
	private void openServerSocket(){
		try {
			this.socket = new ServerSocket(serverPort);
		} catch (IOException e) {
			throw new RuntimeException("Cannot open port",e);
		}
	}
	
	
	
}
