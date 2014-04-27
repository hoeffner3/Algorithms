package com.kushd.simple;

public class Qsort {
	
	private static int[] array = {23,12,45,24,56,2,52,35,14,29,23,12,45,24,56,2,52,35,14,29};
	
	public static void main(String[] args) {
		
		//for(int i=0;i<20;i++){
			//System.out.println(array[i]);
		//}
		System.out.println(findKLargest(0, 19, 5));
		System.out.println("");
		quicksort(0, 19);
		System.out.println("");
		for(int i=0;i<20;i++){
			System.out.println(array[i]);
		}
		System.out.println("");
		System.out.println(findKLargest(0, 19, 5));
		
	}
	
	
	public static void quicksort(int start, int end){
		int x = array[start];
		int i = start;
		int j = start+1;
		while(j<=end){
			if(array[j] <= x){
				int temp = array[j];
				array[j] = array[i+1];
				array[i+1] = temp;
				j++; i++;
			}else{
				j++;
			}
		}
		int tp = array[start];
		array[start] = array[i];
		array[i] = tp;
		
		if((i-1)>start){
			quicksort(start,(i-1));
		}
		if((i+1)<end){
			quicksort((i+1),end);
		}
	}
	
	public static int findKLargest(int start, int end, int k){
		int x = array[start];
		int i = start;
		int j = start+1;
		while(j<=end){
			if(array[j] <= x){
				int temp = array[j];
				array[j] = array[i+1];
				array[i+1] = temp;
				i++;
			}
			j++;
		}
		int tp = x;
		array[start] = array[i];
		array[i] = tp;
		
		if((i+1)==k)
			return x;
		else if((i+1)<k)
			return findKLargest(i+1, end, k);
		else
			return findKLargest(start, i-1, k);
	}
	
	public static int findKsmallest(int start, int end, int k){
		if(start >=end){
			return array[start];
		}
		int x = array[start];
		int i = start;
		int j = start+1;
		while(j<=end){
			if(array[j] < x){
				int temp = array[j];
				array[j] = array[i+1];
				array[i+1] = temp;
				i++;
			}
			j++;
		}
		int tp = x;
		array[start] = array[i];
		array[i] = tp;
		
		if((i+1)==k){
			return x;
		}else if((i+1)<k){
			return findKsmallest(i+1, end, k);
		}else{
			return findKsmallest(start, i-1, k);
		}
	}

}
