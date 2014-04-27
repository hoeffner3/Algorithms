package com.kushd.generics;

import java.util.Iterator;

public class CircularArrayIterator<TI> implements Iterator<TI> {
	
	private int _current = -1;
	private TI[] _items;
	
	public CircularArrayIterator(TI[] array){
		_items = array;
	}

	@Override
	public boolean hasNext() {
		
		return _current < _items.length-1;
	}

	@Override
	public TI next() {
		_current++;
		return _items[_current];
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("...");
		
	}

}
