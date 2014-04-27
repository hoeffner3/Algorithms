package com.kushd.leetcode.oj;
import java.util.ArrayList;
import java.util.Collections;

public class CombinationsII {
	
	
	public static void main(String[] args) {
		int[] num = {2,5};
		combinationSum2(num, 7);
		System.out.println(finallist);
	}
	
	
	private static ArrayList<ArrayList<Integer>> finallist;
    
    public static ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        
        //Arrays.sort(num);
        finallist = new ArrayList<ArrayList<Integer>>();
        int[] temp = new int[num.length];
        helper(num,target,temp,0,0);
        return finallist;
    }
    
    private static void helper(int[] array, int target, int[] temp, int index, int sum){
        
        if(sum >= target){
            if(sum == target){
                ArrayList<Integer> list = new ArrayList<Integer>();
                for(int i=0;i<index;i++){
                    list.add(new Integer(temp[i]));
                }
                Collections.sort(list);
                finallist.add(list);
            }
            return;
        }
        
        for(int j=index;j<array.length;j++){
            temp[index] = array[j];
            sum = sum + temp[index];
            helper(array,target,temp,index+1,sum);
            sum = sum - temp[index];
        }
        
        return;
    }

}
