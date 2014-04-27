package com.kushd.leetcode.archives;
import java.util.HashMap;
import java.util.Map;

public class Second {
	
	public static void main(String[] args) {
		//String output = minWindow("acbbaca", "aba");
		//System.out.println(output);
		int[] array = {7,2,4,9,1,3,5};
		String output = getBestTime(array);
		System.out.println(output);
	}
	
	
	public static String minWindow(String input, String pattern){
		int inputLen = input.length();
		int patternLen = pattern.length();
		Map<Character, Integer> needToFind = new HashMap<Character,Integer>();
		Map<Character, Integer> hasFound = new HashMap<Character, Integer>();
		for(int i=0;i<patternLen;i++){
			if(needToFind.get(pattern.charAt(i)) == null){
				needToFind.put(pattern.charAt(i), 1);
			}else{
				int temp = needToFind.get(pattern.charAt(i));
				needToFind.put(pattern.charAt(i), ++temp);
			}
		}
		
		int minWindowLength = Integer.MAX_VALUE;
		int minBegin=0;
		int count = 0;
		for(int begin=0,end=0;end<inputLen;end++){
			if(needToFind.get(input.charAt(end)) == null){
				continue;
			}
			//Updating the hashmap
			if(hasFound.get(input.charAt(end)) == null){
				hasFound.put(input.charAt(end), 1);
			}else{
				int temp = hasFound.get(input.charAt(end));
				hasFound.put(input.charAt(end), ++temp);
			}
			// 
			if(hasFound.get(input.charAt(end)) <= needToFind.get(input.charAt(end))){
				count++;
			}
			if(count == patternLen){
				Integer tempp = hasFound.get(input.charAt(begin));
				while(needToFind.get(input.charAt(begin)) == null || (tempp != null && tempp >
				needToFind.get(input.charAt(begin))) ){
					if(needToFind.get(input.charAt(begin)) == null){
						begin++;
					}else{
						hasFound.put(input.charAt(begin), --tempp);
						begin++;
					}
				}
				int windowLength = end - begin +1;
				if(windowLength < minWindowLength){
					minWindowLength = windowLength;
					minBegin = begin;
				}
			}
			
		}
		return minWindowLength + "-" + minBegin;
	}
	
	public static String getBestTime(int[] array){
		int size = array.length;
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		int maxDiff = 0;
		int begin = -1;
		int end = -1;
		for(int i=0;i<size;i++){
			if(array[i] < min){
				min = array[i];
				minIndex = i;
			}
			int curDiff = array[i] - min;
			if(curDiff > maxDiff){
				maxDiff = curDiff;
				begin = minIndex;
				end = i;
			}
		}
		return begin + "-" + end;
	}
	
	public static void combinationsToSum(int target, int[] candidates, int sum, int[] indexes, int n){
		if(sum > target){
			return;
		}
		if(sum == target){
			printSum(indexes, candidates, n);
			System.out.println("new");
		}
		for(int i = indexes[n]; i < candidates.length; i++){
			indexes[n] = i;
			combinationsToSum(target, candidates, sum+candidates[i], indexes, n+1);
		}
	}

	private static void printSum(int[] indexes, int[] candidates, int n) {
		for(int i=0;i<n;i++){
			System.out.println(candidates[indexes[i]]);
		}
		
	}

}
