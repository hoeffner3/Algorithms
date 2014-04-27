package com.kushd.threads;

import java.util.LinkedList;

public class BlockingQueue<E> {
	
	private LinkedList<E> queue = new LinkedList<E>();
	private int limit;
	
	public BlockingQueue(int limit) {
		this.limit = limit;
	}
	
	public synchronized void enqueue(E entry) throws InterruptedException{
		while(queue.size() == limit){
			wait();
		}
		if(queue.size() == 0){
			notifyAll();
		}
		queue.add(entry);
	}
	
	public synchronized E dequeue() throws InterruptedException{
		while(queue.size() == 0){
			wait();
		}
		if(queue.size() == limit){
			notifyAll();
		}
		return queue.poll();
	}
	

}
