package com.kushd.fbhc2013;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LuckyNumbers {
	
	private static int MAX_DIGITS = 18;
	private static int MAX_NUMBER = 157;
	
	public static void main(String[] args) {
		//List<String>[][] list = integerPartition(MAX_NUMBER,MAX_NUMBER);
		//System.out.println(list[MAX_NUMBER][MAX_NUMBER].size());
		int[][] list = integerP(MAX_NUMBER,MAX_NUMBER);
		System.out.println(list[MAX_NUMBER][MAX_NUMBER]);
		//System.out.println(list[MAX_NUMBER][MAX_NUMBER]);
	}
	
	private static List<String>[][] integerPartition(int sum, int largest ){
		List<String>[][] list = (List<String>[][])new List[sum+1][largest+1];
		
		for(int i=1;i<=sum;i++){
			list[i][0] = new ArrayList<String>();
		}
		for(int i=1;i<=sum;i++){
			for(int j=1;j<=largest;j++){
				list[i][j] = new ArrayList<String>();
				for(String element : list[i][j-1]){
					list[i][j].add(element);
				}
				if(i>j){
					for(String prevElement : list[i-j][j]){
						if(canAdd(prevElement)){
							if(j <= 9){
								list[i][j].add(prevElement+"-"+j);
							}
						}
					}
				}else if(i==j){
					list[i][j].add(Integer.toString(i));
				}
			}
		}
			
		return list;
	}
	
	private static int[][] integerP(int sum, int largest ){
		int[][] list = new int[sum+1][largest+1];
		
		for(int i=1;i<=sum;i++){
			list[i][0] = 0;
		}
		for(int i=1;i<=sum;i++){
			for(int j=1;j<=largest;j++){
					list[i][j] = list[i][j-1];
				if(i>j){
							if(j <= 9){
								list[i][j] = list[i][j] + list[i-j][j];
							}
					
				}else if(i==j && j <= 9){
					list[i][j] = list[i][j] + 1;
				}
			}
		}
			
		return list;
	}

	private static boolean canAdd(String element) {
		if(element.contains("-")){
			String[] digits = element.split("-");
			if(digits.length < MAX_DIGITS){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}

}
