package com.kushd.simple;

import java.util.Stack;


public class Msort {
	
	public static void main(String[] args){
		//SimpleNode head = makeRandomList(5);
		//display(head);
		//SimpleNode sortedhead = mergeSort(head, 5);
		//SimpleNode reversehead = reverseRec(null,head);
		System.out.println(1<<2);
		//display(reversehead);
		
	}
	
	private static SimpleNode makeRandomList(int len){
		SimpleNode head = new SimpleNode(1);
		int i=0;
		SimpleNode current = head;
		while(i<(len-1)){
			SimpleNode temp = new SimpleNode((int)(Math.random()*100));
			current.next = temp;
			current = temp;
			i++;
		}
		return head;
	}
	
	private static void display(SimpleNode head){
		SimpleNode temp = head;
		while(temp!=null){
			System.out.println(temp.value);
			temp = temp.next;
		}
	}
	
	
	private static SimpleNode mergeSort(SimpleNode head, int size){
		if(size>1){
			int leftsize = size/2;
			int rightsize = size - leftsize;
			int i=0;
			SimpleNode mid = head;
			while(i<leftsize){
				mid = mid.next;
				i++;
			}
			
			return merge(mergeSort(head,leftsize),mergeSort(mid,rightsize),leftsize,rightsize);
		}else{
			return head;
		}
	}
	
	private static SimpleNode merge(SimpleNode left, SimpleNode right, int leftsize, int rightsize){
		if(leftsize==0){
			if(rightsize==1){
				right.next = null;
			}
			return right;
		}
		if(rightsize==0){
			if(leftsize==1){
				left.next = null;
			}
			return left;
		}
		if(left.value < right.value){
			left.next = merge(left.next,right,leftsize-1,rightsize);
			return left;
		}else{
			right.next = merge(left,right.next,leftsize,rightsize-1);
			return right;
		}
		
	}
	
	private static SimpleNode reverse(SimpleNode head){
		SimpleNode current = head;
		SimpleNode prev = null;
		while(current!=null){
			SimpleNode temp = current;
			current = current.next;
			temp.next = prev;
			prev = temp;
			
			//System.out.println(prev.getNodeData());
			
		}
		return prev;
	}
	
	private static SimpleNode reverseRec(SimpleNode prev, SimpleNode cur){
		if(null == cur){
			return prev;
		}
		SimpleNode temp = cur.next;
		cur.next = prev;
		return reverseRec(cur, temp);
	}
	
	private static boolean isPalindrome(SimpleNode head){
		SimpleNode fast = head;
		SimpleNode slow = head;
		
		Stack<Integer> stack = new Stack<Integer>();
		while(fast!=null&& fast.next!=null){
			stack.push(slow.value);
			slow = slow.next;
			fast = fast.next.next;
		}
		
		if(fast!=null){
			slow = slow.next;
		}
		
		while(slow!=null){
			if(stack.pop() != slow.value){
				return false;
			}
			slow = slow.next;
		}
		
		return true;
		
	}

}
