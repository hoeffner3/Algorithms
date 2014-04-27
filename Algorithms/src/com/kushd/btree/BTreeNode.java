package com.kushd.btree;

import java.util.ArrayList;
import java.util.List;

public class BTreeNode<E extends Comparable<E>> {
	
	List<E> keys;
	int mindegree;
	List<BTreeNode<E>> childs;
	boolean isLeaf;
	
	public BTreeNode(int mindegree, boolean isLeaf) {
		this.mindegree = mindegree;
		this.isLeaf = isLeaf;
		this.keys = new ArrayList<E>();
		this.childs = new ArrayList<BTreeNode<E>>();
	}
	
	public int binarySearch(E key){
		int low = 0;
		int high = keys.size()-1;
		while(low <= high){
			int mid = low + (high-low)/2;
			if(keys.get(mid).compareTo(key) <= 0){
				if(mid+1 <keys.size() && keys.get(mid+1).compareTo(key) == 1){
					return mid;
				}else if(mid+1 == keys.size()){
					return mid;
				}else{
					low = mid+1;
				}
			}else{
				if(mid-1 >=0 && keys.get(mid-1).compareTo(key) == -1){
					return mid-1;
				}else if(mid-1 ==-1){
					return mid-1;
				}else{
					high = mid-1;
				}
			}
		}
		
		return -10; // This case should not occur
	}
	
	public void splitChild(int i, BTreeNode<E> child){
		BTreeNode<E> secchild = new BTreeNode<E>(child.mindegree, child.isLeaf);
		for(int j=0;j<mindegree-1;j++){
			secchild.keys.add(j, child.keys.remove(mindegree));
		}
		if(child.isLeaf == false){
			for(int j=0;j<mindegree;j++){
				secchild.childs.add(j, child.childs.remove(mindegree));
			}
		}
		childs.add(i+1, secchild);
		keys.add(i, child.keys.remove(mindegree-1));
	}

}
