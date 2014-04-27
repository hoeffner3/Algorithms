package com.kushd.simple;


import java.util.Stack;


public class BST {
	
	private int count;
	private long sum;
	private TreeProblems root;
	
	public BST(){
		root=null;
		count=0;
		sum=0;
	}

	public void insert(int value){
		root = insert(value,root);
	}
	
	private TreeProblems insert(int value, TreeProblems current){
		if(current==null){
			current = new TreeProblems(value);
			count++;
		}else if(value < current.getNodeData()){
			current.left = insert(value,current.left);
		}else if(value > current.getNodeData()){
			current.right = insert(value,current.right);
		}else if(value == current.getNodeData()){
			System.out.println("Duplicate node cannot be added");
		}
		return current;
	}
	
	public void preorder(){
		preorder(root);
	}
	
	private void preorder(TreeProblems current){
		if(current!=null){
			System.out.println(current.getNodeData());
			preorder(current.left);
			preorder(current.right);
		}
	}
	
	public void inorderIterative(TreeProblems root){
		Stack<TreeProblems> st = new Stack<TreeProblems>();
		TreeProblems current = root;
		while(!st.empty() || current!=null){
			if(current!=null){
				st.push(current);
				current=current.left;
			}else{
				current = st.peek();
				st.pop();
				System.out.println(current.getNodeData());
				current = current.right;
			}
		}
	}
	
	
	
}
