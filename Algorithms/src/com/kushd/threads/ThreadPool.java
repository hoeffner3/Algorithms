package com.kushd.threads;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
	
	private static ThreadPool instance = null;
	private BlockingQueue<Runnable> taskQueue = null;
	private List<PoolThread> threads;
	private static final int MAX_TASK = 100;
	private static final int MAX_THREADS = 10;
	private boolean isStopped = false;
	
	private ThreadPool() {
		taskQueue = new BlockingQueue<Runnable>(MAX_TASK);
		threads = new ArrayList<PoolThread>(MAX_THREADS);
		for(int i=0;i<MAX_THREADS;i++){
			threads.add(new PoolThread(taskQueue));
		}
		for(PoolThread pthread : threads){
			pthread.start();
		}
	};
	
	public static synchronized ThreadPool getInstance(){
		if(null == instance){
			instance = new ThreadPool();
		}
		return instance;
	}
	
	public synchronized void execute(Runnable runnable) throws InterruptedException{
		if(isStopped){
			throw new IllegalStateException("Thread Pool has been stopped");
		}
		
		taskQueue.enqueue(runnable);
	}
	
	public synchronized void stop(){
		isStopped = true;
		for(PoolThread pthread : threads){
			pthread.stopThread();
		}
	}
	

}


class PoolThread extends Thread {
	
	private BlockingQueue<Runnable> taskQueue=null;
	private boolean isStopped = false;
	
	public PoolThread(BlockingQueue<Runnable> taskQueue) {
		this.taskQueue = taskQueue;
	}

	@Override
	public void run() {
		while(!isStopped){
			try{
				Runnable runnable = taskQueue.dequeue();
				runnable.run();
			}catch(InterruptedException e){
				System.out.println("Pool Thread -" + this.getId() + " interrupted");
			}
		}
		
	}
	
	public synchronized void stopThread(){
		isStopped = true;
		this.interrupt();
	}
	
	
}



