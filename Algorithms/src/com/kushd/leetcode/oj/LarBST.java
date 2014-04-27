package com.kushd.leetcode.oj;
import java.io.ObjectInputStream.GetField;

public class LarBST {
	
	public static void main(String[] args) {
		//int[] candidates = {2,3,6,7};
		//int[] indexes = {0,0,0,0};
		//combinationsToSum(7, candidates, 0, indexes, 0);
		
		/*Node six = new Node(6,null,null);
		Node eleven = new Node(11,null,null);
		Node ten = new Node(10,null,null);
		Node nine = new Node(9,null,null);
		Node tn = new Node(39,null,null);
		Node seven = new Node(7,null,null);
		
		Node five = new Node(5,null,six);
		Node thirteen = new Node(13,eleven,null);
		Node two = new Node(2,ten,null);
		Node three = new Node(3,nine,null);
		
		Node twelve = new Node(12,five,thirteen);
		Node tf = new Node(34,two,null);
		Node ts = new Node(27,three,tn);
		
		Node eight = new Node(8,seven,twelve);
		Node fs = new Node(56,tf,ts);
		
		Node four = new Node(4,null,eight);
		Node sn = new Node(69,fs,null);
		
		Node te = new Node(28,four,sn);*/
		
		//int height = printLeftEdges(te);
		//System.out.println("height-"+height);
		Node one = new Node(1,null,null);
		Node eight = new Node(8,null,null);
		Node seven = new Node(7,null,null);
		Node five = new Node(5,one,eight);
		Node fifteen = new Node(15, null,seven);
		Node ten = new Node(10,five,fifteen);
		Subtree subtree = findLargestBSTSubtree(ten);
		System.out.println(subtree.getNodes() + "-"+subtree.getRoot().getValue());
	}
	
	
	
	
	public static Subtree findLargestBSTSubtree(Node root){
		if(null == root){
			return new Subtree(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, null, true);
		}
		Subtree lefttree = findLargestBSTSubtree(root.getLeft());
		Subtree righttree = findLargestBSTSubtree(root.getRight());
		if(lefttree.isBST() && righttree.isBST()){
			if(root.getValue() >= lefttree.getMax() && root.getValue() <= righttree.getMin()){
				return new Subtree(lefttree.getMin() == Integer.MAX_VALUE?root.getValue():lefttree.getMin(), 
						righttree.getMax()==Integer.MIN_VALUE?root.getValue():righttree.getMax(), 
								lefttree.getNodes()+righttree.getNodes()+1, 
								root, true);
			}
		}
		if(lefttree.getNodes() > righttree.getNodes()){
			lefttree.setBST(false);
			return lefttree;
		}
		else{
			righttree.setBST(false);
			return righttree;
		}
		
	}
	
	public static int printLeftEdges(Node root){
		if(null == root){
			return 0;
		}
		System.out.println(root.getValue());
		int level = printLeftEdges(root.getLeft());
		root = printLevel(root.getRight(), level);
		int level2 = printLeftEdges(root);
		
		return level+level2+1;
	}


	private static Node printLevel(Node root, int level) {
		if(null == root){
			return null;
		}
		if(level == 0){
			return root;
		}
		if(root.getLeft() == null && root.getRight() == null){
			System.out.println(root.getValue());
		}
		Node left = printLevel(root.getLeft(),level-1);
		if(left != null){
			return left;
		}
		return printLevel(root.getRight(), level-1);
	}

}

class Subtree{
	private int min;
	private int max;
	private int nodes;
	private Node root;
	private boolean isBST;
	
	public Subtree(int min, int max, int nodes, Node root, boolean isBST) {
		this.min = min;
		this.max = max;
		this.nodes = nodes;
		this.root = root;
		this.isBST = isBST;
	}
	
	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}
	public int getNodes() {
		return nodes;
	}
	public void setNodes(int nodes) {
		this.nodes = nodes;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}

	public boolean isBST() {
		return isBST;
	}

	public void setBST(boolean isBST) {
		this.isBST = isBST;
	}
}
