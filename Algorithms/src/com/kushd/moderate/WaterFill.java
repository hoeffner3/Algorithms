package com.kushd.moderate;

import java.util.Stack;

public class WaterFill {

	private static int[] arr = {9,8,7,6,5,4,3,2,1};
	
	public static void main(String[] args){
		
		int[] localmax = getLocalMax(rev(arr));
		localmax = rev(localmax);
		for(int i=0;i<localmax.length;i++){
			if(localmax[i]!=-1){
				localmax[i] = localmax.length-localmax[i]-1;
			}
		}
		for(int i=0;i<localmax.length;i++){
			System.out.println(localmax[i]);
		}
		System.out.println("");
		//int[] indexsum = calcWaterFill(arr, localmax);
		
		//for(int i=0;i<indexsum.length;i++){
		//	System.out.println(indexsum[i]);
		//}
	}
	
	public static int[] rev(int[] array){
		int[] revarr = new int[array.length];
		for(int i=0;i<revarr.length;i++){
			revarr[i] = array[array.length-i-1];
		}
		return revarr;
	}
	
	public static int[] getLocalMax(int[] arr){
		int[] localmax = new int[arr.length];
		for(int i=0; i<localmax.length;i++){
			localmax[i] = -1;
		}
		Stack<Integer> st = new Stack<Integer>();
		st.push(0);
		int i=1;
		while(i<localmax.length){
			int peek = st.peek();
			while(arr[peek] <= arr[i]){
				localmax[peek] = i;
				st.pop();
				if(st.isEmpty()){
					break;
				}else{
					peek = st.peek();
				}
			}
			st.push(i);
			i++;
		}
		return localmax;
	}
	
	public static int[] calcWaterFill(int[] arr, int[] localmax){
		int i=1;
		int globalMax = 0;
		int[] indexsum = new int[arr.length];
		indexsum[0] = 0;
		while(i<arr.length){
			if(arr[i]>=arr[globalMax]){
				indexsum[i] = indexsum[globalMax] + (arr[globalMax]*(i-globalMax));
				globalMax = i;
				i++;
			}else{
				if(arr[i]<=arr[i-1]){
					indexsum[i] = indexsum[i-1] + (arr[i]*1);
					i++;
				}else{
					indexsum[i] = indexsum[localmax[i]] + (arr[i]*(i-localmax[i]));
					i++;
				}
			}
		}
		return indexsum;
	}
	
}
