package com.kushd.threads;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class NonBlockingExamples {

}

class QueueExample {
	
	AtomicReference<Element> head = new AtomicReference<Element>(null);
	AtomicReference<Element> tail = head;
	
	public void put(int value){
		Element newNode = new Element(value);
		while(true){
			Element curtail = tail.get();
			Element residue = curtail == null?null:curtail.next;
			if(curtail == tail.get()){
				if(residue == null){
					if(new AtomicReference<Element>(curtail.next).compareAndSet(null, newNode)){
						tail.compareAndSet(curtail, newNode);
						return;
					}
				}else{
					tail.compareAndSet(curtail, residue);
				}
			}
		}
	}
	
}


class StackExample {
	
	AtomicReference<Element> head = new AtomicReference<Element>(null);
	
	public void push(int value){
		Element newHead = new Element(value);
		while(true){
			Element oldHead = head.get();
			newHead.next = oldHead;
			if(head.compareAndSet(oldHead, newHead)){
				return;
			}
		}
	}
	
	public int pop(){
		while(true){
			Element oldHead = head.get();
			Element newHead = oldHead == null ? null:oldHead.next;
			if(head.compareAndSet(oldHead, newHead)){
				return oldHead.value;
			}
		}
	}
	
}

class Element {
	
	final int value;
	Element next;
	
	public Element(int value) {
		this.value = value;
	}
	
}

class AIExample {
	
	public void increment(AtomicInteger i){
		while(true){
			int value = i.get();
			if(i.compareAndSet(value, value+1)){
				return;
			}
		}
	}
	
}
