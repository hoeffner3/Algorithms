package com.kushd.simple;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class BFSReverse {
	
	public static void main(String[] args){
		BNode head = new BNode(4);
		BNode child1 = new BNode(45);
		BNode child2 = new BNode(23);
		BNode child3 = new BNode(87);
		List<BNode> childlist = new LinkedList<BNode>();
		childlist.add(child1);
		childlist.add(child2);
		childlist.add(child3);
		head.setNodeChild(childlist);
		
		displayBFSReverse(head);
	}
	
	private static void displayBFSReverse(BNode head){
		Queue<BNode> queue = new LinkedList<BNode>();
		Stack<BNode> stack = new Stack<BNode>();
		queue.add(head);
		System.out.println("Came here");
		while(!queue.isEmpty()){
			BNode node = queue.poll();
			List<BNode> childs = node.getChildren();
			if(childs!=null){
				int size = childs.size();
				int i=0;
				while(i<size){
					BNode n = childs.get(i);
					queue.add(n);
					i++;
				}
			}
			stack.push(node);
		}
		System.out.println("finish");
		while(!stack.isEmpty()){
			System.out.println((stack.pop()).value);
			
		}
	}

}
