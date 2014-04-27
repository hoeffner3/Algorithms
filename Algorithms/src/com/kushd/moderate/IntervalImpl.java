package com.kushd.moderate;

import java.util.LinkedList;
import java.util.TreeSet;

public class IntervalImpl {

	public static void main(String[] args) {
		IntervalRange obj = new IntervalRange();
		obj.addInterval(3, 6);
        obj.addInterval(8, 9);
        obj.addInterval(1, 5);
        obj.addInterval(1, 3);
        obj.addInterval(1, 8);
        obj.addInterval(3, 10);
		System.out.println(obj.getTotalCoveredLength());
	}
}

class IntervalRange {
	
	private int totalRange=0;
	private TreeSet<INode> set = new TreeSet<INode>();
	
	
	public void addInterval(int from, int to){
		LinkedList<INode> toRemovelist = new LinkedList<INode>();
		for(INode node : set){
			if(node.isInLeft(from, to)){
				break;
			}else if(!(node.isInRight(from, to))){
				toRemovelist.add(node);
				from = Math.min(node.start, from);
				to = Math.max(node.end, to);
			}
		}
		for(INode item : toRemovelist){
			totalRange = totalRange - item.getRange();
			set.remove(item);
		}
		set.add(new INode(from,to));
		totalRange = totalRange + (to-from);
	}
	
	public int getTotalCoveredLength(){
		return totalRange;
	}
	
}

class INode implements Comparable<INode> {
	
	int start;
	int end;
	
	public INode(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	public boolean isInLeft(int start, int end){
		if(end < this.start){
			return true;
		}
		return false;
	}
	
	public boolean isInRight(int start, int end){
		if(this.end < start){
			return true;
		}
		return false;
	}
	
	
	public int getRange(){
		return this.end-this.start;
	}

	@Override
	public int compareTo(INode arg0) {
		if(this.start < arg0.start){
			return -1;
		}else if(this.start == arg0.start){
			if(this.end < arg0.end){
				return -1;
			}else if(this.end == arg0.end){
				return 0;
			}else{
				return 1;
			}
		}else{
			return 1;
		}
	}

	
	
}