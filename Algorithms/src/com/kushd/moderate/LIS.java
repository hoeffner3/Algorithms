package com.kushd.moderate;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LIS {
	
	public static void main(String[] args){
		
		int[] array = {10,22,9,33,21,50,41,60,80};
		getLIS(array, array.length-1);
		
		for(Integer key : stacks.keySet()){
			System.out.println(stacks.get(key));
		}
		
	}
	
	private static Map<Integer,Stack<Integer>> stacks = new HashMap<Integer,Stack<Integer>>();
	
	public static Stack<Integer> getLIS(int[] array, int n){
		if(stacks.get(n)!=null){
			return stacks.get(n);
		}
		
		if(n==0){
			Stack<Integer> temp = new Stack<Integer>();
			temp.add(array[n]);
			stacks.put(n, temp);
			return temp;
		}
		int max=0;
		Stack<Integer> newst = new Stack<Integer>();
		for(int i=n-1;i>=0;i--){
			Stack<Integer> temp = getLIS(array,i);
			if(temp.peek()<array[n] && temp.size() > max){
				newst.clear();
				for(Integer ii : temp){
					newst.add(ii);
				}
				//newst = (Stack<Integer>) temp.clone();
				max = temp.size();
			}
		}
		newst.add(array[n]);
		stacks.put(n, newst);
		return newst;
	}

}
