package com.kushd.hackerrank;
import java.util.Scanner;

public class MedianCount {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
	      
	      int N;
	      N = in.nextInt();
	   
	      String s[] = new String[N];
	      int x[] = new int[N];
	      
	      for(int i=0; i<N; i++){
	         s[i] = in.next();
	         x[i] = in.nextInt();
	      }
	   
	      median(s,x);
	}
	
	private static void median(String[] a, int[] x){
		MBST tree = new MBST();
		int cnt=0;
		for(String cmd : a){
			if(cmd.equals("a")){
				tree.insert(x[cnt]);
				if(tree.size%2==0){
					long temp = tree.getEvenMedianSum();
					if(temp%2==0){
						System.out.println(temp/2);
					}else{
						double temp2 = (double)temp/(double)2;
						System.out.println(String.format("%.1f", temp2));
					}
				}else{
					System.out.println(tree.getOddMedian());
				}
			}else{
				boolean remove = tree.delete(x[cnt]);
				if(remove){
					if(tree.size%2==0){
						long temp = tree.getEvenMedianSum();
						if(temp%2==0){
							System.out.println(temp/2);
						}else{
							double temp2 = (double)temp/(double)2;
							System.out.println(String.format("%.1f", temp2));
						}
					}else{
						System.out.println(tree.getOddMedian());
					}
				}else{
					System.out.println("Wrong!");
				}
			}
			cnt++;
		}
	}
	
}

class MBST {
	
	public int size=0;
	private MKNode root;
	
	public void insert(long number){
		root = insertHelper(root,number);
	}

	private MKNode insertHelper(MKNode node,long number) {
		if(node == null){
			node = new MKNode(number, 0, 0,1);
			size++;
		}else if(number > node.value){
			node.right = insertHelper(node.right, number);
			node.rcnt++;
			int leftheight = node.left == null?0:node.left.height;
			int rightheight = node.right == null?0:node.right.height;
			node.height = Math.max(leftheight,rightheight)+1;
			if(rightheight - leftheight == 2 ){
				if(number > node.right.value){
					node = rotateWithRight(node);
				}else{
					node = doublerotatewithright(node);
				}
			}
		}else{
			node.left = insertHelper(node.left, number);
			node.lcnt++;
			int leftheight = node.left == null?0:node.left.height;
			int rightheight = node.right == null?0:node.right.height;
			node.height = Math.max(leftheight,rightheight)+1;
			if(leftheight - rightheight == 2){
				if(number > node.left.value){
					node = doublerotatewithleft(node);
				}else{
					node = rotateWithLeft(node);
				}
			}
		}
		return node;
		
	}
	
	private MKNode rotateWithLeft(MKNode cur){
		MKNode left = cur.left;
		cur.left = left.right;
		left.right = cur;
		
		cur.lcnt = cur.left == null?0:(cur.left.lcnt+cur.left.rcnt+1);
		left.rcnt = cur.lcnt+cur.rcnt+1;
		
		int leftheight = cur.left == null?0:cur.left.height;
		int rightheight = cur.right == null?0:cur.right.height;
		cur.height = Math.max(leftheight, rightheight)+1;
		leftheight = left.left == null?0:left.left.height;
		rightheight = left.right == null?0:left.right.height;
		left.height = Math.max(leftheight, rightheight)+1;
		
		return left;
	}
	
	private MKNode rotateWithRight(MKNode cur){
		MKNode right = cur.right;
		cur.right = right.left;
		right.left = cur;
		
		cur.rcnt = cur.right == null?0:(cur.right.lcnt+cur.right.rcnt+1);
		right.lcnt = cur.lcnt+cur.rcnt+1;
		
		int leftheight = cur.left == null?0:cur.left.height;
		int rightheight = cur.right == null?0:cur.right.height;
		cur.height = Math.max(leftheight, rightheight)+1;
		leftheight = right.left == null?0:right.left.height;
		rightheight = right.right == null?0:right.right.height;
		right.height = Math.max(leftheight, rightheight)+1;
		
		return right;
	}
	
	private MKNode doublerotatewithleft(MKNode cur){
	     cur.left = rotateWithRight(cur.left);
	     cur = rotateWithLeft(cur);

	     return cur;
	}
	
	private MKNode doublerotatewithright(MKNode cur){
	     cur.right = rotateWithLeft(cur.right);
	     cur = rotateWithRight(cur);

	     return cur;
	}

	
	public long getEvenMedianSum(){
		if(size==0){
			return -1;
		}
		
		long one = getNodeRank(root,size/2);
		long two = getNodeRank(root,(size/2)+1);
		
		return one+two;
	}
	
	public long getOddMedian(){
		if(size==0){
			return -1;
		}
		long one = getNodeRank(root,(size/2)+1);
		return one;
	}
	
	private long getNodeRank(MKNode node, long rank) {
		if(rank == node.lcnt+1){
			return node.value;
		}else if(rank > node.lcnt+1){
			return getNodeRank(node.right, (rank-node.lcnt-1));
		}else{
			return getNodeRank(node.left, rank);
		}
		
	}

	public boolean delete(long number){
		if(!doesExist(root, number)){
			return false; 
		}
		root = deleteHelper(root,number);
		size--;
		return (size>0);
	}
	
	private MKNode deleteHelper(MKNode node, long number) {
		if(number > node.value){
			node.right = deleteHelper(node.right, number);
			node.rcnt--;
			int leftheight = node.left == null?0:node.left.height;
			int rightheight = node.right == null?0:node.right.height;
			node.height = Math.max(leftheight,rightheight) + 1;
			if(leftheight - rightheight == 2){
				int leftgcheight = node.left.left==null?0:node.left.left.height;
				int rightgcheight = node.left.right==null?0:node.left.right.height;
				if(leftgcheight >= rightgcheight){
					node = rotateWithLeft(node);
				}else{
					node = doublerotatewithleft(node);
				}
			}
		}else if(number < node.value){
			node.left = deleteHelper(node.left, number);
			node.lcnt--;
			int leftheight = node.left == null?0:node.left.height;
			int rightheight = node.right == null?0:node.right.height;
			node.height = Math.max(leftheight,rightheight) + 1;
			if(rightheight - leftheight == 2){
				int leftgcheight = node.right.left==null?0:node.right.left.height;
				int rightgcheight = node.right.right==null?0:node.right.right.height;
				if(rightgcheight >= leftgcheight){
					node = rotateWithRight(node);
				}else{
					node = doublerotatewithright(node);
				}
			}
		}else{
			if(node.left == null){
				return node.right;
			}else if(node.right == null){
				return node.left;
			}else{
				MKNode pred = getPred(node.left);
				node.value = pred.value;
				node.left = deleteHelper(node.left, pred.value);
				node.lcnt--;
				int leftheight = node.left == null?0:node.left.height;
				int rightheight = node.right == null?0:node.right.height;
				node.height = Math.max(leftheight,rightheight) + 1;
				if(rightheight - leftheight == 2){
					int leftgcheight = node.right.left==null?0:node.right.left.height;
					int rightgcheight = node.right.right==null?0:node.right.right.height;
					if(rightgcheight >= leftgcheight){
						node = rotateWithRight(node);
					}else{
						node = doublerotatewithright(node);
					}
				}
			}
		}
		return node;
	}

	private MKNode getPred(MKNode cur) {
		MKNode prev = null;
		while(cur!=null){
			prev = cur;
			cur = cur.right;
		}
		return prev;
	}

	private boolean doesExist(MKNode node, long number){
		if(null == node){
			return false;
		}
		if(number == node.value){
			return true;
		}
		if(number > node.value){
			return doesExist(node.right, number);
		}else{
			return doesExist(node.left, number);
		}
	}
	

}

class MKNode {
	
	public long value;
	public MKNode left;
	public MKNode right;
	public int lcnt;
	public int rcnt;
	public int height;
	
	public MKNode(long value, int lcnt, int rcnt,int height) {
		this.value = value;
		this.lcnt = lcnt;
		this.rcnt = rcnt;
		this.height = height;
	}
}

