package com.kushd.moderate;

public class BSTFromPreOrder {
	
	public static void main(String[] args) {
		Integer[] arr = {5,3,2,4,7,6,8};
		IntegerStream stream = new IntegerStream(arr);
		BSTPONode root = constructBST(stream, Integer.MIN_VALUE, Integer.MAX_VALUE);
		preorder(root);
	}
	
	public static BSTPONode constructBST(IntegerStream stream, int min, int max){
		BSTPONode node=null;
		if(stream.hasNext() && stream.peek() > min && stream.peek() < max){
			int val = stream.getNext();
			node = new BSTPONode(val);
			if(stream.hasNext()){
				node.left = constructBST(stream, min, val);
				node.right = constructBST(stream, val, max);
			}
		}
		return node;
	}
	
	public static void preorder(BSTPONode root){
		if(root == null){
			return;
		}
		System.out.println(root.value);
		preorder(root.left);
		preorder(root.right);
	}
	
	

}

class IntegerStream {
	
	Integer[] array;
	int index;
	
	public IntegerStream(Integer[] array) {
		this.array = array;
		this.index = 0;
	}
	
	boolean hasNext(){
		return index < array.length;
	}
	
	Integer getNext(){
		Integer temp = array[index];
		index++;
		return temp;
	}
	
	Integer peek(){
		return array[index];
	}
}

class BSTPONode {
	
	int value;
	BSTPONode left;
	BSTPONode right;
	
	public BSTPONode(int value) {
		this.value = value;
	}
	
}
