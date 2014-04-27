package com.kushd.simple;

import java.util.List;


public class BNode {
	
	public int value;
	public List<BNode> childlist;
	
	public BNode(int v){
		value = v;
	}
	
	public void setNodeChild(List<BNode> clist){
		childlist = clist;
	}
	
	public List<BNode> getChildren(){
		return childlist;
	}

}
