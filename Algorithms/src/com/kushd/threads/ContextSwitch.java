package com.kushd.threads;

public class ContextSwitch {
	
	public static void main(String[] args) {
		Object lock = new Object();
		long endTime = 0;
		NewTask task;
		synchronized (lock) {
			task = new NewTask(lock);
			task.start();
			try{
				lock.wait();
				endTime = System.nanoTime();
				System.out.println("endTime : " + endTime);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		System.out.println("task.getStartTime : " + task.getStartTime());
		long diffTime = endTime - task.getStartTime();
		System.out.println("Context Switch Time : " + diffTime);
	}
	
}


class NewTask extends Thread {
	
	private Object lock;
	private long startTime;
	
	public NewTask(Object lock){
		this.lock = lock;
	}
	
	@Override
	public void run() {
		synchronized (lock) {
			startTime = System.nanoTime();
			System.out.println("startTime : " + startTime);
			lock.notify();
		}
	}
	
	public long getStartTime(){
		return startTime;
	}
	
}
