package com.kushd.tcpserver;

import java.util.concurrent.Callable;

public class ConsumerCallable<E> implements Callable<E> {

	private int id;
	
	public ConsumerCallable(int id) {
		this.id = id;
	}
	
	@Override
	public E call() throws Exception {
		
		System.out.println("Consumer Thread "+id+" starts");
		
		try{
			Thread.sleep((long)5000);
		}catch(InterruptedException e){};
		
		System.out.println("Consumer Thread "+id+" ends");
		
		return null;
	}

}
