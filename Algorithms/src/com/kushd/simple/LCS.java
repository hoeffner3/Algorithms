package com.kushd.simple;

public class LCS {
	
	
	
	
	public TreeProblems lCS(TreeProblems root, TreeProblems one, TreeProblems two){
		if(one == null || two == null || root == null){
			return null;
		}
		if(Math.max(one.getNodeData(), two.getNodeData()) < root.getNodeData()){
			return lCS(root.left, one, two);
		}else if(Math.min(one.getNodeData(), two.getNodeData()) > root.getNodeData()){
			return lCS(root.right, one, two);
		}else{
			return root;
		}
	}
	
	public TreeProblems lCSBT(TreeProblems root, TreeProblems one, TreeProblems two){
		if(root == null || one == null || two == null){
			return null;
		}
		if(root == one || root == two){
			return root;
		}
		TreeProblems left = lCSBT(root.left, one, two);
		TreeProblems right = lCSBT(root.right, one, two);
		if(left!=null && right!=null){
			return root;
		}
		return (left!=null)?left:right;
	}

}
