package com.kushd.moderate;

import java.util.HashMap;
import java.util.Map;

public class SubArraySequence {
	
	public static void main(String[] args){
		//int[] arr = {7,3,5,1,2,8};
		//findContiguousSequence(arr);
		int[] arr = {-1,3,-5,4,6,-1,2,-7,13,-3};
		maxSubArray(arr);
	}
	
	public static int findContiguousSequence(int[] arr){
		int maxLen=0;
		int start=0;
		Map<Integer,Integer> store = new HashMap<Integer,Integer>();
		
		int i=0;
		while(i<arr.length){
			int left = store.containsKey(arr[i]-1) ? store.get(arr[i]-1):0;
			int right = store.containsKey(arr[i]+1)?store.get(arr[i]+1):0;
			int len = left+1+right;
			store.put(arr[i]-left, len);
			store.put(arr[i]+right, len);
			if(len>maxLen){
				maxLen=len;
				start = arr[i]-left;
			}
			i++;
		}
		System.out.println("Answer----");
		System.out.println(start);
		System.out.println(maxLen);
		
		return start;
	}
	
	public static void maxSubArray(int[] arr){
		int maxSumSoFar = 0;
		int a=-1;
		int b=-1;
		int sum = 0;
		int i=0;
		int j=0;
		while(i<arr.length){
			sum = sum + arr[i];
			if(sum<0){
				sum = 0;
				j = i+1;
			}
			if(sum > maxSumSoFar){
				maxSumSoFar = sum;
				a = j;
				b = i;
			}
			i++;
		}
		System.out.println(a+"   "+b);
	}

}
