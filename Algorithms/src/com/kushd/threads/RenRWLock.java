package com.kushd.threads;

import java.util.HashMap;
import java.util.Map;

public class RenRWLock {
	
	private Map<Thread, Integer> readingThreads = new HashMap<Thread,Integer>();
	private int writers = 0;
	private int writeRequests = 0;
	private Thread writingThread = null;
	
	public synchronized void lockRead() throws InterruptedException {
		
		Thread curThread =  Thread.currentThread();
		while(!canReadAccess(curThread)){
			wait();
		}
		readingThreads.put(curThread, getReadAccessCount(curThread)+1);
	}
	
	private int getReadAccessCount(Thread curThread){
		if(readingThreads.containsKey(curThread)){
			return readingThreads.get(curThread);
		}
		return 0;
	}
	
	private boolean canReadAccess(Thread curThread){
		if(isWriter(curThread)){
			return true;
		}
		if(hasWriter()){
			return false;
		}
		if(isReader(curThread)){
			return true;
		}
		if(hasWriteRequests()){
			return false;
		}
		return true;
	}
	
	public synchronized void unlockRead(){
		Thread curThread = Thread.currentThread();
		if(!isReader(curThread)){
			throw new IllegalMonitorStateException("Calling thred does not hold a read lock");
		}
		int accessCnt = readingThreads.get(curThread);
		if(accessCnt==1){
			readingThreads.remove(curThread);
		}else{
			readingThreads.put(curThread, accessCnt-1);
		}
		notifyAll();
	}
	
	public synchronized void lockWrite() throws InterruptedException {
		writeRequests++;
		Thread curThread = Thread.currentThread();
		while(!canWriteAccess(curThread)){
			wait();
		}
		writeRequests--;
		writers++;
		writingThread = curThread;
	}
	
	private boolean canWriteAccess(Thread curThread){
		if(isOnlyReader(curThread)){
			return true;
		}
		if(hasReaders()){
			return false;
		}
		if(writingThread == null){
			return true;
		}
		if(!isWriter(curThread)){
			return false;
		}
		return true;
	}
	
	public synchronized void unlockWrite(){
		if(!isWriter(Thread.currentThread())){
			throw new IllegalMonitorStateException("Current thread does not hold the write lock");
		}
		writers--;
		if(writers==0){
			writingThread=null;
		}
		notifyAll();
	}
	
	private boolean isWriter(Thread curThread){
		return writingThread == curThread;
	}
	
	private boolean hasWriter(){
		return writingThread != null;
	}
	
	private boolean hasWriteRequests(){
		return this.writeRequests>0;
	}
	
	private boolean isReader(Thread curThread){
		return readingThreads.get(curThread) != null;
	}
	
	private boolean hasReaders(){
		return readingThreads.size()>0;
	}
	
	private boolean isOnlyReader(Thread curThread){
		return readingThreads.size() == 1 && readingThreads.get(curThread) != null;
	}

}
