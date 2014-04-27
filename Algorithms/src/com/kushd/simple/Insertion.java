package com.kushd.simple;

public class Insertion {
	
	public static void main(String[] args){
		int[] arr = {0,1,2,2,1,3,5,2};
		int[] ranks = findRanks(arr);
		for(int cnt=0;cnt<ranks.length;cnt++){
			System.out.println(ranks[cnt]);
		}
		
	}
	
	public static int[] findRanks(int[] array){
		
		int[] rank = new int[array.length];
		int i=0;
		while(i<array.length){
			int cpos = i-array[i];
			for(int j = (i+1);j<array.length;j++){
				if(j-array[j]<=cpos){
					cpos++;
				}
			}
			rank[i] = cpos+1;
			i++;
		}
		
		return rank;
		
	}
	

}
