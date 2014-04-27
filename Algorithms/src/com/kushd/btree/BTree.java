package com.kushd.btree;

public class BTree {

	
	public static void main(String[] args) {
		BTreeImpl<Integer> btree = new BTreeImpl<Integer>(3);
		btree.insert(10);
		btree.insert(20);
		btree.insert(5);
		btree.insert(6);
		btree.insert(12);
		btree.insert(30);
		btree.insert(7);
		btree.insert(17);
		btree.traverse();
	}
}


class BTreeImpl<E extends Comparable<E>> {
	
	
	private BTreeNode<E> root;
	private int mindegree;
	
	public BTreeImpl(int mindegree) {
		this.mindegree = mindegree;
	}
	
	public void insert(E key){
		if(null == root){
			root = new BTreeNode<E>(mindegree, true);
			root.keys.add(key);
			return;
		}
		if(root.keys.size() == (2*mindegree-1)){
			BTreeNode<E> newroot = new BTreeNode<E>(mindegree, false);
			newroot.childs.add(0, root);
			root = newroot;
			root.splitChild(0, root.childs.get(0));
			int i=0;
			if(root.keys.get(0).compareTo(key) <= 0){
				i++;
			}
			insertNotFull(root.childs.get(i),key);
		}else{
			insertNotFull(root, key);
		}
	}
	
	private void insertNotFull(BTreeNode<E> node, E key) {
		if(node.isLeaf){
			int index = node.binarySearch(key);
			node.keys.add(index+1, key);
			return;
		}
		int index = node.binarySearch(key);
		int i = index+1;
		if((node.childs.get(index+1)).keys.size() == (2*mindegree-1)){
			node.splitChild(index+1, node.childs.get(index+1));
			if(node.keys.get(i).compareTo(key) <= 0){
				i++;
			}
		}
		insertNotFull(node.childs.get(i), key);
	}

	public BTreeNode<E> search(E key){
		return root == null?null:searchHelper(root, key);
	}
	
	
	
	private BTreeNode<E> searchHelper(BTreeNode<E> node, E key) {
		int index = node.binarySearch(key);
		if(index>=0 && node.keys.get(index).compareTo(key)==0){
			return node;
		}
		if(node.isLeaf == true){
			return null;
		}
		return node.childs.get(index+1);
	}

	public void traverse(){
		if(root != null){
			traverseHelper(root,0);
		}
	}
	
	private void traverseHelper(BTreeNode<E> node,int level){
		for(int i=0;i<node.keys.size();i++){
			if(node.isLeaf == false){
				traverseHelper(node.childs.get(i),level+1);
			}
			System.out.println(node.keys.get(i) + " level-" + level);
		}
		if(node.isLeaf == false){
			traverseHelper(node.childs.get(node.keys.size()),level+1);
		}
	}
	
}
