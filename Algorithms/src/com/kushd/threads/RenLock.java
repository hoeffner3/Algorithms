package com.kushd.threads;

public class RenLock {
	
	
	boolean isLocked = false;
	Thread lockedBy = null;
	int lockedCount = 0;
	
	public synchronized void lock() throws InterruptedException {
		
		Thread curThread = Thread.currentThread();
		while(isLocked && curThread != lockedBy){
			wait();
		}
		isLocked=true;
		lockedBy = curThread;
		lockedCount++;
		
	}
	
	public synchronized void unlock(){
		
		if(lockedBy == 	Thread.currentThread()){
			lockedCount--;
		}
		if(lockedCount==0){
			isLocked=false;
			notify();
		}
	}

}
