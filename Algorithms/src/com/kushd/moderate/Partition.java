package com.kushd.moderate;

public class Partition {
	
	
	public static void main(String[] args) {
		System.out.println(partition(5, 5));
	}
	
	private static int partition(int sum, int lNum){
		int[][] table = new int[sum+1][lNum+1];
		for(int i=0;i<=sum;i++){
			table[i][0] = 0;
		}
		for(int i=1;i<=lNum;i++){
			table[0][i] = 1;
		}
		
		for(int i=1;i<=sum;i++){
			for(int j=1;j<=lNum;j++){
				if(i-j < 0){
					table[i][j] = table[i][j-1];
				}else{
					table[i][j] = table[i][j-1] + table[i-j][j];
				}
			}
		}
		
		return table[sum][lNum];
	}

}
