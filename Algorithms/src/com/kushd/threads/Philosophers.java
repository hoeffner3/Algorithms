package com.kushd.threads;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philosophers{
	
	public static void main(String[] arg){
		
		Random rm = new Random();
		ChopSticks c1 = new ChopSticks();
		ChopSticks c2 = new ChopSticks();
		ChopSticks c3 = new ChopSticks();
		ChopSticks c4 = new ChopSticks();
		Philosopher p1 = new Philosopher(c1, c4,1,rm);
		Philosopher p2 = new Philosopher(c2, c1,2,rm);
		Philosopher p3 = new Philosopher(c3, c2,3,rm);
		Philosopher p4 = new Philosopher(c4, c3,4,rm);
		
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		System.out.println("main thread exited ");
	}
	
	
}


class Philosopher extends Thread {
	private int bites=10;
	private int id;
	private ChopSticks left;
	private ChopSticks right;
	private Random random;
	
	public Philosopher(ChopSticks l, ChopSticks r, int v, Random rm){
		left = l;
		right = r;
		id = v;
		random = rm;
	}
	
	public void chew(){ try {
		Thread.sleep(1 + (int) (random.nextDouble() * 500));
	} catch (InterruptedException e) {
		
		e.printStackTrace();
	}  };
	
	public boolean eat(){
		if(pickChopsticks()){
			chew();
			left.putDown();
			right.putDown();
			return true;
		}
		return false;
	}
	
	private boolean pickChopsticks() {
		if(!left.pickUp()){
			return false;
		}
		if(!right.pickUp()){
			left.putDown();
			return false;
		}
		return true;
	}

	@Override
	public void run() {
		
		int i=0;
		while(i<bites){
			
			if(eat()){
				System.out.println(id+" eat-"+i);
				i++;
			}else{
				System.out.println(id+" eat-unsuccessful");
			}
		}
	}
	

}

class ChopSticks{
	private Lock lock;
	
	public ChopSticks(){
		lock = new ReentrantLock();
	}
	
	public void putDown(){
		lock.unlock();
	}
	
	public boolean pickUp(){
		return lock.tryLock();
	}
}
