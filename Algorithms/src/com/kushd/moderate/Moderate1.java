package com.kushd.moderate;

public class Moderate1 {
	
	
	public static BiNode convertToDLL(BiNode root){
		if(null == root)
			return null;
		BiNode part1 = convertToDLL(root.left);
		if(part1!=null){
			root.left = part1.left;
			part1.left.right = root;
		}
		BiNode part3 = convertToDLL(root.right);
		if(part3!=null){
			root.right = part3;
			part3.left = root;
		}
		//concat head and tail to ensure circular DLL
		BiNode head = part1 == null?root:part1;
		BiNode tail = part3 == null?root:part3.left;
		head.left = tail;
		tail.right = head;
		return head;
	}
	
	public static void swapInPlace(int a, int b){
		a = a^b;
		b = a^b;
		a = b^a;
		
		System.out.println("a: "+a);
		System.out.println("b: "+b);
	}
	
	public static int getMax(int a, int b){
		int c = a-b;
		int sa = getSign(a);
		int sb = getSign(b);
		int sc = getSign(c);
		
		int useSignA = sa^sb;
		int useSignC = useSignA^1;
		int k = useSignA*sa+useSignC*sc;
		int q = k^1;
		return a*k+b*q;
	}
	
	public static int getSign(int n){
		return (((n>>31)&0x1)^1);
	}
	
	public static int countFactZeros(int n){
		if(n<0){
			return -1;
		}
		int cnt=0;
		for(int i=5;i<n;i*=5){
			cnt += n/i;
		}
		return cnt;
	}
	
	

}

class BiNode{
	public int data;
	public BiNode left;
	public BiNode right;
}
