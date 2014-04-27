package com.kushd.tcpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WorkerRunnable implements Runnable {
	
	protected Socket clientSocket;
	protected String serverMessage;
	protected int MAX_CONSUMER = 3;
	protected ExecutorService consumerPool = Executors.newFixedThreadPool(MAX_CONSUMER);
	
	public WorkerRunnable(Socket clientSocket, String serverMessage) {
		this.clientSocket = clientSocket;
		this.serverMessage = serverMessage;
	}

	@Override
	public void run() {
		try{
			InputStream input = clientSocket.getInputStream();
			
			List<Future<?>> futurelist = new ArrayList<Future<?>>();
			for(int i=0;i<MAX_CONSUMER;i++){
				Future<?> future = consumerPool.submit(new ConsumerCallable(i));
				futurelist.add(future);
			}
			
			for(Future<?> future : futurelist){
				try {
					future.get();
				} catch (Exception e) {
					System.out.println("There was a error retreiving the future object");
				}
			}
			
			OutputStream output = clientSocket.getOutputStream();
            long time = System.currentTimeMillis();
            output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " +
                    this.serverMessage + " - " +
                    time +
                    "").getBytes());
            output.close();
            input.close();
            System.out.println("Request processed: " + time);
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
