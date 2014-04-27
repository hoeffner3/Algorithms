package com.kushd.generics;

import java.util.Iterator;

public class CircularArray<T> implements Iterable<T> {
	
	private T[] items;
	private int head=0;
	
	public CircularArray(int size){
		items = (T[]) new Object[size];
	}
	
	private int convert(int index){
		if(index<0){
			index += items.length;
		}
		
		return (head+index)%items.length;
	}
	
	public void rotate(int shiftright){
		head = convert(shiftright);
	}
	
	public T get(int i){
		if(i<0 || i>= items.length){
			throw new java.lang.IndexOutOfBoundsException("....");
		}
		return items[convert(i)];
	}
	
	public void set(int i, T item){
		items[convert(i)] = item;
	}

	@Override
	public Iterator<T> iterator() {
		
		return (new CircularArrayIterator<T>(this.items));
	}

}
