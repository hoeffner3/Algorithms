package com.kushd.moderate;

public class MaxProductSubArray {

	
	
	public int maxProduct(int[] array){
		int max_ending_here = 1;
		int min_ending_here = 1;
		int max = Integer.MIN_VALUE;
		for(int i=0;i<array.length;i++){
			if(array[i] == 0){
				max_ending_here = 1;
				min_ending_here = 1;
			}else if(array[i] > 0){
				max_ending_here = max_ending_here*array[i];
				min_ending_here = Math.min(1, min_ending_here*array[i]);
			}else{
				int temp = max_ending_here;
				max_ending_here = Math.max(min_ending_here*array[i],1);
				min_ending_here = temp*array[i];
			}
			
			if(max_ending_here > max){
				max = max_ending_here;
			}
		}
		return max;
	}
	
}
