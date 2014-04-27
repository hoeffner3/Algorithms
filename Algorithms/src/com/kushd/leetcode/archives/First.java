package com.kushd.leetcode.archives;

import java.util.HashMap;
import java.util.Map;

public class First {
	
	
	public static int[] swapArray(int[] array, int start, int end){
		int i = start;
		int j=0;
		while(i<=end){
			array[j] = array[i];
			i++; j++;
		}
		return array;
	}
	
	//m and n are size of the array
	public static int findKthSmallestInUnion(int[] arrayA, int m, int[] arrayB, int n, int k){
		
		int i = (int)((double)m/(m+n)*(k-1));
		int j = (k-1) - i;
		
		int Ai_1 = ((i == 0) ? Integer.MIN_VALUE : arrayA[i-1]);
		int Bj_1 = ((j == 0) ? Integer.MIN_VALUE : arrayB[j-1]);
		int Ai   = ((i == m) ? Integer.MAX_VALUE : arrayA[i]);
		int Bj   = ((j == n) ? Integer.MAX_VALUE : arrayB[j]);
		
		if(Bj_1 < Ai && Ai < Bj){
			return Ai;
		}
		if(Ai_1 < Bj && Bj < Ai){
			return Bj;
		}
		
		if(Ai < Bj){
			return findKthSmallestInUnion(swapArray(arrayA, i+1, m-1), m-i-1, arrayB, j, k-i-1);
		}else{
			return findKthSmallestInUnion(arrayA, i, swapArray(arrayB, j+1, n-1), n-j-1, k-j-1);
		}
		
	}
	
	private static Map<String,Boolean> map = new HashMap<String,Boolean>();
	
	public static void main(String[] args) {
		//Map<MaxMoney,Integer> maxMoneyMap = new HashMap<MaxMoney, Integer>();
		//int[] coins = {3,2};//{3,2,2,3,1,2};
		//MaxMoney maxMoney = new MaxMoney(0,1);
		//int result = getMaxMoney(maxMoney, coins, maxMoneyMap);
		//System.out.println(result);
		//String p = processPattern("**a****a*b*bab*a*****abb*a*bab*a****b**a**b**a***bbbb**b*b***b**a*b*ba*b**ab*aa*b***a*a****b****bab*a*");
		//System.out.println(p);
		map.clear();
		boolean ans = regularExpressionMatch("babbaabaaabaaababbbabbbabbbbbbaabbbbbabaaaabbbbaaaaaababababaaabbbababbaabbbbbbbbabbbbbabbbbabbbbbabbbbbbbaaabbbbbbbaabbbabbaabbbbaabbbaabaabbaaabbbbaaabbabbbabaabbbbbaababbaabbbaaabbbabaaaabaaaabbbbaba", "?*a*a*b*bab*a*abb*a*bab*a*b*a*b*a*bbbb*b*b*b*a*b*ba*b*ab*aa*b*a*a*b*bab*a*", 0, 0);
		System.out.println(ans);
	}
	
	public static int getMaxMoney(MaxMoney maxMoney, int[] coins, Map<MaxMoney,Integer> maxMoneyMap){
		if(maxMoney.start > maxMoney.end){
			return 0;
		}
		if(maxMoney.start == maxMoney.end){
			return coins[maxMoney.start];
		}
		
		if(maxMoneyMap.get(maxMoney) != null){
			return maxMoneyMap.get(maxMoney);
		}
		
		MaxMoney aa = new MaxMoney(maxMoney.start+2,maxMoney.end);
		MaxMoney bb = new MaxMoney(maxMoney.start+1,maxMoney.end-1);
		MaxMoney cc = new MaxMoney(maxMoney.start,maxMoney.end-2);
		int result = Math.max((coins[maxMoney.start]+Math.min(getMaxMoney(aa, coins, maxMoneyMap), getMaxMoney(bb, coins, maxMoneyMap))), 
				(coins[maxMoney.end]+Math.min(getMaxMoney(bb, coins, maxMoneyMap), getMaxMoney(cc, coins, maxMoneyMap))));
		
		maxMoneyMap.put(maxMoney, result);
		return result;
	}
	
	public static boolean regularExpressionMatch(String input, String pattern, int iIndex, int pIndex){
		if(pIndex == pattern.length()){
			boolean ans = (iIndex == input.length());
			map.put(iIndex+"-"+pIndex, ans);
			return ans;
		}
		if(iIndex == input.length()){
			boolean ans = (pIndex == pattern.length());
			map.put(iIndex+"-"+pIndex, ans);
		    return ans;
		}
		
		if(map.get(iIndex+"-"+pIndex) != null){
			return map.get(iIndex+"-"+pIndex);
		}
		
		if((pIndex+1 == pattern.length()) || (pattern.charAt(pIndex+1) != '*')){
			boolean ans = ((pattern.charAt(pIndex) == input.charAt(iIndex)) || (pattern.charAt(pIndex) == '?' && iIndex<input.length())) && regularExpressionMatch(input, pattern, iIndex+1, pIndex+1);
			map.put(iIndex+"-"+pIndex, ans);
			return ans;
		}
		
		while((pattern.charAt(pIndex) == '?' && iIndex<input.length()) || (pattern.charAt(pIndex) == input.charAt(iIndex)) ){
			if(regularExpressionMatch(input, pattern, iIndex, pIndex+2)){
				map.put(iIndex+"-"+pIndex, true);
				return true;
			}
			iIndex++;
			if(iIndex == input.length()){
				break;
			}
		}
		boolean ans = regularExpressionMatch(input, pattern, iIndex, pIndex+2);
		map.put(iIndex+"-"+pIndex, ans);
		return ans;
	}
	
	public static String processPattern(String p){
        if(p.charAt(0) == '*'){
            p = "?" + p;
        }
        boolean flag = false;
        int i=0;
        StringBuilder sb = new StringBuilder();
        while(i<p.length()){
            if(p.charAt(i) == '*' && flag){
                
            }else if(p.charAt(i) == '*' && !flag){
                flag=true;
                sb.append(p.charAt(i));
            }else{
                flag=false;
                sb.append(p.charAt(i));
            }
            i++;
        }
        return sb.toString();
    }
	
	

}


class MaxMoney {
	public int start;
	public int end;
	
	MaxMoney(int i, int j){
		this.start = i;
		this.end = j;
	}
}