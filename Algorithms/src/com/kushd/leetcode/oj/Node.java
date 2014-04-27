package com.kushd.leetcode.oj;
public class Node {
	
	private int value;
	public Node left;
	public Node right;
	
	public Node(int value, Node left, Node right) {
		this.setValue(value);
		this.setLeft(left);
		this.setRight(right);
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
