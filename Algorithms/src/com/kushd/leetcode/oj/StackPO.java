package com.kushd.leetcode.oj;
import java.util.Iterator;
import java.util.Stack;

public class StackPO {
	
	
	private void poTraversal(StPONode root){
		if(root == null){
			return;
		}
		Stack<StPONode> st = new Stack<StPONode>();
		StPONode prev = null;
		StPONode cur = root;
		st.push(root);
		while(!st.isEmpty()){
			cur = st.peek();
			if(prev==null || (prev.left == cur) || (prev.right == cur)){
				if(cur.left != null){
					st.push(cur.left);
				}else if(cur.right != null){
					st.push(cur.right);
				}else{
					st.pop();
					System.out.println(cur.value);
				}
			}else if(cur.left == prev){
				if(cur.right != null){
					st.push(cur.right);
				}else{
					st.pop();
					System.out.println(cur.value);
				}
			}else if(cur.right == prev){
				st.pop();
				System.out.println(cur.value);
			}
			prev = cur;
		}
		
	}
	
	private void inTraversal(StPONode root){
		Stack<StPONode> st = new Stack<StPONode>();
		StPONode cur=root;
		while(!st.isEmpty() || cur != null){
			if(cur != null){
				st.push(cur);
				cur = cur.left;
			}else{
				cur = st.pop();
				System.out.println(cur.value);
				cur = cur.right;
			}
		}
	}
	
	private void preTraversal(StPONode root){
		Stack<StPONode> st = new Stack<StPONode>();
		StPONode cur=root;
		while(!st.isEmpty() || cur != null){
			if(cur != null){
				System.out.println(cur.value);
				st.push(cur);
				cur = cur.left;
			}else{
				cur = st.pop();
				cur = cur.right;
			}
		}
	}

}

class TreeInteratorInOrder implements Iterator<StPONode> {
	
	private StPONode cur;
	private Stack<StPONode> stack;
	
	public TreeInteratorInOrder(StPONode root) {
		this.cur = root;
		this.stack = new Stack<StPONode>();
	}

	@Override
	public boolean hasNext() {
		return cur != null || !stack.isEmpty();
	}

	@Override
	public StPONode next() {
		while(cur != null){
			stack.push(cur);
			cur = cur.left;
		}
		StPONode temp = stack.pop();
		cur = temp.right;
		return temp;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
}


class StPONode {
	
	int value;
	StPONode left;
	StPONode right;
	
	public StPONode(int value) {
		this.value = value;
	}
	
}
