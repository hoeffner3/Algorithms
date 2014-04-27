package com.kushd.simple;


import java.util.List;



public class TreeProblems {
		private int value;
		public TreeProblems left;
		public TreeProblems right;
		
		public TreeProblems(int v){
			value = v;
			left=null;
			right=null;
		}
		public int getNodeData(){
			return value;
		}
		
		
		public static boolean isBalanced(TreeProblems root){
			if(checkHeight(root) == -1){
				return false;
			}
			return true;
		}
		
		private static int checkHeight(TreeProblems cur){
			if(cur==null){
				return 0;
			}
			int leftHeight = checkHeight(cur.left);
			if(leftHeight==-1){
				return -1;
			}
			int rightHeight = checkHeight(cur.right);
			if(rightHeight==-1){
				return -1;
			}
			if(Math.abs((leftHeight-rightHeight))>1){
				return -1;
			}else{
				return (Math.max(leftHeight, rightHeight)+1);
			}
		}
		
		public static boolean containsTree(TreeProblems big, TreeProblems small){
			if(small == null){
				return true;
			}
			return subtree(big,small);
		}
		
		private static boolean subtree(TreeProblems big, TreeProblems small){
			if(big==null){
				return false;
			}
			if(big.value == small.value){
				if (matchtree(big,small))
					return true;
			}
			return (subtree(big.left,small) || subtree(big.right,small));
		}
		
		private static boolean matchtree(TreeProblems big, TreeProblems small){
			if(big==null && small==null){
				return true;
			}
			if(big==null || small==null){
				return false;
			}
			if(big.value != small.value){
				return false;
			}else{
				return (matchtree(big.left, small.left)&&matchtree(big.right, small.right));
			}
		}
		
		public static void findSum(TreeProblems root, int v){
			int depth = depth(root);
			int[] path = new int[depth];
			findSum(root, v, path, 0);
		}
		
		private static void findSum(TreeProblems root, int v, int[] path, int level){
			if(root == null){
				return;
			}
			path[level] = root.value;
			int t=0;
			for(int i=level;i>0;i--){
				t+=path[i];
				if(v == t){
					printSum(path, i, level);
				}
			}
			findSum(root.left, v, path, level+1);
			findSum(root.right, v, path, level+1);
			
		}
		
		private static void printSum(int[] path, int start, int end){
			for(int i=start;i<=end;i++){
				System.out.println(path[i]+" ");
			}
			System.out.println("");
		}
		private static int depth(TreeProblems root){
			if(root == null){
				return 0;
			}
			return 1+(Math.max(depth(root.left), depth(root.right)));
		}
	};