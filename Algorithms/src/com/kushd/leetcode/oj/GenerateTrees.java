package com.kushd.leetcode.oj;
import java.util.ArrayList;

public class GenerateTrees {
	
	public static void main(String[] args) {
		ArrayList<Node> list = generateTrees(10);
		for(Node roott : list){
			System.out.println("Printing Tree");
			System.out.println("");
			printTree(roott);
			System.out.println("");
		}
		System.out.println(list.size());
	}
	
	private static void printTree(Node root){
		if(root == null){
			System.out.print("#");
			return;
		}
		System.out.print(root.getValue());
		printTree(root.left);
		printTree(root.right);
	}
	
	public static ArrayList<Node> generateTrees(int n) {
        ArrayList<Node> returnList = new ArrayList<Node>();
        for(int i=1;i<=n;i++){
            ArrayList<Node> list = recurse(i,createLeftNodes(i),createRightNodes(i,n));
            returnList.addAll(list);
        }
        return returnList;
    }
    
    private static ArrayList<Integer> createLeftNodes(int node){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=1;i<node;i++){
            list.add(i);
        }
        return list;
    }
    
    private static ArrayList<Integer> createRightNodes(int node, int n){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=node+1;i<=n;i++){
            list.add(i);
        }
        return list;
    }
    
    private static ArrayList<Node> recurse(int node, ArrayList<Integer> leftNodes, ArrayList<Integer> rightNodes){
        ArrayList<Node> list = new ArrayList<Node>();
        Node root = new Node(node,null,null);
        list.add(root);
        ArrayList<Node> tempList = new ArrayList<Node>();
        for(Integer leftNode : leftNodes){
            ArrayList<Node> leftTrees = recurse(leftNode,getLeftNodes(leftNode,leftNodes),getRightNodes(leftNode,leftNodes));
            for(Node leftTree : leftTrees){
                for(Node element : list){
                	Node newElement = copyTree(element);
                    newElement.left = leftTree;
                    tempList.add(newElement);
                }
            }
        }
        if(!tempList.isEmpty()){
        	list.clear();
        	list.addAll(tempList);
        	tempList.clear();
        }
        for(Integer rightNode : rightNodes){
            ArrayList<Node> rightTrees = recurse(rightNode,getLeftNodes(rightNode,rightNodes),getRightNodes(rightNode,rightNodes));
            for(Node rightTree : rightTrees){
                for(Node element : list){
                	Node newElement = copyTree(element);
                    newElement.right = rightTree;
                    tempList.add(newElement);
                }
            }
        }
        if(!tempList.isEmpty()){
        	list.clear();
        	list.addAll(tempList);
        	tempList.clear();
        }
        return list;
    }
    
    private static Node copyTree(Node root){
    	if(root == null){
    		return null;
    	}
    	Node newRoot = new Node(root.getValue(),null,null);
    	newRoot.left = copyTree(root.left);
    	newRoot.right = copyTree(root.right);
    	return newRoot;
    }
    
    private static ArrayList<Integer> getLeftNodes(int node, ArrayList<Integer> nodes){
        ArrayList<Integer> leftList = new ArrayList<Integer>();
        for(Integer element : nodes){
            if(element < node){
                leftList.add(element);
            }
        }
        return leftList;
    }
    
    private static ArrayList<Integer> getRightNodes(int node, ArrayList<Integer> nodes){
        ArrayList<Integer> rightList = new ArrayList<Integer>();
        for(Integer element : nodes){
            if(element > node){
                rightList.add(element);
            }
        }
        return rightList;
    }

}
